package top.zbeboy.dms.web.system.personal;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.zbeboy.dms.config.Workbook;
import top.zbeboy.dms.domain.dms.tables.pojos.Student;
import top.zbeboy.dms.domain.dms.tables.pojos.Users;
import top.zbeboy.dms.service.data.StudentService;
import top.zbeboy.dms.service.platform.UsersService;
import top.zbeboy.dms.service.util.BCryptUtils;
import top.zbeboy.dms.service.util.DateTimeUtils;
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

    /**
     * 保存学生
     *
     * @return true or false
     */
    @PostMapping(value = "/web/system/personal/student/save")
    public ResponseEntity<Map<String, Object>> save(@RequestParam("realName") String realName,
                                                    String sex,
                                                    String placeOrigin,
                                                    int politicalLandscapeId) {
        AjaxUtils ajaxUtils = AjaxUtils.of();
        String username = usersService.getUserFromSession().getUsername();
        Users users = usersService.findByUsername(username);
        users.setRealName(realName);
        usersService.update(users);

        List<Student> students = studentService.findByUsername(username);
        if(!Objects.isNull(students) && !students.isEmpty()){
            Student student = students.get(0);
            student.setSex(sex);
            student.setPoliticalLandscapeId(politicalLandscapeId);
            student.setPlaceOrigin(placeOrigin);
            studentService.update(student);
        }
        ajaxUtils.success().msg("保存成功");
        return new ResponseEntity<>(ajaxUtils.send(), HttpStatus.OK);
    }
}
