package top.zbeboy.dms.web.data.student;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StudentViewController {

    /**
     * 学生数据页
     *
     * @return 学生数据页.
     */
    @GetMapping(value = "/web/menu/data/student")
    public String student() {
        return "data_v7";
    }
}
