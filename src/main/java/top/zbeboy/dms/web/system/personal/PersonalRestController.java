package top.zbeboy.dms.web.system.personal;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.zbeboy.dms.domain.dms.tables.pojos.Staff;
import top.zbeboy.dms.domain.dms.tables.pojos.Student;
import top.zbeboy.dms.domain.dms.tables.pojos.Users;
import top.zbeboy.dms.service.data.StaffService;
import top.zbeboy.dms.service.data.StudentService;
import top.zbeboy.dms.service.platform.UsersService;
import top.zbeboy.dms.service.util.BCryptUtils;
import top.zbeboy.dms.web.util.AjaxUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
public class PersonalRestController {

    @Resource
    private UsersService usersService;

    @Resource
    private StudentService studentService;

    @Resource
    private StaffService staffService;

    /**
     * 保存学生
     *
     * @return true or false
     */
    @PostMapping(value = "/web/system/personal/student/save")
    public ResponseEntity<Map<String, Object>> student(@RequestParam("realName") String realName,
                                                       String sex,
                                                       String placeOrigin,
                                                       int politicalLandscapeId) {
        AjaxUtils ajaxUtils = AjaxUtils.of();
        String username = usersService.getUserFromSession().getUsername();
        Users users = usersService.findByUsername(username);
        users.setRealName(realName);
        usersService.update(users);

        List<Student> students = studentService.findByUsername(username);
        if (!Objects.isNull(students) && !students.isEmpty()) {
            Student student = students.get(0);
            student.setSex(sex);
            student.setPoliticalLandscapeId(politicalLandscapeId);
            student.setPlaceOrigin(placeOrigin);
            studentService.update(student);
        }
        ajaxUtils.success().msg("保存成功");
        return new ResponseEntity<>(ajaxUtils.send(), HttpStatus.OK);
    }

    /**
     * 保存教师
     *
     * @return true or false
     */
    @PostMapping(value = "/web/system/personal/staff/save")
    public ResponseEntity<Map<String, Object>> staff(@RequestParam("realName") String realName,
                                                     String sex,
                                                     int politicalLandscapeId) {
        AjaxUtils ajaxUtils = AjaxUtils.of();
        String username = usersService.getUserFromSession().getUsername();
        Users users = usersService.findByUsername(username);
        users.setRealName(realName);
        usersService.update(users);

        List<Staff> staffs = staffService.findByUsername(username);
        if (!Objects.isNull(staffs) && !staffs.isEmpty()) {
            Staff staff = staffs.get(0);
            staff.setSex(sex);
            staff.setPoliticalLandscapeId(politicalLandscapeId);
            staffService.update(staff);
        }
        ajaxUtils.success().msg("保存成功");
        return new ResponseEntity<>(ajaxUtils.send(), HttpStatus.OK);
    }

    /**
     * 保存系统
     *
     * @return true or false
     */
    @PostMapping(value = "/web/system/personal/admin/save")
    public ResponseEntity<Map<String, Object>> admin(@RequestParam("realName") String realName) {
        AjaxUtils ajaxUtils = AjaxUtils.of();
        String username = usersService.getUserFromSession().getUsername();
        Users users = usersService.findByUsername(username);
        users.setRealName(realName);
        usersService.update(users);
        ajaxUtils.success().msg("保存成功");
        return new ResponseEntity<>(ajaxUtils.send(), HttpStatus.OK);
    }

    /**
     * 更改密码
     *
     * @return true or false
     */
    @PostMapping(value = "/web/system/personal/password")
    public ResponseEntity<Map<String, Object>> password(@RequestParam("password") String password) {
        AjaxUtils ajaxUtils = AjaxUtils.of();
        String username = usersService.getUserFromSession().getUsername();
        Users users = usersService.findByUsername(username);
        users.setPassword(BCryptUtils.bCryptPassword(password));
        usersService.update(users);
        ajaxUtils.success().msg("保存成功");
        return new ResponseEntity<>(ajaxUtils.send(), HttpStatus.OK);
    }
}
