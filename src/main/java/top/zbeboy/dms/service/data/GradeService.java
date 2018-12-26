package top.zbeboy.dms.service.data;

import org.jooq.Record;
import org.jooq.Result;
import top.zbeboy.dms.domain.dms.tables.pojos.Grade;
import top.zbeboy.dms.domain.dms.tables.records.GradeRecord;
import top.zbeboy.dms.web.bean.data.grade.GradeBean;
import top.zbeboy.dms.web.util.BootstrapTableUtils;

import java.util.Optional;

public interface GradeService {

    /**
     * 根据id查询年级信息
     *
     * @param gradeId 年级id
     * @return 数据
     */
    Grade findByGradeId(int gradeId);

    /**
     * 关联查询年级信息
     *
     * @param gradeId 年级id
     * @return 数据
     */
    Optional<Record> findByGradeIdRelation(int gradeId);

    /**
     * 查询专业下的年级
     *
     * @param gradeIsDel 状态
     * @param scienceId  专业id
     * @return 数据
     */
    Result<GradeRecord> findByGradeIsDelAndScienceId(boolean gradeIsDel, int scienceId);

    /**
     * 根据年级和专业id查询
     *
     * @param scienceId 专业id
     * @param grade     年级
     * @return 数据
     */
    Result<GradeRecord> findByScienceIdAndGrade(int scienceId, String grade);

    /**
     * 根据年级和专业id查询(不等于年级id)
     *
     * @param gradeId   年级id
     * @param scienceId 专业id
     * @param grade     年级
     * @return 数据
     */
    Result<GradeRecord> findByScienceIdAndGradeNeGradeId(int gradeId, int scienceId, String grade);

    /**
     * 分页查询
     *
     * @param bootstrapTableUtils bootstrapTable工具类
     * @return 分页数据
     */
    Result<Record> findAllByPage(BootstrapTableUtils<GradeBean> bootstrapTableUtils);

    /**
     * 根据条件查询总数
     *
     * @return 条件查询总数
     */
    int countByCondition(BootstrapTableUtils<GradeBean> bootstrapTableUtils);

    /**
     * 保存
     *
     * @param grade 数据
     */
    void save(Grade grade);

    /**
     * 更新
     *
     * @param grade 数据
     */
    void update(Grade grade);
}
