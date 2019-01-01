package top.zbeboy.dms.web.data.student;

import org.jooq.Record;
import org.jooq.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import top.zbeboy.dms.config.Workbook;
import top.zbeboy.dms.domain.dms.tables.pojos.Authorities;
import top.zbeboy.dms.domain.dms.tables.pojos.Student;
import top.zbeboy.dms.domain.dms.tables.pojos.Users;
import top.zbeboy.dms.domain.dms.tables.records.StudentRecord;
import top.zbeboy.dms.service.common.UploadService;
import top.zbeboy.dms.service.data.StudentService;
import top.zbeboy.dms.service.entry.StudentReadExcel;
import top.zbeboy.dms.service.export.StudentDataExport;
import top.zbeboy.dms.service.platform.UsersService;
import top.zbeboy.dms.service.platform.UsersTypeService;
import top.zbeboy.dms.service.system.AuthoritiesService;
import top.zbeboy.dms.service.util.BCryptUtils;
import top.zbeboy.dms.service.util.DateTimeUtils;
import top.zbeboy.dms.service.util.RequestUtils;
import top.zbeboy.dms.web.bean.data.student.StudentBean;
import top.zbeboy.dms.web.bean.file.FileBean;
import top.zbeboy.dms.web.util.AjaxUtils;
import top.zbeboy.dms.web.util.BootstrapTableUtils;
import top.zbeboy.dms.web.vo.data.student.StudentAddVo;
import top.zbeboy.dms.web.vo.data.student.StudentEditVo;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.*;

@RestController
public class StudentRestController {

    private final Logger log = LoggerFactory.getLogger(StudentRestController.class);

    @Resource
    private StudentService studentService;

    @Resource
    private AuthoritiesService authoritiesService;

    @Resource
    private UsersService usersService;

    @Resource
    private UsersTypeService usersTypeService;

    @Resource
    private UploadService uploadService;

    /**
     * 列表数据
     *
     * @return 数据
     */
    @GetMapping(value = "/web/data/student/data")
    public BootstrapTableUtils<StudentBean> studentData(HttpServletRequest request) {
        BootstrapTableUtils<StudentBean> bootstrapTableUtils = new BootstrapTableUtils<>(request);
        Result<Record> records = studentService.findAllByPage(bootstrapTableUtils);
        List<StudentBean> students = new ArrayList<>();
        if (!ObjectUtils.isEmpty(records) && records.isNotEmpty()) {
            students = records.into(StudentBean.class);
            students.forEach(studentBean -> {
                List<Authorities> authorities = authoritiesService.findByUsername(studentBean.getUsername());
                List<String> role = new ArrayList<>();
                authorities.forEach(auth -> {
                    role.add(authoritiesService.getAuthName(auth.getAuthority()));
                });
                studentBean.setAuthority(io.vavr.collection.List.of(role).mkString(","));
            });
        }
        bootstrapTableUtils.setTotal(studentService.countByCondition(bootstrapTableUtils));
        bootstrapTableUtils.setRows(students);
        return bootstrapTableUtils;
    }

    /**
     * 数据导出
     */
    @GetMapping(value = "/web/data/student/export")
    public void export(HttpServletRequest request, HttpServletResponse response) {
        try {
            BootstrapTableUtils<StudentBean> bootstrapTableUtils = new BootstrapTableUtils<>(request);
            Result<Record> records = studentService.export(bootstrapTableUtils);
            List<StudentBean> students = new ArrayList<>();
            if (!ObjectUtils.isEmpty(records) && records.isNotEmpty()) {
                students = records.into(StudentBean.class);
            }
            String fileName = "学生数据";
            String ext = Workbook.XLSX_FILE;
            StudentDataExport export = new StudentDataExport(students);
            String path = Workbook.studentFilePath() + fileName + "." + ext;
            export.exportExcel(RequestUtils.getRealPath(request) + Workbook.studentFilePath(), fileName, ext);
            uploadService.download(fileName, path, response, request);
        } catch (IOException e) {
            log.error("Export file error, error is {}", e);
        }
    }

    /**
     * 角色数据
     *
     * @return 数据
     */
    @GetMapping(value = "/web/data/student/roles")
    public ResponseEntity<Map<String, Object>> roles() {
        return new ResponseEntity<>(AjaxUtils.of().success().msg("获取数据成功").put("roles", authoritiesService.getAuthAll()).send(), HttpStatus.OK);
    }

    /**
     * 权限数据
     *
     * @return 数据
     */
    @GetMapping(value = "/web/data/student/auths")
    public ResponseEntity<Map<String, Object>> auths(@RequestParam("username") String username) {
        return new ResponseEntity<>(AjaxUtils.of().success().msg("获取数据成功").put("auths", authoritiesService.findByUsername(username)).send(), HttpStatus.OK);
    }

    /**
     * 数据
     *
     * @return 数据
     */
    @PostMapping(value = "/web/data/student/one")
    public ResponseEntity<Map<String, Object>> student(@RequestParam("username") String username) {
        AjaxUtils ajaxUtils = AjaxUtils.of();
        Optional<Record> record = studentService.findByUsernameRelation(username);
        StudentBean student = new StudentBean();
        if (record.isPresent()) {
            student = record.get().into(StudentBean.class);
        }
        ajaxUtils.success().msg("获取数据成功").put("student", student);
        return new ResponseEntity<>(ajaxUtils.send(), HttpStatus.OK);
    }

    /**
     * 检验学号
     *
     * @param studentNumber 学号
     * @return true or false
     */
    @PostMapping(value = "/web/data/student/check/add/number")
    public ResponseEntity<Map<String, Object>> checkAddName(@RequestParam("studentNumber") String studentNumber) {
        AjaxUtils ajaxUtils = AjaxUtils.of();
        String name = StringUtils.trimWhitespace(studentNumber);
        Student student = studentService.findByStudentNumber(name);
        if (Objects.isNull(student)) {
            ajaxUtils.success().msg("学号未被使用");
        } else {
            ajaxUtils.fail().msg("学号已被使用");
        }
        return new ResponseEntity<>(ajaxUtils.send(), HttpStatus.OK);
    }

    /**
     * 检验学号
     *
     * @param studentId     学生id
     * @param studentNumber 学号
     * @return true or false
     */
    @PostMapping(value = "/web/data/student/check/update/number")
    public ResponseEntity<Map<String, Object>> checkUpdateName(@RequestParam("studentId") int studentId,
                                                               @RequestParam("studentNumber") String studentNumber) {
        AjaxUtils ajaxUtils = AjaxUtils.of();
        String name = StringUtils.trimWhitespace(studentNumber);
        Result<StudentRecord> students = studentService.findByStudentNumberNeStudentId(studentId, name);
        if (Objects.isNull(students) || students.isEmpty()) {
            ajaxUtils.success().msg("学号未被使用");
        } else {
            ajaxUtils.fail().msg("学号已被使用");
        }
        return new ResponseEntity<>(ajaxUtils.send(), HttpStatus.OK);
    }

    /**
     * 保存学生
     *
     * @param studentAddVo 数据
     * @return true or false
     */
    @PostMapping(value = "/web/data/student/save")
    public ResponseEntity<Map<String, Object>> save(@Valid StudentAddVo studentAddVo, BindingResult bindingResult) {
        AjaxUtils ajaxUtils = AjaxUtils.of();
        if (!bindingResult.hasErrors()) {
            Users users = new Users();
            users.setUsername(studentAddVo.getStudentNumber());
            users.setPassword(BCryptUtils.bCryptPassword(studentAddVo.getStudentNumber()));
            users.setUsersTypeId(usersTypeService.findByUsersTypeName(Workbook.STUDENT_USERS_TYPE).getUsersTypeId());
            users.setRealName(studentAddVo.getRealName());
            users.setAvatar(Workbook.USERS_AVATAR);
            users.setJoinDate(DateTimeUtils.getNowSqlDate());
            users.setEnabled(true);
            users.setAccountNonExpired(true);
            users.setAccountNonLocked(true);
            users.setCredentialsNonExpired(true);
            usersService.save(users);

            Student student = new Student();
            student.setUsername(studentAddVo.getStudentNumber());
            student.setStudentNumber(studentAddVo.getStudentNumber());
            student.setOrganizeId(studentAddVo.getOrganizeId());
            student.setSex(studentAddVo.getSex());
            student.setPoliticalLandscapeId(studentAddVo.getPoliticalLandscapeId());
            student.setPlaceOrigin(studentAddVo.getPlaceOrigin());
            studentService.save(student);

            ajaxUtils.success().msg("保存成功");
        } else {
            ajaxUtils.fail().msg(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
        }
        return new ResponseEntity<>(ajaxUtils.send(), HttpStatus.OK);
    }

    /**
     * 更新
     *
     * @param studentEditVo 学生
     * @return true or false
     */
    @PostMapping(value = "/web/data/student/update")
    public ResponseEntity<Map<String, Object>> update(@Valid StudentEditVo studentEditVo, BindingResult bindingResult) {
        AjaxUtils ajaxUtils = AjaxUtils.of();
        if (!bindingResult.hasErrors()) {
            Users users = usersService.findByUsername(studentEditVo.getUsername());
            users.setRealName(studentEditVo.getRealName());
            usersService.update(users);

            Student student = studentService.findByStudentId(studentEditVo.getStudentId());
            student.setStudentNumber(studentEditVo.getStudentNumber());
            student.setOrganizeId(studentEditVo.getOrganizeId());
            student.setSex(studentEditVo.getSex());
            student.setPoliticalLandscapeId(studentEditVo.getPoliticalLandscapeId());
            student.setPlaceOrigin(studentEditVo.getPlaceOrigin());
            studentService.update(student);

            ajaxUtils.success().msg("更新成功");
        } else {
            ajaxUtils.fail().msg(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
        }
        return new ResponseEntity<>(ajaxUtils.send(), HttpStatus.OK);
    }

    /**
     * 更新状态
     *
     * @param username 账号
     * @param enabled  状态
     * @return true or false
     */
    @PostMapping(value = "/web/data/student/status")
    public ResponseEntity<Map<String, Object>> update(@RequestParam("username") String username, @RequestParam("enabled") boolean enabled) {
        AjaxUtils ajaxUtils = AjaxUtils.of();
        Users users = usersService.findByUsername(username);
        users.setEnabled(enabled);
        usersService.update(users);
        return new ResponseEntity<>(ajaxUtils.success().msg("更新成功").send(), HttpStatus.OK);
    }

    /**
     * 保存角色
     *
     * @param role 数据
     * @return true or false
     */
    @PostMapping(value = "/web/data/student/role/save")
    public ResponseEntity<Map<String, Object>> roleSave(String[] role, @RequestParam("username") String username) {
        AjaxUtils ajaxUtils = AjaxUtils.of();
        authoritiesService.deleteByUsername(username);
        for (String r : role) {
            Authorities authorities = new Authorities();
            authorities.setUsername(username);
            authorities.setAuthority(r);
            authoritiesService.save(authorities);
        }
        return new ResponseEntity<>(ajaxUtils.success().msg("保存成功").send(), HttpStatus.OK);
    }

    /**
     * 导入学生
     *
     * @param multipartHttpServletRequest 数据
     * @return true or false
     */
    @RequestMapping("/web/data/student/import")
    public ResponseEntity<Map<String, Object>> studentImport(MultipartHttpServletRequest multipartHttpServletRequest) {
        AjaxUtils ajaxUtils = AjaxUtils.of();
        try {
            String path = Workbook.studentImportPath();
            List<FileBean> fileBeen = uploadService.upload(multipartHttpServletRequest,
                    RequestUtils.getRealPath(multipartHttpServletRequest) + path, multipartHttpServletRequest.getRemoteAddr());
            if (Objects.nonNull(fileBeen) && fileBeen.size() > 0) {
                String filePath = fileBeen.get(0).getLastPath();
                List<StudentBean> list = new StudentReadExcel().readExcel(filePath);
                if (Objects.nonNull(list)) {
                    list.forEach(studentBean -> {
                        Users users = new Users();
                        users.setUsername(studentBean.getStudentNumber());
                        users.setPassword(BCryptUtils.bCryptPassword(studentBean.getStudentNumber()));
                        users.setUsersTypeId(usersTypeService.findByUsersTypeName(Workbook.STUDENT_USERS_TYPE).getUsersTypeId());
                        users.setRealName(studentBean.getRealName());
                        users.setAvatar(Workbook.USERS_AVATAR);
                        users.setJoinDate(DateTimeUtils.getNowSqlDate());
                        users.setEnabled(true);
                        users.setAccountNonExpired(true);
                        users.setAccountNonLocked(true);
                        users.setCredentialsNonExpired(true);
                        usersService.save(users);

                        Student student = new Student();
                        student.setUsername(studentBean.getStudentNumber());
                        student.setStudentNumber(studentBean.getStudentNumber());
                        student.setOrganizeId(studentBean.getOrganizeId());
                        student.setSex(studentBean.getSex());
                        student.setPoliticalLandscapeId(studentBean.getPoliticalLandscapeId());
                        student.setPlaceOrigin(studentBean.getPlaceOrigin());
                        studentService.save(student);
                    });
                }
            }
        } catch (Exception e) {
            log.error("Upload file exception,is {}", e);
        }

        return new ResponseEntity<>(ajaxUtils.success().msg("导入成功").send(), HttpStatus.OK);
    }
}
