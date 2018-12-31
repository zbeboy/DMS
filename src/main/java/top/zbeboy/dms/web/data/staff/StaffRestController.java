package top.zbeboy.dms.web.data.staff;

import org.jooq.Record;
import org.jooq.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import top.zbeboy.dms.domain.dms.tables.pojos.Staff;
import top.zbeboy.dms.domain.dms.tables.pojos.Student;
import top.zbeboy.dms.domain.dms.tables.pojos.Users;
import top.zbeboy.dms.domain.dms.tables.records.StaffRecord;
import top.zbeboy.dms.domain.dms.tables.records.StudentRecord;
import top.zbeboy.dms.service.common.UploadService;
import top.zbeboy.dms.service.data.StaffService;
import top.zbeboy.dms.service.data.StudentService;
import top.zbeboy.dms.service.export.StaffDataExport;
import top.zbeboy.dms.service.export.StudentDataExport;
import top.zbeboy.dms.service.platform.UsersService;
import top.zbeboy.dms.service.platform.UsersTypeService;
import top.zbeboy.dms.service.system.AuthoritiesService;
import top.zbeboy.dms.service.util.BCryptUtils;
import top.zbeboy.dms.service.util.DateTimeUtils;
import top.zbeboy.dms.service.util.RequestUtils;
import top.zbeboy.dms.web.bean.data.staff.StaffBean;
import top.zbeboy.dms.web.bean.data.student.StudentBean;
import top.zbeboy.dms.web.data.student.StudentRestController;
import top.zbeboy.dms.web.util.AjaxUtils;
import top.zbeboy.dms.web.util.BootstrapTableUtils;
import top.zbeboy.dms.web.vo.data.staff.StaffAddVo;
import top.zbeboy.dms.web.vo.data.staff.StaffEditVo;
import top.zbeboy.dms.web.vo.data.student.StudentAddVo;
import top.zbeboy.dms.web.vo.data.student.StudentEditVo;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.*;

@RestController
public class StaffRestController {

    private final Logger log = LoggerFactory.getLogger(StaffRestController.class);

    @Resource
    private StaffService staffService;

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
    @GetMapping(value = "/web/data/staff/data")
    public BootstrapTableUtils<StaffBean> staffData(HttpServletRequest request) {
        BootstrapTableUtils<StaffBean> bootstrapTableUtils = new BootstrapTableUtils<>(request);
        Result<Record> records = staffService.findAllByPage(bootstrapTableUtils);
        List<StaffBean> staffs = new ArrayList<>();
        if (!ObjectUtils.isEmpty(records) && records.isNotEmpty()) {
            staffs = records.into(StaffBean.class);
            staffs.forEach(staffBean -> {
                List<Authorities> authorities = authoritiesService.findByUsername(staffBean.getUsername());
                List<String> role = new ArrayList<>();
                authorities.forEach(auth -> {
                    role.add(authoritiesService.getAuthName(auth.getAuthority()));
                });
                staffBean.setAuthority(io.vavr.collection.List.of(role).mkString(","));
            });
        }
        bootstrapTableUtils.setTotal(staffService.countByCondition(bootstrapTableUtils));
        bootstrapTableUtils.setRows(staffs);
        return bootstrapTableUtils;
    }

    /**
     * 数据导出
     */
    @GetMapping(value = "/web/data/staff/export")
    public void export(HttpServletRequest request, HttpServletResponse response) {
        try {
            BootstrapTableUtils<StaffBean> bootstrapTableUtils = new BootstrapTableUtils<>(request);
            Result<Record> records = staffService.export(bootstrapTableUtils);
            List<StaffBean> staffs = new ArrayList<>();
            if (!ObjectUtils.isEmpty(records) && records.isNotEmpty()) {
                staffs = records.into(StaffBean.class);
            }
            String fileName = "教师数据";
            String ext = Workbook.XLSX_FILE;
            StaffDataExport export = new StaffDataExport(staffs);
            String path = Workbook.staffFilePath() + fileName + "." + ext;
            export.exportExcel(RequestUtils.getRealPath(request) + Workbook.staffFilePath(), fileName, ext);
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
    @GetMapping(value = "/web/data/staff/roles")
    public ResponseEntity<Map<String, Object>> roles() {
        return new ResponseEntity<>(AjaxUtils.of().success().msg("获取数据成功").put("roles", authoritiesService.getAuthAll()).send(), HttpStatus.OK);
    }

    /**
     * 权限数据
     *
     * @return 数据
     */
    @GetMapping(value = "/web/data/staff/auths")
    public ResponseEntity<Map<String, Object>> auths(@RequestParam("username") String username) {
        return new ResponseEntity<>(AjaxUtils.of().success().msg("获取数据成功").put("auths", authoritiesService.findByUsername(username)).send(), HttpStatus.OK);
    }

    /**
     * 数据
     *
     * @return 数据
     */
    @PostMapping(value = "/web/data/staff/one")
    public ResponseEntity<Map<String, Object>> student(@RequestParam("username") String username) {
        AjaxUtils ajaxUtils = AjaxUtils.of();
        Optional<Record> record = staffService.findByUsernameRelation(username);
        StaffBean staff = new StaffBean();
        if (record.isPresent()) {
            staff = record.get().into(StaffBean.class);
        }
        ajaxUtils.success().msg("获取数据成功").put("staff", staff);
        return new ResponseEntity<>(ajaxUtils.send(), HttpStatus.OK);
    }

    /**
     * 检验工号
     *
     * @param staffNumber 工号
     * @return true or false
     */
    @PostMapping(value = "/web/data/staff/check/add/number")
    public ResponseEntity<Map<String, Object>> checkAddName(@RequestParam("staffNumber") String staffNumber) {
        AjaxUtils ajaxUtils = AjaxUtils.of();
        String name = StringUtils.trimWhitespace(staffNumber);
        Staff staff = staffService.findByStaffNumber(name);
        if (Objects.isNull(staff)) {
            ajaxUtils.success().msg("工号未被使用");
        } else {
            ajaxUtils.fail().msg("工号已被使用");
        }
        return new ResponseEntity<>(ajaxUtils.send(), HttpStatus.OK);
    }

    /**
     * 检验学号
     *
     * @param staffId     教师id
     * @param staffNumber 工号
     * @return true or false
     */
    @PostMapping(value = "/web/data/staff/check/update/number")
    public ResponseEntity<Map<String, Object>> checkUpdateName(@RequestParam("staffId") int staffId,
                                                               @RequestParam("staffNumber") String staffNumber) {
        AjaxUtils ajaxUtils = AjaxUtils.of();
        String name = StringUtils.trimWhitespace(staffNumber);
        Result<StaffRecord> staffs = staffService.findByStaffNumberNeStaffId(staffId, name);
        if (Objects.isNull(staffs) || staffs.isEmpty()) {
            ajaxUtils.success().msg("工号未被使用");
        } else {
            ajaxUtils.fail().msg("工号已被使用");
        }
        return new ResponseEntity<>(ajaxUtils.send(), HttpStatus.OK);
    }

    /**
     * 保存教师
     *
     * @param staffAddVo 数据
     * @return true or false
     */
    @PostMapping(value = "/web/data/staff/save")
    public ResponseEntity<Map<String, Object>> save(@Valid StaffAddVo staffAddVo, BindingResult bindingResult) {
        AjaxUtils ajaxUtils = AjaxUtils.of();
        if (!bindingResult.hasErrors()) {
            Users users = new Users();
            users.setUsername(staffAddVo.getStaffNumber());
            users.setPassword(BCryptUtils.bCryptPassword(staffAddVo.getStaffNumber()));
            users.setUsersTypeId(usersTypeService.findByUsersTypeName(Workbook.STAFF_USERS_TYPE).getUsersTypeId());
            users.setRealName(staffAddVo.getRealName());
            users.setAvatar(Workbook.USERS_AVATAR);
            users.setJoinDate(DateTimeUtils.getNowSqlDate());
            users.setEnabled(true);
            users.setAccountNonExpired(true);
            users.setAccountNonLocked(true);
            users.setCredentialsNonExpired(true);
            usersService.save(users);

            Staff staff = new Staff();
            staff.setUsername(staffAddVo.getStaffNumber());
            staff.setStaffNumber(staffAddVo.getStaffNumber());
            staff.setDepartmentId(staffAddVo.getDepartmentId());
            staff.setSex(staffAddVo.getSex());
            staff.setPoliticalLandscapeId(staffAddVo.getPoliticalLandscapeId());
            staffService.save(staff);

            ajaxUtils.success().msg("保存成功");
        } else {
            ajaxUtils.fail().msg(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
        }
        return new ResponseEntity<>(ajaxUtils.send(), HttpStatus.OK);
    }

    /**
     * 更新
     *
     * @param staffEditVo 数据
     * @return true or false
     */
    @PostMapping(value = "/web/data/staff/update")
    public ResponseEntity<Map<String, Object>> update(@Valid StaffEditVo staffEditVo, BindingResult bindingResult) {
        AjaxUtils ajaxUtils = AjaxUtils.of();
        if (!bindingResult.hasErrors()) {
            Users users = usersService.findByUsername(staffEditVo.getUsername());
            users.setRealName(staffEditVo.getRealName());
            usersService.update(users);

            Staff staff = staffService.findByStaffId(staffEditVo.getStaffId());
            staff.setStaffNumber(staffEditVo.getStaffNumber());
            staff.setDepartmentId(staffEditVo.getDepartmentId());
            staff.setSex(staffEditVo.getSex());
            staff.setPoliticalLandscapeId(staffEditVo.getPoliticalLandscapeId());
            staffService.update(staff);

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
    @PostMapping(value = "/web/data/staff/status")
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
    @PostMapping(value = "/web/data/staff/role/save")
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
}
