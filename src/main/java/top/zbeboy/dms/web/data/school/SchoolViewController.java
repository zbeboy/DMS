package top.zbeboy.dms.web.data.school;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SchoolViewController {

    /**
     * 登录页
     *
     * @return 登录页.
     */
    @GetMapping(value = "/web/menu/data/school")
    public String login() {
        return "data_v1";
    }
}
