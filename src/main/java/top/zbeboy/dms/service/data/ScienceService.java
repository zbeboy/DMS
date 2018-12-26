package top.zbeboy.dms.service.data;

import org.jooq.Record;
import org.jooq.Result;
import top.zbeboy.dms.domain.dms.tables.pojos.Science;
import top.zbeboy.dms.domain.dms.tables.records.ScienceRecord;
import top.zbeboy.dms.web.bean.data.science.ScienceBean;
import top.zbeboy.dms.web.util.BootstrapTableUtils;

import java.util.Optional;

public interface ScienceService {

    /**
     * 根据id查询专业信息
     *
     * @param scienceId 专业id
     * @return 数据
     */
    Science findByScienceId(int scienceId);

    /**
     * 关联查询专业信息
     *
     * @param scienceId 专业id
     * @return 数据
     */
    Optional<Record> findByScienceIdRelation(int scienceId);

    /**
     * 查询系下的专业
     *
     * @param scienceIsDel 状态
     * @param departmentId 系id
     * @return 数据
     */
    Result<ScienceRecord> findByScienceIsDelAndDepartmentId(boolean scienceIsDel, int departmentId);

    /**
     * 根据专业名和系id查询
     *
     * @param departmentId 系id
     * @param scienceName  专业名
     * @return 数据
     */
    Result<ScienceRecord> findByDepartmentIdAndScienceName(int departmentId, String scienceName);

    /**
     * 根据专业名和系id查询(不等于专业id)
     *
     * @param scienceId    专业id
     * @param departmentId 系id
     * @param scienceName  专业名
     * @return 数据
     */
    Result<ScienceRecord> findByDepartmentIdAndScienceNameNeScienceId(int scienceId, int departmentId, String scienceName);

    /**
     * 分页查询
     *
     * @param bootstrapTableUtils bootstrapTable工具类
     * @return 分页数据
     */
    Result<Record> findAllByPage(BootstrapTableUtils<ScienceBean> bootstrapTableUtils);

    /**
     * 根据条件查询总数
     *
     * @return 条件查询总数
     */
    int countByCondition(BootstrapTableUtils<ScienceBean> bootstrapTableUtils);

    /**
     * 保存
     *
     * @param science 数据
     */
    void save(Science science);

    /**
     * 更新
     *
     * @param science 数据
     */
    void update(Science science);
}
