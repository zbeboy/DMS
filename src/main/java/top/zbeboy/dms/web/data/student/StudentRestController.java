package top.zbeboy.dms.web.data.student;

import org.jooq.Record;
import org.jooq.Result;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.zbeboy.dms.config.Workbook;
import top.zbeboy.dms.domain.dms.tables.pojos.Authorities;
import top.zbeboy.dms.domain.dms.tables.pojos.Student;
import top.zbeboy.dms.domain.dms.tables.pojos.Users;
import top.zbeboy.dms.domain.dms.tables.records.StudentRecord;
import top.zbeboy.dms.service.data.StudentService;
import top.zbeboy.dms.service.platform.UsersService;
import top.zbeboy.dms.service.platform.UsersTypeService;
import top.zbeboy.dms.service.system.AuthoritiesService;
import top.zbeboy.dms.service.util.BCryptUtils;
import top.zbeboy.dms.service.util.DateTimeUtils;
import top.zbeboy.dms.web.bean.data.student.StudentBean;
import top.zbeboy.dms.web.util.AjaxUtils;
import top.zbeboy.dms.web.util.BootstrapTableUtils;
import top.zbeboy.dms.web.vo.data.student.StudentAddVo;
import top.zbeboy.dms.web.vo.data.student.StudentEditVo;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.*;

@RestController
public class StudentRestController {

    @Resource
    private StudentService studentService;

    @Resource
    private AuthoritiesService authoritiesService;

    @Resource
    private UsersService usersService;

    @Resource
    private UsersTypeService usersTypeService;

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
                studentBean.setAuthority(io.vavr.collection.List.of(authorities).mkString(","));
            });
        }
        bootstrapTableUtils.setTotal(studentService.countByCondition(bootstrapTableUtils));
        bootstrapTableUtils.setRows(students);
        return bootstrapTableUtils;
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
}
