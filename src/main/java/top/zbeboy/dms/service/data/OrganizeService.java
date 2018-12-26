package top.zbeboy.dms.service.data;

import org.jooq.Record;
import org.jooq.Result;
import top.zbeboy.dms.domain.dms.tables.pojos.Organize;
import top.zbeboy.dms.domain.dms.tables.records.OrganizeRecord;
import top.zbeboy.dms.web.bean.data.organize.OrganizeBean;
import top.zbeboy.dms.web.util.BootstrapTableUtils;

import java.util.Optional;

public interface OrganizeService {

    /**
     * 根据id查询班级信息
     *
     * @param organizeId 班级id
     * @return 数据
     */
    Organize findByOrganizeId(int organizeId);

    /**
     * 关联查询班级信息
     *
     * @param organizeId 班级id
     * @return 数据
     */
    Optional<Record> findByOrganizeIdRelation(int organizeId);

    /**
     * 查询年级下的班级
     *
     * @param organizeIsDel 状态
     * @param gradeId       年级id
     * @return 数据
     */
    Result<OrganizeRecord> findByOrganizeIsDelAndGradeId(boolean organizeIsDel, int gradeId);

    /**
     * 根据班级和年级id查询
     *
     * @param gradeId      年级id
     * @param organizeName 班级
     * @return 数据
     */
    Result<OrganizeRecord> findByGradeIdAndOrganizeName(int gradeId, String organizeName);

    /**
     * 根据班级和年级id查询(不等于班级id)
     *
     * @param organizeId   班级id
     * @param gradeId      年级id
     * @param organizeName 班级
     * @return 数据
     */
    Result<OrganizeRecord> findByGradeIdAndOrganizeNameNeOrganizeId(int organizeId, int gradeId, String organizeName);

    /**
     * 分页查询
     *
     * @param bootstrapTableUtils bootstrapTable工具类
     * @return 分页数据
     */
    Result<Record> findAllByPage(BootstrapTableUtils<OrganizeBean> bootstrapTableUtils);

    /**
     * 根据条件查询总数
     *
     * @return 条件查询总数
     */
    int countByCondition(BootstrapTableUtils<OrganizeBean> bootstrapTableUtils);

    /**
     * 保存
     *
     * @param organize 数据
     */
    void save(Organize organize);

    /**
     * 更新
     *
     * @param organize 数据
     */
    void update(Organize organize);
}
