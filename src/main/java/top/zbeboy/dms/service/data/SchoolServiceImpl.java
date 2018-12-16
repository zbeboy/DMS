package top.zbeboy.dms.service.data;

import com.alibaba.fastjson.JSONObject;
import org.jooq.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import top.zbeboy.dms.service.plugin.BootstrapTablesPlugin;
import top.zbeboy.dms.service.util.SQLQueryUtils;
import top.zbeboy.dms.web.bean.data.school.SchoolBean;
import top.zbeboy.dms.web.util.BootstrapTableUtils;

import static top.zbeboy.dms.domain.dms.Tables.SCHOOL;

@Service("schoolService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class SchoolServiceImpl extends BootstrapTablesPlugin<SchoolBean> implements SchoolService {

    private final DSLContext create;

    @Autowired
    SchoolServiceImpl(DSLContext dslContext) {
        create = dslContext;
    }

    @Override
    public Result<Record> findAllByPage(BootstrapTableUtils<SchoolBean> bootstrapTableUtils) {
        return dataPagingQueryAll(bootstrapTableUtils, create, SCHOOL);
    }

    @Override
    public int countByCondition(BootstrapTableUtils<SchoolBean> bootstrapTableUtils) {
        return statisticsWithCondition(bootstrapTableUtils, create, SCHOOL);
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
