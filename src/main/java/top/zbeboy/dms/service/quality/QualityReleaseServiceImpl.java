package top.zbeboy.dms.service.quality;

import com.alibaba.fastjson.JSONObject;
import org.jooq.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import top.zbeboy.dms.domain.dms.tables.daos.QualityReleaseDao;
import top.zbeboy.dms.domain.dms.tables.pojos.QualityRelease;
import top.zbeboy.dms.service.plugin.BootstrapTablesPlugin;
import top.zbeboy.dms.service.util.SQLQueryUtils;
import top.zbeboy.dms.web.bean.quality.QualityReleaseBean;
import top.zbeboy.dms.web.util.BootstrapTableUtils;

import javax.annotation.Resource;

import static top.zbeboy.dms.domain.dms.Tables.QUALITY_RELEASE;
import static top.zbeboy.dms.domain.dms.Tables.USERS;

@Service("qualityReleaseService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class QualityReleaseServiceImpl extends BootstrapTablesPlugin<QualityReleaseBean> implements QualityReleaseService {

    private final DSLContext create;

    @Resource
    private QualityReleaseDao qualityReleaseDao;

    @Autowired
    QualityReleaseServiceImpl(DSLContext dslContext) {
        create = dslContext;
    }

    @Override
    public QualityRelease findById(String id) {
        return qualityReleaseDao.findById(id);
    }

    @Override
    public Result<Record> findAllByPage(BootstrapTableUtils<QualityReleaseBean> bootstrapTableUtils) {
        Result<Record> records;
        Condition a = searchCondition(bootstrapTableUtils);
        if (ObjectUtils.isEmpty(a)) {
            SelectJoinStep<Record> selectJoinStep = create.select()
                    .from(QUALITY_RELEASE)
                    .join(USERS)
                    .on(QUALITY_RELEASE.USERNAME.eq(USERS.USERNAME));
            sortCondition(bootstrapTableUtils, null, selectJoinStep, JOIN_TYPE);
            pagination(bootstrapTableUtils, null, selectJoinStep, JOIN_TYPE);
            records = selectJoinStep.fetch();
        } else {
            SelectConditionStep<Record> selectConditionStep = create.select()
                    .from(QUALITY_RELEASE)
                    .join(USERS)
                    .on(QUALITY_RELEASE.USERNAME.eq(USERS.USERNAME))
                    .where(a);
            sortCondition(bootstrapTableUtils, selectConditionStep, null, CONDITION_TYPE);
            pagination(bootstrapTableUtils, selectConditionStep, null, CONDITION_TYPE);
            records = selectConditionStep.fetch();
        }
        return records;
    }

    @Override
    public int countByCondition(BootstrapTableUtils<QualityReleaseBean> bootstrapTableUtils) {
        Record1<Integer> count;
        Condition a = searchCondition(bootstrapTableUtils);
        if (ObjectUtils.isEmpty(a)) {
            SelectJoinStep<Record1<Integer>> selectJoinStep = create.selectCount()
                    .from(QUALITY_RELEASE)
                    .join(USERS)
                    .on(QUALITY_RELEASE.USERNAME.eq(USERS.USERNAME));
            count = selectJoinStep.fetchOne();
        } else {
            SelectConditionStep<Record1<Integer>> selectConditionStep = create.selectCount()
                    .from(QUALITY_RELEASE)
                    .join(USERS)
                    .on(QUALITY_RELEASE.USERNAME.eq(USERS.USERNAME))
                    .where(a);
            count = selectConditionStep.fetchOne();
        }
        return count.value1();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void save(QualityRelease qualityRelease) {
        qualityReleaseDao.insert(qualityRelease);
    }

    @Override
    public void update(QualityRelease qualityRelease) {
        qualityReleaseDao.update(qualityRelease);
    }

    /**
     * 全局搜索条件
     *
     * @param bootstrapTableUtils datatables工具类
     * @return 搜索条件
     */
    @Override
    public Condition searchCondition(BootstrapTableUtils<QualityReleaseBean> bootstrapTableUtils) {
        Condition a = null;
        JSONObject search = bootstrapTableUtils.getSearch();
        if (!ObjectUtils.isEmpty(search)) {
            String releaseTitle = StringUtils.trimWhitespace(search.getString("releaseTitle"));
            if (StringUtils.hasLength(releaseTitle)) {
                a = QUALITY_RELEASE.RELEASE_TITLE.like(SQLQueryUtils.likeAllParam(releaseTitle));
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
    public void sortCondition(BootstrapTableUtils<QualityReleaseBean> bootstrapTableUtils, SelectConditionStep<Record> selectConditionStep, SelectJoinStep<Record> selectJoinStep, int type) {
        String orderColumnName = bootstrapTableUtils.getSortName();
        String orderDir = bootstrapTableUtils.getSortOrder();
        boolean isAsc = "asc".equalsIgnoreCase(orderDir);
        SortField[] sortField = null;
        if (StringUtils.hasLength(orderColumnName)) {
            if ("releaseTitle".equalsIgnoreCase(orderColumnName)) {
                sortField = new SortField[2];
                if (isAsc) {
                    sortField[0] = QUALITY_RELEASE.RELEASE_TITLE.asc();
                    sortField[1] = QUALITY_RELEASE.QUALITY_RELEASE_ID.asc();
                } else {
                    sortField[0] = QUALITY_RELEASE.RELEASE_TITLE.desc();
                    sortField[1] = QUALITY_RELEASE.QUALITY_RELEASE_ID.desc();
                }
            }

            if ("year".equalsIgnoreCase(orderColumnName)) {
                sortField = new SortField[2];
                if (isAsc) {
                    sortField[0] = QUALITY_RELEASE.YEAR.asc();
                    sortField[1] = QUALITY_RELEASE.QUALITY_RELEASE_ID.asc();
                } else {
                    sortField[0] = QUALITY_RELEASE.YEAR.desc();
                    sortField[1] = QUALITY_RELEASE.QUALITY_RELEASE_ID.desc();
                }
            }

            if ("term".equalsIgnoreCase(orderColumnName)) {
                sortField = new SortField[2];
                if (isAsc) {
                    sortField[0] = QUALITY_RELEASE.TERM.asc();
                    sortField[1] = QUALITY_RELEASE.QUALITY_RELEASE_ID.asc();
                } else {
                    sortField[0] = QUALITY_RELEASE.TERM.desc();
                    sortField[1] = QUALITY_RELEASE.QUALITY_RELEASE_ID.desc();
                }
            }

            if ("releaseDate".equalsIgnoreCase(orderColumnName)) {
                sortField = new SortField[2];
                if (isAsc) {
                    sortField[0] = QUALITY_RELEASE.RELEASE_DATE.asc();
                    sortField[1] = QUALITY_RELEASE.QUALITY_RELEASE_ID.asc();
                } else {
                    sortField[0] = QUALITY_RELEASE.RELEASE_DATE.desc();
                    sortField[1] = QUALITY_RELEASE.QUALITY_RELEASE_ID.desc();
                }
            }

            if ("realName".equalsIgnoreCase(orderColumnName)) {
                sortField = new SortField[2];
                if (isAsc) {
                    sortField[0] = USERS.REAL_NAME.asc();
                    sortField[1] = QUALITY_RELEASE.QUALITY_RELEASE_ID.asc();
                } else {
                    sortField[0] = USERS.REAL_NAME.desc();
                    sortField[1] = QUALITY_RELEASE.QUALITY_RELEASE_ID.desc();
                }
            }
        }
        sortToFinish(selectConditionStep, selectJoinStep, type, sortField);
    }
}
