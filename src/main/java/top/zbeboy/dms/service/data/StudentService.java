package top.zbeboy.dms.service.data;

import org.jooq.Record;
import org.jooq.Result;
import top.zbeboy.dms.domain.dms.tables.pojos.Student;
import top.zbeboy.dms.domain.dms.tables.records.StudentRecord;
import top.zbeboy.dms.web.bean.data.student.StudentBean;
import top.zbeboy.dms.web.util.BootstrapTableUtils;

import java.util.List;
import java.util.Optional;

public interface StudentService {

    /**
     * 通过id查询
     *
     * @param studentId 学生id
     * @return 数据
     */
    Student findByStudentId(int studentId);

    /**
     * 根据账号查询学生信息
     *
     * @param username 账号
     * @return 数据
     */
    Optional<Record> findByUsernameRelation(String username);

    /**
     * 根据学号查询
     *
     * @param studentNumber 学号
     * @return 数据
     */
    Student findByStudentNumber(String studentNumber);

    /**
     * 根据账号查询
     *
     * @param username 账号
     * @return 数据
     */
    List<Student> findByUsername(String username);

    /**
     * 根据学号查询(不等于学生id)
     *
     * @param studentId     学生id
     * @param studentNumber 学号
     * @return 数据
     */
    Result<StudentRecord> findByStudentNumberNeStudentId(int studentId, String studentNumber);

    /**
     * 分页查询
     *
     * @param bootstrapTableUtils bootstrapTable工具类
     * @return 分页数据
     */
    Result<Record> findAllByPage(BootstrapTableUtils<StudentBean> bootstrapTableUtils);

    /**
     * 根据条件查询总数
     *
     * @return 条件查询总数
     */
    int countByCondition(BootstrapTableUtils<StudentBean> bootstrapTableUtils);

    /**
     * 数据导出
     *
     * @param bootstrapTableUtils bootstrapTable工具类
     * @return 分页数据
     */
    Result<Record> export(BootstrapTableUtils<StudentBean> bootstrapTableUtils);

    /**
     * 保存
     *
     * @param student 数据
     */
    void save(Student student);

    /**
     * 事务性保存
     *
     * @param studentBean 学生
     */
    void saveWithUsers(StudentBean studentBean);

    /**
     * 更新
     *
     * @param student 数据
     */
    void update(Student student);
}
