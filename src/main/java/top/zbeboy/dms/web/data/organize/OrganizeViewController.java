package top.zbeboy.dms.web.data.organize;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrganizeViewController {

    /**
     * 班级数据页
     *
     * @return 班级数据页.
     */
    @GetMapping(value = "/web/menu/data/organize")
    public String organize() {
        return "data_v6";
    }
}
