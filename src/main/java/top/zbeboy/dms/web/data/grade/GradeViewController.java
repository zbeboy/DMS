package top.zbeboy.dms.web.data.grade;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GradeViewController {

    /**
     * 年级数据页
     *
     * @return 年级数据页.
     */
    @GetMapping(value = "/web/menu/data/grade")
    public String grade() {
        return "data_v5";
    }
}
