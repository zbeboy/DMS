package top.zbeboy.dms.service.data;

import com.alibaba.fastjson.JSONObject;
import org.jooq.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import top.zbeboy.dms.domain.dms.tables.daos.OrganizeDao;
import top.zbeboy.dms.domain.dms.tables.pojos.Organize;
import top.zbeboy.dms.domain.dms.tables.records.OrganizeRecord;
import top.zbeboy.dms.service.plugin.BootstrapTablesPlugin;
import top.zbeboy.dms.service.util.SQLQueryUtils;
import top.zbeboy.dms.web.bean.data.organize.OrganizeBean;
import top.zbeboy.dms.web.util.BootstrapTableUtils;

import javax.annotation.Resource;
import java.util.Optional;

import static top.zbeboy.dms.domain.dms.Tables.*;

@Service("organizeService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class OrganizeServiceImpl extends BootstrapTablesPlugin<OrganizeBean> implements OrganizeService {

    private final DSLContext create;

    @Resource
    private OrganizeDao organizeDao;

    @Autowired
    OrganizeServiceImpl(DSLContext dslContext) {
        create = dslContext;
    }

    @Override
    public Organize findByOrganizeId(int organizeId) {
        return organizeDao.findById(organizeId);
    }

    @Override
    public Optional<Record> findByOrganizeIdRelation(int organizeId) {
        return create.select()
                .from(ORGANIZE)
                .join(GRADE)
                .on(ORGANIZE.GRADE_ID.eq(GRADE.GRADE_ID))
                .join(SCIENCE)
                .on(GRADE.SCIENCE_ID.eq(SCIENCE.SCIENCE_ID))
                .join(DEPARTMENT)
                .on(SCIENCE.DEPARTMENT_ID.eq(DEPARTMENT.DEPARTMENT_ID))
                .join(COLLEGE)
                .on(DEPARTMENT.COLLEGE_ID.eq(COLLEGE.COLLEGE_ID))
                .where(ORGANIZE.ORGANIZE_ID.eq(organizeId))
                .fetchOptional();
    }

    @Override
    public Result<OrganizeRecord> findByOrganizeIsDelAndGradeId(boolean organizeIsDel, int gradeId) {
        return create.selectFrom(ORGANIZE)
                .where(ORGANIZE.GRADE_ID.eq(gradeId).and(ORGANIZE.ORGANIZE_IS_DEL.eq(organizeIsDel)))
                .fetch();
    }

    @Override
    public Result<OrganizeRecord> findByGradeIdAndOrganizeName(int gradeId, String organizeName) {
        return create.selectFrom(ORGANIZE)
                .where(ORGANIZE.GRADE_ID.eq(gradeId).and(ORGANIZE.ORGANIZE_NAME.eq(organizeName)))
                .fetch();
    }

    @Override
    public Result<OrganizeRecord> findByGradeIdAndOrganizeNameNeOrganizeId(int organizeId, int gradeId, String organizeName) {
        return create.selectFrom(ORGANIZE)
                .where(ORGANIZE.GRADE_ID.eq(gradeId).and(ORGANIZE.ORGANIZE_NAME.eq(organizeName)).and(ORGANIZE.ORGANIZE_ID.ne(organizeId)))
                .fetch();
    }

    @Override
    public Result<Record> findAllByPage(BootstrapTableUtils<OrganizeBean> bootstrapTableUtils) {
        Result<Record> records;
        Condition a = searchCondition(bootstrapTableUtils);
        if (ObjectUtils.isEmpty(a)) {
            SelectJoinStep<Record> selectJoinStep = create.select()
                    .from(ORGANIZE)
                    .join(GRADE)
                    .on(ORGANIZE.GRADE_ID.eq(GRADE.GRADE_ID))
                    .join(SCIENCE)
                    .on(GRADE.SCIENCE_ID.eq(SCIENCE.SCIENCE_ID))
                    .join(DEPARTMENT)
                    .on(SCIENCE.DEPARTMENT_ID.eq(DEPARTMENT.DEPARTMENT_ID))
                    .join(COLLEGE)
                    .on(DEPARTMENT.COLLEGE_ID.eq(COLLEGE.COLLEGE_ID))
                    .join(SCHOOL)
                    .on(COLLEGE.SCHOOL_ID.eq(SCHOOL.SCHOOL_ID))
                    .leftJoin(STAFF)
                    .on(ORGANIZE.STAFF_ID.eq(STAFF.STAFF_ID))
                    .leftJoin(USERS)
                    .on(STAFF.USERNAME.eq(USERS.USERNAME));
            sortCondition(bootstrapTableUtils, null, selectJoinStep, JOIN_TYPE);
            pagination(bootstrapTableUtils, null, selectJoinStep, JOIN_TYPE);
            records = selectJoinStep.fetch();
        } else {
            SelectConditionStep<Record> selectConditionStep = create.select()
                    .from(ORGANIZE)
                    .join(GRADE)
                    .on(ORGANIZE.GRADE_ID.eq(GRADE.GRADE_ID))
                    .join(SCIENCE)
                    .on(GRADE.SCIENCE_ID.eq(SCIENCE.SCIENCE_ID))
                    .join(DEPARTMENT)
                    .on(SCIENCE.DEPARTMENT_ID.eq(DEPARTMENT.DEPARTMENT_ID))
                    .join(COLLEGE)
                    .on(DEPARTMENT.COLLEGE_ID.eq(COLLEGE.COLLEGE_ID))
                    .join(SCHOOL)
                    .on(COLLEGE.SCHOOL_ID.eq(SCHOOL.SCHOOL_ID))
                    .leftJoin(STAFF)
                    .on(ORGANIZE.STAFF_ID.eq(STAFF.STAFF_ID))
                    .leftJoin(USERS)
                    .on(STAFF.USERNAME.eq(USERS.USERNAME))
                    .where(a);
            sortCondition(bootstrapTableUtils, selectConditionStep, null, CONDITION_TYPE);
            pagination(bootstrapTableUtils, selectConditionStep, null, CONDITION_TYPE);
            records = selectConditionStep.fetch();
        }
        return records;
    }

    @Override
    public int countByCondition(BootstrapTableUtils<OrganizeBean> bootstrapTableUtils) {
        Record1<Integer> count;
        Condition a = searchCondition(bootstrapTableUtils);
        if (ObjectUtils.isEmpty(a)) {
            SelectJoinStep<Record1<Integer>> selectJoinStep = create.selectCount()
                    .from(ORGANIZE)
                    .join(GRADE)
                    .on(ORGANIZE.GRADE_ID.eq(GRADE.GRADE_ID))
                    .join(SCIENCE)
                    .on(GRADE.SCIENCE_ID.eq(SCIENCE.SCIENCE_ID))
                    .join(DEPARTMENT)
                    .on(SCIENCE.DEPARTMENT_ID.eq(DEPARTMENT.DEPARTMENT_ID))
                    .join(COLLEGE)
                    .on(DEPARTMENT.COLLEGE_ID.eq(COLLEGE.COLLEGE_ID))
                    .join(SCHOOL)
                    .on(COLLEGE.COLLEGE_ID.eq(SCHOOL.SCHOOL_ID))
                    .leftJoin(STAFF)
                    .on(ORGANIZE.STAFF_ID.eq(STAFF.STAFF_ID))
                    .leftJoin(USERS)
                    .on(STAFF.USERNAME.eq(USERS.USERNAME));
            count = selectJoinStep.fetchOne();
        } else {
            SelectConditionStep<Record1<Integer>> selectConditionStep = create.selectCount()
                    .from(ORGANIZE)
                    .join(GRADE)
                    .on(ORGANIZE.GRADE_ID.eq(GRADE.GRADE_ID))
                    .join(SCIENCE)
                    .on(GRADE.SCIENCE_ID.eq(SCIENCE.SCIENCE_ID))
                    .join(DEPARTMENT)
                    .on(SCIENCE.DEPARTMENT_ID.eq(DEPARTMENT.DEPARTMENT_ID))
                    .join(COLLEGE)
                    .on(DEPARTMENT.COLLEGE_ID.eq(COLLEGE.COLLEGE_ID))
                    .join(SCHOOL)
                    .on(COLLEGE.COLLEGE_ID.eq(SCHOOL.SCHOOL_ID))
                    .leftJoin(STAFF)
                    .on(ORGANIZE.STAFF_ID.eq(STAFF.STAFF_ID))
                    .leftJoin(USERS)
                    .on(STAFF.USERNAME.eq(USERS.USERNAME))
                    .where(a);
            count = selectConditionStep.fetchOne();
        }
        return count.value1();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void save(Organize organize) {
        organizeDao.insert(organize);
    }

    @Override
    public void update(Organize organize) {
        organizeDao.update(organize);
    }

    /**
     * 全局搜索条件
     *
     * @param bootstrapTableUtils datatables工具类
     * @return 搜索条件
     */
    @Override
    public Condition searchCondition(BootstrapTableUtils<OrganizeBean> bootstrapTableUtils) {
        Condition a = null;
        JSONObject search = bootstrapTableUtils.getSearch();
        if (!ObjectUtils.isEmpty(search)) {
            String schoolName = StringUtils.trimWhitespace(search.getString("schoolName"));
            String collegeName = StringUtils.trimWhitespace(search.getString("collegeName"));
            String departmentName = StringUtils.trimWhitespace(search.getString("departmentName"));
            String scienceName = StringUtils.trimWhitespace(search.getString("scienceName"));
            String grade = StringUtils.trimWhitespace(search.getString("grade"));
            String organizeName = StringUtils.trimWhitespace(search.getString("organizeName"));
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

            if (StringUtils.hasLength(organizeName)) {
                if (ObjectUtils.isEmpty(a)) {
                    a = ORGANIZE.ORGANIZE_NAME.like(SQLQueryUtils.likeAllParam(organizeName));
                } else {
                    a = a.and(ORGANIZE.ORGANIZE_NAME.like(SQLQueryUtils.likeAllParam(organizeName)));
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
    public void sortCondition(BootstrapTableUtils<OrganizeBean> bootstrapTableUtils, SelectConditionStep<Record> selectConditionStep, SelectJoinStep<Record> selectJoinStep, int type) {
        String orderColumnName = bootstrapTableUtils.getSortName();
        String orderDir = bootstrapTableUtils.getSortOrder();
        boolean isAsc = "asc".equalsIgnoreCase(orderDir);
        SortField[] sortField = null;
        if (StringUtils.hasLength(orderColumnName)) {
            if ("schoolName".equalsIgnoreCase(orderColumnName)) {
                sortField = new SortField[2];
                if (isAsc) {
                    sortField[0] = SCHOOL.SCHOOL_NAME.asc();
                    sortField[1] = ORGANIZE.ORGANIZE_ID.asc();
                } else {
                    sortField[0] = SCHOOL.SCHOOL_NAME.desc();
                    sortField[1] = ORGANIZE.ORGANIZE_ID.desc();
                }
            }

            if ("collegeName".equalsIgnoreCase(orderColumnName)) {
                sortField = new SortField[2];
                if (isAsc) {
                    sortField[0] = COLLEGE.COLLEGE_NAME.asc();
                    sortField[1] = ORGANIZE.ORGANIZE_ID.asc();
                } else {
                    sortField[0] = COLLEGE.COLLEGE_NAME.desc();
                    sortField[1] = ORGANIZE.ORGANIZE_ID.desc();
                }
            }

            if ("departmentName".equalsIgnoreCase(orderColumnName)) {
                sortField = new SortField[2];
                if (isAsc) {
                    sortField[0] = DEPARTMENT.DEPARTMENT_NAME.asc();
                    sortField[1] = ORGANIZE.ORGANIZE_ID.asc();
                } else {
                    sortField[0] = DEPARTMENT.DEPARTMENT_NAME.desc();
                    sortField[1] = ORGANIZE.ORGANIZE_ID.desc();
                }
            }

            if ("scienceName".equalsIgnoreCase(orderColumnName)) {
                sortField = new SortField[2];
                if (isAsc) {
                    sortField[0] = SCIENCE.SCIENCE_NAME.asc();
                    sortField[1] = ORGANIZE.ORGANIZE_ID.asc();
                } else {
                    sortField[0] = SCIENCE.SCIENCE_NAME.desc();
                    sortField[1] = ORGANIZE.ORGANIZE_ID.desc();
                }
            }

            if ("grade".equalsIgnoreCase(orderColumnName)) {
                sortField = new SortField[2];
                if (isAsc) {
                    sortField[0] = GRADE.GRADE_.asc();
                    sortField[1] = ORGANIZE.ORGANIZE_ID.asc();
                } else {
                    sortField[0] = GRADE.GRADE_.desc();
                    sortField[1] = ORGANIZE.ORGANIZE_ID.desc();
                }
            }

            if ("organizeName".equalsIgnoreCase(orderColumnName)) {
                sortField = new SortField[2];
                if (isAsc) {
                    sortField[0] = ORGANIZE.ORGANIZE_NAME.asc();
                    sortField[1] = ORGANIZE.ORGANIZE_ID.asc();
                } else {
                    sortField[0] = ORGANIZE.ORGANIZE_NAME.desc();
                    sortField[1] = ORGANIZE.ORGANIZE_ID.desc();
                }
            }

            if ("organizeIsDel".equalsIgnoreCase(orderColumnName)) {
                sortField = new SortField[2];
                if (isAsc) {
                    sortField[0] = ORGANIZE.ORGANIZE_IS_DEL.asc();
                    sortField[1] = ORGANIZE.ORGANIZE_ID.asc();
                } else {
                    sortField[0] = ORGANIZE.ORGANIZE_IS_DEL.desc();
                    sortField[1] = ORGANIZE.ORGANIZE_ID.desc();
                }
            }

            if ("staffId".equalsIgnoreCase(orderColumnName)) {
                sortField = new SortField[2];
                if (isAsc) {
                    sortField[0] = ORGANIZE.STAFF_ID.asc();
                    sortField[1] = ORGANIZE.ORGANIZE_ID.asc();
                } else {
                    sortField[0] = ORGANIZE.STAFF_ID.desc();
                    sortField[1] = ORGANIZE.ORGANIZE_ID.desc();
                }
            }
        }
        sortToFinish(selectConditionStep, selectJoinStep, type, sortField);
    }
}
