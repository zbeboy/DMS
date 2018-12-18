package top.zbeboy.dms.web.data.college;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CollegeViewController {

    /**
     * 院数据页
     *
     * @return 院数据页.
     */
    @GetMapping(value = "/web/menu/data/college")
    public String school() {
        return "data_v2";
    }
}
