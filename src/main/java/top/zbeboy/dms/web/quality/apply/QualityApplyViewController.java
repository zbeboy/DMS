package top.zbeboy.dms.web.quality.apply;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import top.zbeboy.dms.config.Workbook;
import top.zbeboy.dms.domain.dms.tables.pojos.Student;
import top.zbeboy.dms.domain.dms.tables.pojos.Users;
import top.zbeboy.dms.domain.dms.tables.pojos.UsersType;
import top.zbeboy.dms.service.data.StudentService;
import top.zbeboy.dms.service.platform.UsersService;
import top.zbeboy.dms.service.platform.UsersTypeService;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

@Controller
public class QualityApplyViewController {

    @Resource
    private UsersService usersService;

    @Resource
    private UsersTypeService usersTypeService;

    @Resource
    private StudentService studentService;

    /**
     * 申请页
     *
     * @return 申请页.
     */
    @GetMapping(value = "/web/menu/quality/apply")
    public String apply() {
        return "quality_apply";
    }

    /**
     * 申请添加页
     *
     * @return 申请页.
     */
    @GetMapping(value = "/web/quality/apply/add/{qualityReleaseId}")
    public String applyAdd(@PathVariable("qualityReleaseId") String qualityReleaseId, ModelMap modelMap) {
        String page;
        Users users = usersService.getUserFromSession();
        UsersType usersType = usersTypeService.findByUsersTypeId(users.getUsersTypeId());
        if (StringUtils.equals(usersType.getUsersTypeName(), Workbook.STUDENT_USERS_TYPE)) {
            List<Student> students = studentService.findByUsername(users.getUsername());
            if (Objects.nonNull(students) && students.size() > 0) {
                modelMap.addAttribute("qualityReleaseId", qualityReleaseId);
                modelMap.addAttribute("studentId", students.get(0).getStudentId());
                page = "quality_apply_add";
            } else {
                modelMap.addAttribute("title", "500");
                modelMap.addAttribute("msg", "查询不到您的学生信息");
                page = "tip";
            }
        } else {
            modelMap.addAttribute("title", "500");
            modelMap.addAttribute("msg", "您的注册类型不是学生");
            page = "tip";
        }
        return page;
    }
}
