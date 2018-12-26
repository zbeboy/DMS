package top.zbeboy.dms.service.data;

import com.alibaba.fastjson.JSONObject;
import org.jooq.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import top.zbeboy.dms.domain.dms.tables.daos.GradeDao;
import top.zbeboy.dms.domain.dms.tables.pojos.Grade;
import top.zbeboy.dms.domain.dms.tables.records.GradeRecord;
import top.zbeboy.dms.service.plugin.BootstrapTablesPlugin;
import top.zbeboy.dms.service.util.SQLQueryUtils;
import top.zbeboy.dms.web.bean.data.grade.GradeBean;
import top.zbeboy.dms.web.util.BootstrapTableUtils;

import javax.annotation.Resource;
import java.util.Optional;

import static top.zbeboy.dms.domain.dms.Tables.*;

@Service("gradeService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class GradeServiceImpl extends BootstrapTablesPlugin<GradeBean> implements GradeService {

    private final DSLContext create;

    @Resource
    private GradeDao gradeDao;

    @Autowired
    GradeServiceImpl(DSLContext dslContext) {
        create = dslContext;
    }

    @Override
    public Grade findByGradeId(int gradeId) {
        return gradeDao.findById(gradeId);
    }

    @Override
    public Optional<Record> findByGradeIdRelation(int gradeId) {
        return create.select()
                .from(GRADE)
                .join(SCIENCE)
                .on(GRADE.SCIENCE_ID.eq(SCIENCE.SCIENCE_ID))
                .join(DEPARTMENT)
                .on(SCIENCE.DEPARTMENT_ID.eq(DEPARTMENT.DEPARTMENT_ID))
                .join(COLLEGE)
                .on(DEPARTMENT.COLLEGE_ID.eq(COLLEGE.COLLEGE_ID))
                .where(GRADE.GRADE_ID.eq(gradeId))
                .fetchOptional();
    }

    @Override
    public Result<GradeRecord> findByGradeIsDelAndScienceId(boolean gradeIsDel, int scienceId) {
        return create.selectFrom(GRADE)
                .where(GRADE.SCIENCE_ID.eq(scienceId).and(GRADE.GRADE_IS_DEL.eq(gradeIsDel)))
                .fetch();
    }

    @Override
    public Result<GradeRecord> findByScienceIdAndGrade(int scienceId, String grade) {
        return create.selectFrom(GRADE)
                .where(GRADE.SCIENCE_ID.eq(scienceId).and(GRADE.GRADE_.eq(grade)))
                .fetch();
    }

    @Override
    public Result<GradeRecord> findByScienceIdAndGradeNeGradeId(int gradeId, int scienceId, String grade) {
        return create.selectFrom(GRADE)
                .where(GRADE.SCIENCE_ID.eq(scienceId).and(GRADE.GRADE_.eq(grade)).and(GRADE.GRADE_ID.ne(gradeId)))
                .fetch();
    }

    @Override
    public Result<Record> findAllByPage(BootstrapTableUtils<GradeBean> bootstrapTableUtils) {
        Result<Record> records;
        Condition a = searchCondition(bootstrapTableUtils);
        if (ObjectUtils.isEmpty(a)) {
            SelectJoinStep<Record> selectJoinStep = create.select()
                    .from(GRADE)
                    .join(SCIENCE)
                    .on(GRADE.SCIENCE_ID.eq(SCIENCE.SCIENCE_ID))
                    .join(DEPARTMENT)
                    .on(SCIENCE.DEPARTMENT_ID.eq(DEPARTMENT.DEPARTMENT_ID))
                    .join(COLLEGE)
                    .on(DEPARTMENT.COLLEGE_ID.eq(COLLEGE.COLLEGE_ID))
                    .join(SCHOOL)
                    .on(COLLEGE.SCHOOL_ID.eq(SCHOOL.SCHOOL_ID));
            sortCondition(bootstrapTableUtils, null, selectJoinStep, JOIN_TYPE);
            pagination(bootstrapTableUtils, null, selectJoinStep, JOIN_TYPE);
            records = selectJoinStep.fetch();
        } else {
            SelectConditionStep<Record> selectConditionStep = create.select()
                    .from(GRADE)
                    .join(SCIENCE)
                    .on(GRADE.SCIENCE_ID.eq(SCIENCE.SCIENCE_ID))
                    .join(DEPARTMENT)
                    .on(SCIENCE.DEPARTMENT_ID.eq(DEPARTMENT.DEPARTMENT_ID))
                    .join(COLLEGE)
                    .on(DEPARTMENT.COLLEGE_ID.eq(COLLEGE.COLLEGE_ID))
                    .join(SCHOOL)
                    .on(COLLEGE.SCHOOL_ID.eq(SCHOOL.SCHOOL_ID))
                    .where(a);
            sortCondition(bootstrapTableUtils, selectConditionStep, null, CONDITION_TYPE);
            pagination(bootstrapTableUtils, selectConditionStep, null, CONDITION_TYPE);
            records = selectConditionStep.fetch();
        }
        return records;
    }

    @Override
    public int countByCondition(BootstrapTableUtils<GradeBean> bootstrapTableUtils) {
        Record1<Integer> count;
        Condition a = searchCondition(bootstrapTableUtils);
        if (ObjectUtils.isEmpty(a)) {
            SelectJoinStep<Record1<Integer>> selectJoinStep = create.selectCount()
                    .from(GRADE)
                    .join(SCIENCE)
                    .on(GRADE.SCIENCE_ID.eq(SCIENCE.SCIENCE_ID))
                    .join(DEPARTMENT)
                    .on(SCIENCE.DEPARTMENT_ID.eq(DEPARTMENT.DEPARTMENT_ID))
                    .join(COLLEGE)
                    .on(DEPARTMENT.COLLEGE_ID.eq(COLLEGE.COLLEGE_ID))
                    .join(SCHOOL)
                    .on(COLLEGE.COLLEGE_ID.eq(SCHOOL.SCHOOL_ID));
            count = selectJoinStep.fetchOne();
        } else {
            SelectConditionStep<Record1<Integer>> selectConditionStep = create.selectCount()
                    .from(GRADE)
                    .join(SCIENCE)
                    .on(GRADE.SCIENCE_ID.eq(SCIENCE.SCIENCE_ID))
                    .join(DEPARTMENT)
                    .on(SCIENCE.DEPARTMENT_ID.eq(DEPARTMENT.DEPARTMENT_ID))
                    .join(COLLEGE)
                    .on(DEPARTMENT.COLLEGE_ID.eq(COLLEGE.COLLEGE_ID))
                    .join(SCHOOL)
                    .on(COLLEGE.COLLEGE_ID.eq(SCHOOL.SCHOOL_ID))
                    .where(a);
            count = selectConditionStep.fetchOne();
        }
        return count.value1();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void save(Grade grade) {
        gradeDao.insert(grade);
    }

    @Override
    public void update(Grade grade) {
        gradeDao.update(grade);
    }

    /**
     * 全局搜索条件
     *
     * @param bootstrapTableUtils datatables工具类
     * @return 搜索条件
     */
    @Override
    public Condition searchCondition(BootstrapTableUtils<GradeBean> bootstrapTableUtils) {
        Condition a = null;
        JSONObject search = bootstrapTableUtils.getSearch();
        if (!ObjectUtils.isEmpty(search)) {
            String schoolName = StringUtils.trimWhitespace(search.getString("schoolName"));
            String collegeName = StringUtils.trimWhitespace(search.getString("collegeName"));
            String departmentName = StringUtils.trimWhitespace(search.getString("departmentName"));
            String scienceName = StringUtils.trimWhitespace(search.getString("scienceName"));
            String grade = StringUtils.trimWhitespace(search.getString("grade"));
            if (StringUtils.hasLength(schoolName)) {
                a = SCHOOL.SCHOOL_NAME.like(SQLQueryUtils.likeAllParam(schoolName));
            }

            if (StringUtils.hasLength(collegeName)) {
                if (ObjectUtils.isEmpty(a)) {
                    a = COLLEGE.COLLEGE_NAME.like(SQLQueryUtils.likeAllParam(collegeName));
                } else {
                    a = a.and(COLLEGE.COLLEGE_NAME.like(SQLQueryUtils.likeAllParam(collegeName)));
                }
            }

            if (StringUtils.hasLength(departmentName)) {
                if (ObjectUtils.isEmpty(a)) {
                    a = DEPARTMENT.DEPARTMENT_NAME.like(SQLQueryUtils.likeAllParam(departmentName));
                } else {
                    a = a.and(DEPARTMENT.DEPARTMENT_NAME.like(SQLQueryUtils.likeAllParam(departmentName)));
                }
            }

            if (StringUtils.hasLength(scienceName)) {
                if (ObjectUtils.isEmpty(a)) {
                    a = SCIENCE.SCIENCE_NAME.like(SQLQueryUtils.likeAllParam(scienceName));
                } else {
                    a = a.and(SCIENCE.SCIENCE_NAME.like(SQLQueryUtils.likeAllParam(scienceName)));
                }
            }

            if (StringUtils.hasLength(grade)) {
                if (ObjectUtils.isEmpty(a)) {
                    a = GRADE.GRADE_.like(SQLQueryUtils.likeAllParam(grade));
                } else {
                    a = a.and(GRADE.GRADE_.like(SQLQueryUtils.likeAllParam(grade)));
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
    public void sortCondition(BootstrapTableUtils<GradeBean> bootstrapTableUtils, SelectConditionStep<Record> selectConditionStep, SelectJoinStep<Record> selectJoinStep, int type) {
        String orderColumnName = bootstrapTableUtils.getSortName();
        String orderDir = bootstrapTableUtils.getSortOrder();
        boolean isAsc = "asc".equalsIgnoreCase(orderDir);
        SortField[] sortField = null;
        if (StringUtils.hasLength(orderColumnName)) {
            if ("schoolName".equalsIgnoreCase(orderColumnName)) {
                sortField = new SortField[2];
                if (isAsc) {
                    sortField[0] = SCHOOL.SCHOOL_NAME.asc();
                    sortField[1] = GRADE.GRADE_ID.asc();
                } else {
                    sortField[0] = SCHOOL.SCHOOL_NAME.desc();
                    sortField[1] = GRADE.GRADE_ID.desc();
                }
            }

            if ("collegeName".equalsIgnoreCase(orderColumnName)) {
                sortField = new SortField[2];
                if (isAsc) {
                    sortField[0] = COLLEGE.COLLEGE_NAME.asc();
                    sortField[1] = GRADE.GRADE_ID.asc();
                } else {
                    sortField[0] = COLLEGE.COLLEGE_NAME.desc();
                    sortField[1] = GRADE.GRADE_ID.desc();
                }
            }

            if ("departmentName".equalsIgnoreCase(orderColumnName)) {
                sortField = new SortField[2];
                if (isAsc) {
                    sortField[0] = DEPARTMENT.DEPARTMENT_NAME.asc();
                    sortField[1] = GRADE.GRADE_ID.asc();
                } else {
                    sortField[0] = DEPARTMENT.DEPARTMENT_NAME.desc();
                    sortField[1] = GRADE.GRADE_ID.desc();
                }
            }

            if ("scienceName".equalsIgnoreCase(orderColumnName)) {
                sortField = new SortField[2];
                if (isAsc) {
                    sortField[0] = SCIENCE.SCIENCE_NAME.asc();
                    sortField[1] = GRADE.GRADE_ID.asc();
                } else {
                    sortField[0] = SCIENCE.SCIENCE_NAME.desc();
                    sortField[1] = GRADE.GRADE_ID.desc();
                }
            }

            if ("grade".equalsIgnoreCase(orderColumnName)) {
                sortField = new SortField[2];
                if (isAsc) {
                    sortField[0] = GRADE.GRADE_.asc();
                    sortField[1] = GRADE.GRADE_ID.asc();
                } else {
                    sortField[0] = GRADE.GRADE_.desc();
                    sortField[1] = GRADE.GRADE_ID.desc();
                }
            }

            if ("gradeIsDel".equalsIgnoreCase(orderColumnName)) {
                sortField = new SortField[2];
                if (isAsc) {
                    sortField[0] = GRADE.GRADE_IS_DEL.asc();
                    sortField[1] = GRADE.GRADE_ID.asc();
                } else {
                    sortField[0] = GRADE.GRADE_IS_DEL.desc();
                    sortField[1] = GRADE.GRADE_ID.desc();
                }
            }
        }
        sortToFinish(selectConditionStep, selectJoinStep, type, sortField);
    }
}
