package top.zbeboy.dms.service.data;

import org.jooq.Record;
import org.jooq.Result;
import top.zbeboy.dms.domain.dms.tables.pojos.Staff;
import top.zbeboy.dms.domain.dms.tables.records.StaffRecord;
import top.zbeboy.dms.web.bean.data.staff.StaffBean;
import top.zbeboy.dms.web.util.BootstrapTableUtils;

import java.util.Optional;

public interface StaffService {

    /**
     * 通过id查询
     *
     * @param staffId 教师id
     * @return 数据
     */
    Staff findByStaffId(int staffId);

    /**
     * 根据账号查询教师信息
     *
     * @param username 账号
     * @return 数据
     */
    Optional<Record> findByUsernameRelation(String username);

    /**
     * 根据工号查询
     *
     * @param staffNumber 工号
     * @return 数据
     */
    Staff findByStaffNumber(String staffNumber);

    /**
     * 根据工号查询(不等于教师id)
     *
     * @param staffId     教师id
     * @param staffNumber 工号
     * @return 数据
     */
    Result<StaffRecord> findByStaffNumberNeStaffId(int staffId, String staffNumber);

    /**
     * 分页查询
     *
     * @param bootstrapTableUtils bootstrapTable工具类
     * @return 分页数据
     */
    Result<Record> findAllByPage(BootstrapTableUtils<StaffBean> bootstrapTableUtils);

    /**
     * 根据条件查询总数
     *
     * @return 条件查询总数
     */
    int countByCondition(BootstrapTableUtils<StaffBean> bootstrapTableUtils);

    /**
     * 数据导出
     *
     * @param bootstrapTableUtils bootstrapTable工具类
     * @return 分页数据
     */
    Result<Record> export(BootstrapTableUtils<StaffBean> bootstrapTableUtils);

    /**
     * 保存
     *
     * @param staff 数据
     */
    void save(Staff staff);

    /**
     * 事务性保存
     *
     * @param staffBean 教师
     */
    void saveWithUsers(StaffBean staffBean);

    /**
     * 更新
     *
     * @param staff 数据
     */
    void update(Staff staff);
}
