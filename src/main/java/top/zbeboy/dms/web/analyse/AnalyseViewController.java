package top.zbeboy.dms.web.analyse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class AnalyseViewController {

    /**
     * 分析页
     *
     * @return 分析页.
     */
    @GetMapping(value = "/web/menu/analyse")
    public String analyse() {
        return "analyse_v1";
    }

    /**
     * 统计页
     *
     * @return 统计页.
     */
    @GetMapping(value = "/web/analyse/chart/{creditId}")
    public String chart(@PathVariable("creditId") int creditId, ModelMap modelMap) {
        modelMap.addAttribute("creditId", creditId);
        return "analyse_v11";
    }
}
