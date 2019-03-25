package top.zbeboy.dms.service.quality;

import org.jooq.Record;
import org.jooq.Result;
import top.zbeboy.dms.domain.dms.tables.pojos.QualityApply;
import top.zbeboy.dms.web.bean.quality.QualityApplyBean;
import top.zbeboy.dms.web.util.BootstrapTableUtils;

public interface QualityApplyService {

    /**
     * 通过主键查询
     *
     * @param id 主键
     * @return 数据
     */
    QualityApply findById(String id);

    /**
     * 分页查询
     *
     * @param bootstrapTableUtils bootstrapTable工具类
     * @return 分页数据
     */
    Result<Record> findAllByPage(BootstrapTableUtils<QualityApplyBean> bootstrapTableUtils);

    /**
     * 根据条件查询总数
     *
     * @return 条件查询总数
     */
    int countByCondition(BootstrapTableUtils<QualityApplyBean> bootstrapTableUtils);

    /**
     * 根据发布id和状态统计
     *
     * @param qualityReleaseId 发布id
     * @param applyState       状态
     * @return 数量
     */
    int countByQualityReleaseIdAndApplyState(String qualityReleaseId, int applyState);

    /**
     * 保存
     *
     * @param qualityApply 数据
     */
    void save(QualityApply qualityApply);

    /**
     * 删除
     *
     * @param id 主键
     */
    void deleteById(String id);

    /**
     * 更新
     *
     * @param qualityApply 数据
     */
    void update(QualityApply qualityApply);
}
