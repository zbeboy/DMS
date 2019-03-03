package top.zbeboy.dms.web.quality.release;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class QualityReleaseViewController {

    /**
     * 发布页
     *
     * @return 发布页.
     */
    @GetMapping(value = "/web/menu/quality/release")
    public String release() {
        return "quality_release";
    }
}
