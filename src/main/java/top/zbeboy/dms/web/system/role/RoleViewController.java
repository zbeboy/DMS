package top.zbeboy.dms.web.system.role;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RoleViewController {

    /**
     * 系统角色
     *
     * @return 系统角色
     */
    @GetMapping(value = "/web/menu/system/role")
    public String role() {
        return "role";
    }
}
