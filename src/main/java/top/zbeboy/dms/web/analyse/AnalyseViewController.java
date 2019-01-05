package top.zbeboy.dms.web.analyse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AnalyseViewController {

    /**
     * 分析页
     *
     * @return 分析页.
     */
    @GetMapping(value = "/web/menu/analyse")
    public String school() {
        return "analyse_v1";
    }
}
