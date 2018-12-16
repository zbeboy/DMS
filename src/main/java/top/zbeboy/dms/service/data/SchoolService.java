package top.zbeboy.dms.service.data;

import org.jooq.Record;
import org.jooq.Result;
import top.zbeboy.dms.web.bean.data.school.SchoolBean;
import top.zbeboy.dms.web.util.BootstrapTableUtils;

public interface SchoolService {
    /**
     * 分页查询
     *
     * @param bootstrapTableUtils bootstrapTable工具类
     * @return 分页数据
     */
    Result<Record> findAllByPage(BootstrapTableUtils<SchoolBean> bootstrapTableUtils);

    /**
     * 根据条件查询总数
     *
     * @return 条件查询总数
     */
    int countByCondition(BootstrapTableUtils<SchoolBean> bootstrapTableUtils);
}
