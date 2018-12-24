package top.zbeboy.dms.service.data;

import org.jooq.Record;
import org.jooq.Result;
import top.zbeboy.dms.domain.dms.tables.pojos.Department;
import top.zbeboy.dms.domain.dms.tables.records.DepartmentRecord;
import top.zbeboy.dms.web.bean.data.department.DepartmentBean;
import top.zbeboy.dms.web.util.BootstrapTableUtils;

import java.util.Optional;

public interface DepartmentService {

    /**
     * 根据id查询系信息
     *
     * @param departmentId 系id
     * @return 数据
     */
    Department findByDepartmentId(int departmentId);

    /**
     * 关联查询系信息
     *
     * @param departmentId 系id
     * @return 数据
     */
    Optional<Record> findByDepartmentIdRelation(int departmentId);

    /**
     * 查询院下的系
     *
     * @param departmentIsDel 状态
     * @param collegeId       院id
     * @return 数据
     */
    Result<DepartmentRecord> findByDepartmentIsDelAndCollegeId(boolean departmentIsDel, int collegeId);

    /**
     * 根据系名和院id查询
     *
     * @param collegeId      院id
     * @param departmentName 系名
     * @return 数据
     */
    Result<DepartmentRecord> findByCollegeIdAndDepartmentName(int collegeId, String departmentName);

    /**
     * 根据系名和院id查询(不等于系id)
     *
     * @param departmentId   系id
     * @param collegeId      院id
     * @param departmentName 院名
     * @return 数据
     */
    Result<DepartmentRecord> findByCollegeIdAndDepartmentNameNeDepartmentId(int departmentId, int collegeId, String departmentName);

    /**
     * 分页查询
     *
     * @param bootstrapTableUtils bootstrapTable工具类
     * @return 分页数据
     */
    Result<Record> findAllByPage(BootstrapTableUtils<DepartmentBean> bootstrapTableUtils);

    /**
     * 根据条件查询总数
     *
     * @return 条件查询总数
     */
    int countByCondition(BootstrapTableUtils<DepartmentBean> bootstrapTableUtils);

    /**
     * 保存
     *
     * @param department 数据
     */
    void save(Department department);

    /**
     * 更新
     *
     * @param department 数据
     */
    void update(Department department);
}
