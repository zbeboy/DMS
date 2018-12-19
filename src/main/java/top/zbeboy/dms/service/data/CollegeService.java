package top.zbeboy.dms.service.data;

import org.jooq.Record;
import org.jooq.Result;
import top.zbeboy.dms.domain.dms.tables.pojos.College;
import top.zbeboy.dms.domain.dms.tables.records.CollegeRecord;
import top.zbeboy.dms.web.bean.data.college.CollegeBean;
import top.zbeboy.dms.web.util.BootstrapTableUtils;

public interface CollegeService {

    /**
     * 根据院查询
     *
     * @param collegeId 院Id
     * @return 数据
     */
    College findByCollegeId(int collegeId);

    /**
     * 根据院名和学校id查询
     *
     * @param schoolId    学校id
     * @param collegeName 院名
     * @return 数据
     */
    Result<CollegeRecord> findBySchoolIdAndCollegeName(int schoolId, String collegeName);

    /**
     * 查询学校下的院
     *
     * @param collegeIsDel 状态
     * @param schoolId     学校id
     * @return 数据
     */
    Result<CollegeRecord> findByCollegeIsDelAndSchoolId(boolean collegeIsDel, int schoolId);

    /**
     * 根据院名和学校id查询(不等于院id)
     *
     * @param collegeId   院id
     * @param schoolId    学校id
     * @param collegeName 院名
     * @return 数据
     */
    Result<CollegeRecord> findBySchoolIdAndCollegeNameNeCollegeId(int collegeId, int schoolId, String collegeName);

    /**
     * 分页查询
     *
     * @param bootstrapTableUtils bootstrapTable工具类
     * @return 分页数据
     */
    Result<Record> findAllByPage(BootstrapTableUtils<CollegeBean> bootstrapTableUtils);

    /**
     * 根据条件查询总数
     *
     * @return 条件查询总数
     */
    int countByCondition(BootstrapTableUtils<CollegeBean> bootstrapTableUtils);

    /**
     * 保存
     *
     * @param college 数据
     */
    void save(College college);

    /**
     * 更新
     *
     * @param college 数据
     */
    void update(College college);
}
