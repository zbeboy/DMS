package top.zbeboy.dms.web.data.department;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DepartmentViewController {

    /**
     * 系数据页
     *
     * @return 系数据页.
     */
    @GetMapping(value = "/web/menu/data/department")
    public String department() {
        return "data_v3";
    }
}
