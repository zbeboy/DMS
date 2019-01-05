package top.zbeboy.dms.web.system.personal;

import org.jooq.Record;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import top.zbeboy.dms.config.Workbook;
import top.zbeboy.dms.domain.dms.tables.pojos.Users;
import top.zbeboy.dms.domain.dms.tables.pojos.UsersType;
import top.zbeboy.dms.service.data.StaffService;
import top.zbeboy.dms.service.data.StudentService;
import top.zbeboy.dms.service.platform.UsersService;
import top.zbeboy.dms.service.platform.UsersTypeService;
import top.zbeboy.dms.web.bean.data.staff.StaffBean;
import top.zbeboy.dms.web.bean.data.student.StudentBean;

import javax.annotation.Resource;
import java.util.Optional;

@Controller
public class PersonalViewController {

    @Resource
    private UsersService usersService;

    @Resource
    private UsersTypeService usersTypeService;

    @Resource
    private StudentService studentService;

    @Resource
    private StaffService staffService;

    /**
     * 个人资料
     *
     * @return 个人资料
     */
    @GetMapping(value = "/web/menu/system/personal")
    public String personal(ModelMap modelMap) {
        Users users = usersService.getUserFromSession();
        UsersType usersType = usersTypeService.findByUsersTypeId(users.getUsersTypeId());
        String page = "A_profile";
        switch (usersType.getUsersTypeName()) {
            case Workbook.STUDENT_USERS_TYPE: {
                Optional<Record> record = studentService.findByUsernameRelation(users.getUsername());
                StudentBean student = new StudentBean();
                if (record.isPresent()) {
                    student = record.get().into(StudentBean.class);
                }
                modelMap.put("student", student);
                page = "S_profile";
                break;
            }
            case Workbook.STAFF_USERS_TYPE: {
                Optional<Record> record = staffService.findByUsernameRelation(users.getUsername());
                StaffBean staff = new StaffBean();
                if (record.isPresent()) {
                    staff = record.get().into(StaffBean.class);
                }
                modelMap.put("staff", staff);
                page = "T_profile";
                break;
            }
            default:
                Users admin = usersService.findByUsername(users.getUsername());
                modelMap.put("admin", admin);
                break;
        }
        return page;
    }
}
