package top.zbeboy.dms.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import top.zbeboy.dms.config.Workbook;
import top.zbeboy.dms.domain.dms.tables.pojos.Files;
import top.zbeboy.dms.domain.dms.tables.pojos.Users;
import top.zbeboy.dms.service.platform.UsersService;
import top.zbeboy.dms.service.system.AuthoritiesService;
import top.zbeboy.dms.service.system.FilesService;

import javax.annotation.Resource;

@Controller
public class MainController {

    @Resource
    private AuthoritiesService authoritiesService;

    @Resource
    private UsersService usersService;

    @Resource
    private FilesService filesService;

    /**
     * 登录页
     *
     * @return 登录页.
     */
    @GetMapping(value = "/login")
    public String login() {
        return !authoritiesService.isAnonymousAuthenticated() ? "redirect:/web/menu/backstage" : "login";
    }

    /**
     * 后台页
     *
     * @return 后台页.
     */
    @GetMapping(value = "/web/menu/backstage")
    public String backstage(ModelMap modelMap) {
        Users users = usersService.getUserFromSession();
        Files files = filesService.findByFileId(users.getAvatar());
        modelMap.addAttribute("avatar", Workbook.DIRECTORY_SPLIT + files.getRelativePath());
        StringBuilder authorities = authoritiesService.getAuthorities();
        modelMap.addAttribute("auth", authorities);
        return "backstage";
    }

    /**
     * 后台frame页
     *
     * @return 后台frame页.
     */
    @GetMapping(value = "/web/menu/backstage_iframe")
    public String backstageIframe() {
        return "backstage_iframe";
    }
}
