package top.zbeboy.dms.service.data;

import com.alibaba.fastjson.JSONObject;
import org.jooq.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import top.zbeboy.dms.domain.dms.tables.daos.CollegeDao;
import top.zbeboy.dms.domain.dms.tables.pojos.College;
import top.zbeboy.dms.domain.dms.tables.records.CollegeRecord;
import top.zbeboy.dms.service.plugin.BootstrapTablesPlugin;
import top.zbeboy.dms.service.util.SQLQueryUtils;
import top.zbeboy.dms.web.bean.data.college.CollegeBean;
import top.zbeboy.dms.web.util.BootstrapTableUtils;

import javax.annotation.Resource;

import static top.zbeboy.dms.domain.dms.Tables.COLLEGE;
import static top.zbeboy.dms.domain.dms.Tables.SCHOOL;

@Service("collegeService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class CollegeServiceImpl extends BootstrapTablesPlugin<CollegeBean> implements CollegeService {

    private final DSLContext create;

    @Resource
    private CollegeDao collegeDao;

    @Autowired
    CollegeServiceImpl(DSLContext dslContext) {
        create = dslContext;
    }

    @Override
    public College findByCollegeId(int collegeId) {
        return collegeDao.findById(collegeId);
    }

    @Override
    public Result<CollegeRecord> findBySchoolIdAndCollegeName(int schoolId, String collegeName) {
        return create.selectFrom(COLLEGE)
                .where(COLLEGE.SCHOOL_ID.eq(schoolId).and(COLLEGE.COLLEGE_NAME.eq(collegeName)))
                .fetch();
    }

    @Override
    public Result<CollegeRecord> findBySchoolIdAndCollegeNameNeCollegeId(int collegeId, int schoolId, String collegeName) {
        return create.selectFrom(COLLEGE)
                .where(COLLEGE.SCHOOL_ID.eq(schoolId).and(COLLEGE.COLLEGE_NAME.eq(collegeName)).and(COLLEGE.COLLEGE_ID.ne(collegeId)))
                .fetch();
    }

    @Override
    public Result<Record> findAllByPage(BootstrapTableUtils<CollegeBean> bootstrapTableUtils) {
        Result<Record> records;
        Condition a = searchCondition(bootstrapTableUtils);
        if (ObjectUtils.isEmpty(a)) {
            SelectJoinStep<Record> selectJoinStep = create.select()
                    .from(COLLEGE)
                    .join(SCHOOL)
                    .on(COLLEGE.SCHOOL_ID.eq(SCHOOL.SCHOOL_ID));
            sortCondition(bootstrapTableUtils, null, selectJoinStep, JOIN_TYPE);
            pagination(bootstrapTableUtils, null, selectJoinStep, JOIN_TYPE);
            records = selectJoinStep.fetch();
        } else {
            SelectConditionStep<Record> selectConditionStep = create.select()
                    .from(COLLEGE)
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
    public int countByCondition(BootstrapTableUtils<CollegeBean> bootstrapTableUtils) {
        Record1<Integer> count;
        Condition a = searchCondition(bootstrapTableUtils);
        if (ObjectUtils.isEmpty(a)) {
            SelectJoinStep<Record1<Integer>> selectJoinStep = create.selectCount()
                    .from(COLLEGE)
                    .join(SCHOOL)
                    .on(COLLEGE.COLLEGE_ID.eq(SCHOOL.SCHOOL_ID));
            count = selectJoinStep.fetchOne();
        } else {
            SelectConditionStep<Record1<Integer>> selectConditionStep = create.selectCount()
                    .from(COLLEGE)
                    .join(SCHOOL)
                    .on(COLLEGE.COLLEGE_ID.eq(SCHOOL.SCHOOL_ID))
                    .where(a);
            count = selectConditionStep.fetchOne();
        }
        return count.value1();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void save(College college) {
        collegeDao.insert(college);
    }

    @Override
    public void update(College college) {
        collegeDao.update(college);
    }

    /**
     * 全局搜索条件
     *
     * @param bootstrapTableUtils datatables工具类
     * @return 搜索条件
     */
    @Override
    public Condition searchCondition(BootstrapTableUtils<CollegeBean> bootstrapTableUtils) {
        Condition a = null;
        JSONObject search = bootstrapTableUtils.getSearch();
        if (!ObjectUtils.isEmpty(search)) {
            String schoolName = StringUtils.trimWhitespace(search.getString("schoolName"));
            String collegeName = StringUtils.trimWhitespace(search.getString("collegeName"));
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
    public void sortCondition(BootstrapTableUtils<CollegeBean> bootstrapTableUtils, SelectConditionStep<Record> selectConditionStep, SelectJoinStep<Record> selectJoinStep, int type) {
        String orderColumnName = bootstrapTableUtils.getSortName();
        String orderDir = bootstrapTableUtils.getSortOrder();
        boolean isAsc = "asc".equalsIgnoreCase(orderDir);
        SortField[] sortField = null;
        if (StringUtils.hasLength(orderColumnName)) {
            if ("schoolName".equalsIgnoreCase(orderColumnName)) {
                sortField = new SortField[2];
                if (isAsc) {
                    sortField[0] = SCHOOL.SCHOOL_NAME.asc();
                    sortField[1] = COLLEGE.COLLEGE_ID.asc();
                } else {
                    sortField[0] = SCHOOL.SCHOOL_NAME.desc();
                    sortField[1] = COLLEGE.COLLEGE_ID.desc();
                }
            }

            if ("collegeName".equalsIgnoreCase(orderColumnName)) {
                sortField = new SortField[2];
                if (isAsc) {
                    sortField[0] = COLLEGE.COLLEGE_NAME.asc();
                    sortField[1] = COLLEGE.COLLEGE_ID.asc();
                } else {
                    sortField[0] = COLLEGE.COLLEGE_NAME.desc();
                    sortField[1] = COLLEGE.COLLEGE_ID.desc();
                }
            }

            if ("collegeAddress".equalsIgnoreCase(orderColumnName)) {
                sortField = new SortField[2];
                if (isAsc) {
                    sortField[0] = COLLEGE.COLLEGE_ADDRESS.asc();
                    sortField[1] = COLLEGE.COLLEGE_ID.asc();
                } else {
                    sortField[0] = COLLEGE.COLLEGE_ADDRESS.desc();
                    sortField[1] = COLLEGE.COLLEGE_ID.desc();
                }
            }

            if ("collegeIsDel".equalsIgnoreCase(orderColumnName)) {
                sortField = new SortField[2];
                if (isAsc) {
                    sortField[0] = COLLEGE.COLLEGE_IS_DEL.asc();
                    sortField[1] = COLLEGE.COLLEGE_ID.asc();
                } else {
                    sortField[0] = COLLEGE.COLLEGE_IS_DEL.desc();
                    sortField[1] = COLLEGE.COLLEGE_ID.desc();
                }
            }
        }
        sortToFinish(selectConditionStep, selectJoinStep, type, sortField);
    }
}
