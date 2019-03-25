package top.zbeboy.dms.service.quality;

import com.alibaba.fastjson.JSONObject;
import org.jooq.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import top.zbeboy.dms.config.Workbook;
import top.zbeboy.dms.domain.dms.tables.daos.QualityApplyDao;
import top.zbeboy.dms.domain.dms.tables.pojos.QualityApply;
import top.zbeboy.dms.domain.dms.tables.pojos.Users;
import top.zbeboy.dms.service.data.StaffService;
import top.zbeboy.dms.service.platform.UsersService;
import top.zbeboy.dms.service.plugin.BootstrapTablesPlugin;
import top.zbeboy.dms.service.system.AuthoritiesService;
import top.zbeboy.dms.service.util.SQLQueryUtils;
import top.zbeboy.dms.web.bean.data.staff.StaffBean;
import top.zbeboy.dms.web.bean.quality.QualityApplyBean;
import top.zbeboy.dms.web.util.BootstrapTableUtils;

import javax.annotation.Resource;
import java.util.Objects;
import java.util.Optional;

import static top.zbeboy.dms.domain.dms.Tables.*;

@Service("qualityApplyService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class QualityApplyServiceImpl extends BootstrapTablesPlugin<QualityApplyBean> implements QualityApplyService {

    private final DSLContext create;

    @Resource
    private QualityApplyDao qualityApplyDao;

    @Resource
    private UsersService usersService;

    @Resource
    private StaffService staffService;

    @Resource
    private AuthoritiesService authoritiesService;

    @Autowired
    QualityApplyServiceImpl(DSLContext dslContext) {
        create = dslContext;
    }

    @Override
    public QualityApply findById(String id) {
        return qualityApplyDao.findById(id);
    }

    @Override
    public Result<Record> findAllByPage(BootstrapTableUtils<QualityApplyBean> bootstrapTableUtils) {
        Result<Record> records;
        Condition a = searchCondition(bootstrapTableUtils);
        a = buildCondition(a);
        if (ObjectUtils.isEmpty(a)) {
            SelectJoinStep<Record> selectJoinStep = create.select()
                    .from(QUALITY_APPLY)
                    .leftJoin(STUDENT)
                    .on(QUALITY_APPLY.STUDENT_ID.eq(STUDENT.STUDENT_ID))
                    .leftJoin(ORGANIZE)
                    .on(STUDENT.ORGANIZE_ID.eq(ORGANIZE.ORGANIZE_ID))
                    .leftJoin(GRADE)
                    .on(ORGANIZE.GRADE_ID.eq(GRADE.GRADE_ID))
                    .leftJoin(SCIENCE)
                    .on(GRADE.SCIENCE_ID.eq(SCIENCE.SCIENCE_ID))
                    .leftJoin(DEPARTMENT)
                    .on(SCIENCE.DEPARTMENT_ID.eq(DEPARTMENT.DEPARTMENT_ID))
                    .leftJoin(COLLEGE)
                    .on(DEPARTMENT.COLLEGE_ID.eq(COLLEGE.COLLEGE_ID))
                    .leftJoin(USERS)
                    .on(STUDENT.USERNAME.eq(USERS.USERNAME));
            sortCondition(bootstrapTableUtils, null, selectJoinStep, JOIN_TYPE);
            pagination(bootstrapTableUtils, null, selectJoinStep, JOIN_TYPE);
            records = selectJoinStep.fetch();
        } else {
            SelectConditionStep<Record> selectConditionStep = create.select()
                    .from(QUALITY_APPLY)
                    .leftJoin(STUDENT)
                    .on(QUALITY_APPLY.STUDENT_ID.eq(STUDENT.STUDENT_ID))
                    .leftJoin(ORGANIZE)
                    .on(STUDENT.ORGANIZE_ID.eq(ORGANIZE.ORGANIZE_ID))
                    .leftJoin(GRADE)
                    .on(ORGANIZE.GRADE_ID.eq(GRADE.GRADE_ID))
                    .leftJoin(SCIENCE)
                    .on(GRADE.SCIENCE_ID.eq(SCIENCE.SCIENCE_ID))
                    .leftJoin(DEPARTMENT)
                    .on(SCIENCE.DEPARTMENT_ID.eq(DEPARTMENT.DEPARTMENT_ID))
                    .leftJoin(COLLEGE)
                    .on(DEPARTMENT.COLLEGE_ID.eq(COLLEGE.COLLEGE_ID))
                    .leftJoin(USERS)
                    .on(STUDENT.USERNAME.eq(USERS.USERNAME))
                    .where(a);
            sortCondition(bootstrapTableUtils, selectConditionStep, null, CONDITION_TYPE);
            pagination(bootstrapTableUtils, selectConditionStep, null, CONDITION_TYPE);
            records = selectConditionStep.fetch();
        }
        return records;
    }

    @Override
    public int countByCondition(BootstrapTableUtils<QualityApplyBean> bootstrapTableUtils) {
        Record1<Integer> count;
        Condition a = searchCondition(bootstrapTableUtils);
        a = buildCondition(a);
        if (ObjectUtils.isEmpty(a)) {
            SelectJoinStep<Record1<Integer>> selectJoinStep = create.selectCount()
                    .from(QUALITY_APPLY)
                    .leftJoin(STUDENT)
                    .on(QUALITY_APPLY.STUDENT_ID.eq(STUDENT.STUDENT_ID))
                    .leftJoin(ORGANIZE)
                    .on(STUDENT.ORGANIZE_ID.eq(ORGANIZE.ORGANIZE_ID))
                    .leftJoin(GRADE)
                    .on(ORGANIZE.GRADE_ID.eq(GRADE.GRADE_ID))
                    .leftJoin(SCIENCE)
                    .on(GRADE.SCIENCE_ID.eq(SCIENCE.SCIENCE_ID))
                    .leftJoin(DEPARTMENT)
                    .on(SCIENCE.DEPARTMENT_ID.eq(DEPARTMENT.DEPARTMENT_ID))
                    .leftJoin(COLLEGE)
                    .on(DEPARTMENT.COLLEGE_ID.eq(COLLEGE.COLLEGE_ID))
                    .leftJoin(USERS)
                    .on(STUDENT.USERNAME.eq(USERS.USERNAME));
            count = selectJoinStep.fetchOne();
        } else {
            SelectConditionStep<Record1<Integer>> selectConditionStep = create.selectCount()
                    .from(QUALITY_APPLY)
                    .leftJoin(STUDENT)
                    .on(QUALITY_APPLY.STUDENT_ID.eq(STUDENT.STUDENT_ID))
                    .leftJoin(ORGANIZE)
                    .on(STUDENT.ORGANIZE_ID.eq(ORGANIZE.ORGANIZE_ID))
                    .leftJoin(GRADE)
                    .on(ORGANIZE.GRADE_ID.eq(GRADE.GRADE_ID))
                    .leftJoin(SCIENCE)
                    .on(GRADE.SCIENCE_ID.eq(SCIENCE.SCIENCE_ID))
                    .leftJoin(DEPARTMENT)
                    .on(SCIENCE.DEPARTMENT_ID.eq(DEPARTMENT.DEPARTMENT_ID))
                    .leftJoin(COLLEGE)
                    .on(DEPARTMENT.COLLEGE_ID.eq(COLLEGE.COLLEGE_ID))
                    .leftJoin(USERS)
                    .on(STUDENT.USERNAME.eq(USERS.USERNAME))
                    .where(a);
            count = selectConditionStep.fetchOne();
        }
        return count.value1();
    }

    @Override
    public int countByQualityReleaseIdAndApplyState(String qualityReleaseId, int applyState) {
        return create.selectCount()
                .from(QUALITY_APPLY)
                .where(QUALITY_APPLY.QUALITY_RELEASE_ID.eq(qualityReleaseId).and(QUALITY_APPLY.APPLY_STATE.eq(applyState)))
                .fetchOne().value1();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void save(QualityApply qualityApply) {
        qualityApplyDao.insert(qualityApply);
    }

    @Override
    public void deleteById(String id) {
        qualityApplyDao.deleteById(id);
    }

    @Override
    public void update(QualityApply qualityApply) {
        qualityApplyDao.update(qualityApply);
    }

    /**
     * 全局搜索条件
     *
     * @param bootstrapTableUtils datatables工具类
     * @return 搜索条件
     */
    @Override
    public Condition searchCondition(BootstrapTableUtils<QualityApplyBean> bootstrapTableUtils) {
        Condition a = null;
        JSONObject search = bootstrapTableUtils.getSearch();
        if (!ObjectUtils.isEmpty(search)) {
            String applyContent = StringUtils.trimWhitespace(search.getString("applyContent"));
            String studentNumber = StringUtils.trimWhitespace(search.getString("studentNumber"));
            if (StringUtils.hasLength(applyContent)) {
                a = QUALITY_APPLY.APPLY_CONTENT.like(SQLQueryUtils.likeAllParam(applyContent));
            }

            if (StringUtils.hasLength(studentNumber)) {
                if (ObjectUtils.isEmpty(a)) {
                    a = STUDENT.STUDENT_NUMBER.like(SQLQueryUtils.likeAllParam(studentNumber));
                } else {
                    a = a.and(STUDENT.STUDENT_NUMBER.like(SQLQueryUtils.likeAllParam(studentNumber)));
                }
            }
        }
        return a;
    }

    /**
     * 数据排序
     *
     * @param bootstrapTableUtils datatables工具类
     * @param selectConditionStep 条件
     */
    @Override
    public void sortCondition(BootstrapTableUtils<QualityApplyBean> bootstrapTableUtils, SelectConditionStep<Record> selectConditionStep, SelectJoinStep<Record> selectJoinStep, int type) {
        String orderColumnName = bootstrapTableUtils.getSortName();
        String orderDir = bootstrapTableUtils.getSortOrder();
        boolean isAsc = "asc".equalsIgnoreCase(orderDir);
        SortField[] sortField = null;
        if (StringUtils.hasLength(orderColumnName)) {
            if ("realName".equalsIgnoreCase(orderColumnName)) {
                sortField = new SortField[2];
                if (isAsc) {
                    sortField[0] = USERS.REAL_NAME.asc();
                    sortField[1] = QUALITY_APPLY.QUALITY_APPLY_ID.asc();
                } else {
                    sortField[0] = USERS.REAL_NAME.desc();
                    sortField[1] = QUALITY_APPLY.QUALITY_APPLY_ID.desc();
                }
            }

            if ("studentNumber".equalsIgnoreCase(orderColumnName)) {
                sortField = new SortField[2];
                if (isAsc) {
                    sortField[0] = STUDENT.STUDENT_NUMBER.asc();
                    sortField[1] = QUALITY_APPLY.QUALITY_APPLY_ID.asc();
                } else {
                    sortField[0] = STUDENT.STUDENT_NUMBER.desc();
                    sortField[1] = QUALITY_APPLY.QUALITY_APPLY_ID.desc();
                }
            }

            if ("applyContent".equalsIgnoreCase(orderColumnName)) {
                sortField = new SortField[2];
                if (isAsc) {
                    sortField[0] = QUALITY_APPLY.APPLY_CONTENT.asc();
                    sortField[1] = QUALITY_APPLY.QUALITY_APPLY_ID.asc();
                } else {
                    sortField[0] = QUALITY_APPLY.APPLY_CONTENT.desc();
                    sortField[1] = QUALITY_APPLY.QUALITY_APPLY_ID.desc();
                }
            }

            if ("applyDate".equalsIgnoreCase(orderColumnName)) {
                sortField = new SortField[2];
                if (isAsc) {
                    sortField[0] = QUALITY_APPLY.APPLY_DATE.asc();
                    sortField[1] = QUALITY_APPLY.QUALITY_APPLY_ID.asc();
                } else {
                    sortField[0] = QUALITY_APPLY.APPLY_DATE.desc();
                    sortField[1] = QUALITY_APPLY.QUALITY_APPLY_ID.desc();
                }
            }

            if ("applyState".equalsIgnoreCase(orderColumnName)) {
                sortField = new SortField[2];
                if (isAsc) {
                    sortField[0] = QUALITY_APPLY.APPLY_STATE.asc();
                    sortField[1] = QUALITY_APPLY.QUALITY_APPLY_ID.asc();
                } else {
                    sortField[0] = QUALITY_APPLY.APPLY_STATE.desc();
                    sortField[1] = QUALITY_APPLY.QUALITY_APPLY_ID.desc();
                }
            }
        }
        sortToFinish(selectConditionStep, selectJoinStep, type, sortField);
    }

    private Condition buildCondition(Condition condition) {
        Users users = usersService.getUserFromSession();

        if (authoritiesService.isCurrentUserInRole(Workbook.ROLE_COLLEGE_YOUTH_LEAGUE_COMMITTEE)) {
            Optional<Record> staffData = staffService.findByUsernameRelation(users.getUsername());
            if (staffData.isPresent()) {
                StaffBean staffBean = staffData.get().into(StaffBean.class);
                if (Objects.nonNull(condition)) {
                    condition = condition.and(COLLEGE.COLLEGE_ID.eq(staffBean.getCollegeId())).and(QUALITY_APPLY.APPLY_STATE.eq(2));
                } else {
                    condition = COLLEGE.COLLEGE_ID.eq(staffBean.getCollegeId()).and(QUALITY_APPLY.APPLY_STATE.eq(2));
                }
            }
        } else if (authoritiesService.isCurrentUserInRole(Workbook.ROLE_COLLEGE_WORK_DEPARTMENT)) {
            Optional<Record> staffData = staffService.findByUsernameRelation(users.getUsername());
            if (staffData.isPresent()) {
                StaffBean staffBean = staffData.get().into(StaffBean.class);
                if (Objects.nonNull(condition)) {
                    condition = condition.and(COLLEGE.COLLEGE_ID.eq(staffBean.getCollegeId())).and(QUALITY_APPLY.APPLY_STATE.eq(2));
                } else {
                    condition = COLLEGE.COLLEGE_ID.eq(staffBean.getCollegeId()).and(QUALITY_APPLY.APPLY_STATE.eq(2));
                }
            }
        } else if (authoritiesService.isCurrentUserInRole(Workbook.ROLE_DEPARTMENT_INSTRUCTOR)) {
            Optional<Record> staffData = staffService.findByUsernameRelation(users.getUsername());
            if (staffData.isPresent()) {
                StaffBean staffBean = staffData.get().into(StaffBean.class);
                if (Objects.nonNull(condition)) {
                    condition = condition.and(DEPARTMENT.DEPARTMENT_ID.eq(staffBean.getDepartmentId())).and(QUALITY_APPLY.APPLY_STATE.eq(1));
                } else {
                    condition = DEPARTMENT.DEPARTMENT_ID.eq(staffBean.getDepartmentId()).and(QUALITY_APPLY.APPLY_STATE.eq(1));
                }
            }
        } else if (authoritiesService.isCurrentUserInRole(Workbook.ROLE_HEADMASTER)) {
            Optional<Record> staffData = staffService.findByUsernameRelation(users.getUsername());
            if (staffData.isPresent()) {
                StaffBean staffBean = staffData.get().into(StaffBean.class);
                if (Objects.nonNull(condition)) {
                    condition = condition.and(ORGANIZE.STAFF_ID.eq(staffBean.getStaffId())).and(QUALITY_APPLY.APPLY_STATE.eq(0));
                } else {
                    condition = ORGANIZE.STAFF_ID.eq(staffBean.getStaffId()).and(QUALITY_APPLY.APPLY_STATE.eq(0));
                }
            }
        } else if (authoritiesService.isCurrentUserInRole(Workbook.ROLE_STUDENT)) {
            if (Objects.nonNull(condition)) {
                condition = condition.and(STUDENT.USERNAME.eq(users.getUsername()));
            } else {
                condition = STUDENT.USERNAME.eq(users.getUsername());
            }
        }
        return condition;
    }
}
