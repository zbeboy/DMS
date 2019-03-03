package top.zbeboy.dms.service.quality;

import org.jooq.Record;
import org.jooq.Result;
import top.zbeboy.dms.domain.dms.tables.pojos.QualityRelease;
import top.zbeboy.dms.web.bean.quality.QualityReleaseBean;
import top.zbeboy.dms.web.util.BootstrapTableUtils;

public interface QualityReleaseService {

    /**
     * 通过主键查询
     *
     * @param id 主键
     * @return 数据
     */
    QualityRelease findById(String id);

    /**
     * 分页查询
     *
     * @param bootstrapTableUtils bootstrapTable工具类
     * @return 分页数据
     */
    Result<Record> findAllByPage(BootstrapTableUtils<QualityReleaseBean> bootstrapTableUtils);

    /**
     * 根据条件查询总数
     *
     * @return 条件查询总数
     */
    int countByCondition(BootstrapTableUtils<QualityReleaseBean> bootstrapTableUtils);

    /**
     * 保存
     *
     * @param qualityRelease 数据
     */
    void save(QualityRelease qualityRelease);

    /**
     * 更新
     *
     * @param qualityRelease 数据
     */
    void update(QualityRelease qualityRelease);
}
