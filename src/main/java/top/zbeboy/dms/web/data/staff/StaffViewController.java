package top.zbeboy.dms.web.data.staff;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StaffViewController {

    /**
     * 教师数据页
     *
     * @return 教师数据页.
     */
    @GetMapping(value = "/web/menu/data/staff")
    public String staff() {
        return "data_v8";
    }
}
