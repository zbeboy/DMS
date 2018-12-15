package top.zbeboy.dms.web.data.school;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SchoolViewController {

    /**
     * 学校数据页
     *
     * @return 学校数据页.
     */
    @GetMapping(value = "/web/menu/data/school")
    public String school() {
        return "data_v1";
    }
}
