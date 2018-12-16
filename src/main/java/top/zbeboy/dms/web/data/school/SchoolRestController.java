package top.zbeboy.dms.web.data.school;

import org.jooq.Record;
import org.jooq.Result;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import top.zbeboy.dms.service.data.SchoolService;
import top.zbeboy.dms.web.bean.data.school.SchoolBean;
import top.zbeboy.dms.web.util.BootstrapTableUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
public class SchoolRestController {

    @Resource
    private SchoolService schoolService;

    /**
     * 列表数据
     *
     * @return 数据
     */
    @GetMapping(value = "/web/data/school/data")
    public BootstrapTableUtils<SchoolBean> schoolData(HttpServletRequest request) {
        BootstrapTableUtils<SchoolBean> bootstrapTableUtils = new BootstrapTableUtils<>(request);
        Result<Record> records = schoolService.findAllByPage(bootstrapTableUtils);
        List<SchoolBean> schools = new ArrayList<>();
        if (!ObjectUtils.isEmpty(records) && records.isNotEmpty()) {
            schools = records.into(SchoolBean.class);
        }
        bootstrapTableUtils.setTotal(schoolService.countByCondition(bootstrapTableUtils));
        bootstrapTableUtils.setRows(schools);
        return bootstrapTableUtils;
    }
}
