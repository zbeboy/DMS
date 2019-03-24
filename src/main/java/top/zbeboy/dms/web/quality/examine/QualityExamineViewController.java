package top.zbeboy.dms.web.quality.examine;

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
public class QualityExamineViewController {

    @Resource
    private UsersService usersService;

    @Resource
    private UsersTypeService usersTypeService;

    @Resource
    private StudentService studentService;

    /**
     * 审核页
     *
     * @return 审核页.
     */
    @GetMapping(value = "/web/menu/quality/examine")
    public String examine() {
        return "quality_examine";
    }

    /**
     * 审核更新页
     *
     * @return 审核页.
     */
    @GetMapping(value = "/web/quality/examine/edit/{qualityReleaseId}")
    public String examineEdit(@PathVariable("qualityReleaseId") String qualityReleaseId, ModelMap modelMap) {
        String page;
        Users users = usersService.getUserFromSession();
        UsersType usersType = usersTypeService.findByUsersTypeId(users.getUsersTypeId());
        if (!StringUtils.equals(usersType.getUsersTypeName(), Workbook.STUDENT_USERS_TYPE)) {
            modelMap.addAttribute("qualityReleaseId", qualityReleaseId);
            page = "quality_examine_edit";
        } else {
            modelMap.addAttribute("title", "500");
            modelMap.addAttribute("msg", "您的注册类型学生，不允许审核");
            page = "tip";
        }
        return page;
    }
}
