package top.zbeboy.dms.service.data;

import com.alibaba.fastjson.JSONObject;
import org.jooq.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import top.zbeboy.dms.domain.dms.tables.daos.SchoolDao;
import top.zbeboy.dms.domain.dms.tables.pojos.School;
import top.zbeboy.dms.domain.dms.tables.records.SchoolRecord;
import top.zbeboy.dms.service.plugin.BootstrapTablesPlugin;
import top.zbeboy.dms.service.util.SQLQueryUtils;
import top.zbeboy.dms.web.bean.data.school.SchoolBean;
import top.zbeboy.dms.web.util.BootstrapTableUtils;

import javax.annotation.Resource;

import java.util.List;

import static top.zbeboy.dms.domain.dms.Tables.SCHOOL;

@Service("schoolService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class SchoolServiceImpl extends BootstrapTablesPlugin<SchoolBean> implements SchoolService {

    private final DSLContext create;

    @Resource
    private SchoolDao schoolDao;

    @Autowired
    SchoolServiceImpl(DSLContext dslContext) {
        create = dslContext;
    }

    @Override
    public School findBySchoolId(int schoolId) {
        return schoolDao.findById(schoolId);
    }

    @Override
    public List<School> findBySchoolIsDel(boolean schoolIsDel) {
        return schoolDao.fetchBySchoolIsDel(schoolIsDel);
    }

    @Override
    public List<School> findBySchoolName(String schoolName) {
        return schoolDao.fetchBySchoolName(schoolName);
    }

    @Override
    public Result<SchoolRecord> findBySchoolNameNeSchoolId(int schoolId, String schoolName) {
        return create.selectFrom(SCHOOL)
                .where(SCHOOL.SCHOOL_NAME.eq(schoolName).and(SCHOOL.SCHOOL_ID.ne(schoolId)))
                .fetch();
    }

    @Override
    public Result<Record> findAllByPage(BootstrapTableUtils<SchoolBean> bootstrapTableUtils) {
        return dataPagingQueryAll(bootstrapTableUtils, create, SCHOOL);
    }

    @Override
    public int countByCondition(BootstrapTableUtils<SchoolBean> bootstrapTableUtils) {
        return statisticsWithCondition(bootstrapTableUtils, create, SCHOOL);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void save(School school) {
        schoolDao.insert(school);
    }

    @Override
    public void update(School school) {
        schoolDao.update(school);
    }

    /**
     * 全局搜索条件
     *
     * @param bootstrapTableUtils datatables工具类
     * @return 搜索条件
     */
    @Override
    public Condition searchCondition(BootstrapTableUtils<SchoolBean> bootstrapTableUtils) {
        Condition a = null;
        JSONObject search = bootstrapTableUtils.getSearch();
        if (!ObjectUtils.isEmpty(search)) {
            String schoolName = StringUtils.trimWhitespace(search.getString("schoolName"));
            if (StringUtils.hasLength(schoolName)) {
                a = SCHOOL.SCHOOL_NAME.like(SQLQueryUtils.likeAllParam(schoolName));
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
    public void sortCondition(BootstrapTableUtils<SchoolBean> bootstrapTableUtils, SelectConditionStep<Record> selectConditionStep, SelectJoinStep<Record> selectJoinStep, int type) {
        String orderColumnName = bootstrapTableUtils.getSortName();
        String orderDir = bootstrapTableUtils.getSortOrder();
        boolean isAsc = "asc".equalsIgnoreCase(orderDir);
        SortField[] sortField = null;
        if (StringUtils.hasLength(orderColumnName)) {
            if ("schoolName".equalsIgnoreCase(orderColumnName)) {
                sortField = new SortField[2];
                if (isAsc) {
                    sortField[0] = SCHOOL.SCHOOL_NAME.asc();
                    sortField[1] = SCHOOL.SCHOOL_ID.asc();
                } else {
                    sortField[0] = SCHOOL.SCHOOL_NAME.desc();
                    sortField[1] = SCHOOL.SCHOOL_ID.desc();
                }
            }

            if ("schoolIsDel".equalsIgnoreCase(orderColumnName)) {
                sortField = new SortField[2];
                if (isAsc) {
                    sortField[0] = SCHOOL.SCHOOL_IS_DEL.asc();
                    sortField[1] = SCHOOL.SCHOOL_ID.asc();
                } else {
                    sortField[0] = SCHOOL.SCHOOL_IS_DEL.desc();
                    sortField[1] = SCHOOL.SCHOOL_ID.desc();
                }
            }
        }
        sortToFinish(selectConditionStep, selectJoinStep, type, sortField);
    }
}
