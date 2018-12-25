package top.zbeboy.dms.web.data.science;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ScienceViewController {

    /**
     * 专业数据页
     *
     * @return 专业数据页.
     */
    @GetMapping(value = "/web/menu/data/science")
    public String science() {
        return "data_v4";
    }
}
