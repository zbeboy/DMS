package top.zbeboy.dms.web.platform;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UsersViewController {

    /**
     * 联系我们
     *
     * @return 联系我们
     */
    @GetMapping(value = "/web/menu/contacts")
    public String contacts() {
        return "contacts";
    }
}
