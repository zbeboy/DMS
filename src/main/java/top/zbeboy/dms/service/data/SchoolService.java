package top.zbeboy.dms.service.data;

import org.jooq.Record;
import org.jooq.Result;
import top.zbeboy.dms.domain.dms.tables.pojos.School;
import top.zbeboy.dms.domain.dms.tables.records.SchoolRecord;
import top.zbeboy.dms.web.bean.data.school.SchoolBean;
import top.zbeboy.dms.web.util.BootstrapTableUtils;

import java.util.List;

public interface SchoolService {

    /**
     * 根据学校查询
     *
     * @param schoolId 学校Id
     * @return 数据
     */
    School findBySchoolId(int schoolId);

    /**
     * 根据学校名查询
     *
     * @param schoolName 学校名
     * @return 数据
     */
    List<School> findBySchoolName(String schoolName);

    /**
     * 根据学校名查询(不等于学校id)
     *
     * @param schoolId   学校Id
     * @param schoolName 学校名
     * @return 数据
     */
    Result<SchoolRecord> findBySchoolNameNeSchoolId(int schoolId, String schoolName);

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

    /**
     * 保存
     *
     * @param school 数据
     */
    void save(School school);

    /**
     * 更新
     *
     * @param school 数据
     */
    void update(School school);
}
