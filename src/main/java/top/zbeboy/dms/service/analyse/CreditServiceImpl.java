package top.zbeboy.dms.service.analyse;

import com.alibaba.fastjson.JSONObject;
import org.jooq.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import top.zbeboy.dms.domain.dms.tables.daos.CreditDao;
import top.zbeboy.dms.domain.dms.tables.pojos.Credit;
import top.zbeboy.dms.service.plugin.BootstrapTablesPlugin;
import top.zbeboy.dms.service.util.SQLQueryUtils;
import top.zbeboy.dms.web.bean.analyse.CreditBean;
import top.zbeboy.dms.web.util.BootstrapTableUtils;

import javax.annotation.Resource;

import static top.zbeboy.dms.domain.dms.Tables.*;

@Service("creditService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class CreditServiceImpl extends BootstrapTablesPlugin<CreditBean> implements CreditService {

    private final DSLContext create;

    @Resource
    private CreditDao creditDao;

    @Autowired
    CreditServiceImpl(DSLContext dslContext) {
        create = dslContext;
    }

    @Override
    public Credit findByCreditId(int id) {
        return creditDao.findById(id);
    }

    @Override
    public Result<Record> findAllByPage(BootstrapTableUtils<CreditBean> bootstrapTableUtils) {
        Result<Record> records;
        Condition a = searchCondition(bootstrapTableUtils);
        if (ObjectUtils.isEmpty(a)) {
            SelectJoinStep<Record> selectJoinStep = create.select()
                    .from(CREDIT)
                    .leftJoin(STUDENT)
                    .on(CREDIT.STUDENT_NUMBER.eq(STUDENT.STUDENT_NUMBER))
                    .leftJoin(USERS)
                    .on(STUDENT.USERNAME.eq(USERS.USERNAME))
                    .leftJoin(ORGANIZE)
                    .on(STUDENT.ORGANIZE_ID.eq(ORGANIZE.ORGANIZE_ID));
            sortCondition(bootstrapTableUtils, null, selectJoinStep, JOIN_TYPE);
            pagination(bootstrapTableUtils, null, selectJoinStep, JOIN_TYPE);
            records = selectJoinStep.fetch();
        } else {
            SelectConditionStep<Record> selectConditionStep = create.select()
                    .from(CREDIT)
                    .leftJoin(STUDENT)
                    .on(CREDIT.STUDENT_NUMBER.eq(STUDENT.STUDENT_NUMBER))
                    .leftJoin(USERS)
                    .on(STUDENT.USERNAME.eq(USERS.USERNAME))
                    .leftJoin(ORGANIZE)
                    .on(STUDENT.ORGANIZE_ID.eq(ORGANIZE.ORGANIZE_ID))
                    .where(a);
            sortCondition(bootstrapTableUtils, selectConditionStep, null, CONDITION_TYPE);
            pagination(bootstrapTableUtils, selectConditionStep, null, CONDITION_TYPE);
            records = selectConditionStep.fetch();
        }
        return records;
    }

    @Override
    public int countByCondition(BootstrapTableUtils<CreditBean> bootstrapTableUtils) {
        Record1<Integer> count;
        Condition a = searchCondition(bootstrapTableUtils);
        if (ObjectUtils.isEmpty(a)) {
            SelectJoinStep<Record1<Integer>> selectJoinStep = create.selectCount()
                    .from(CREDIT)
                    .leftJoin(STUDENT)
                    .on(CREDIT.STUDENT_NUMBER.eq(STUDENT.STUDENT_NUMBER))
                    .leftJoin(USERS)
                    .on(STUDENT.USERNAME.eq(USERS.USERNAME))
                    .leftJoin(ORGANIZE)
                    .on(STUDENT.ORGANIZE_ID.eq(ORGANIZE.ORGANIZE_ID));
            count = selectJoinStep.fetchOne();
        } else {
            SelectConditionStep<Record1<Integer>> selectConditionStep = create.selectCount()
                    .from(CREDIT)
                    .leftJoin(STUDENT)
                    .on(CREDIT.STUDENT_NUMBER.eq(STUDENT.STUDENT_NUMBER))
                    .leftJoin(USERS)
                    .on(STUDENT.USERNAME.eq(USERS.USERNAME))
                    .leftJoin(ORGANIZE)
                    .on(STUDENT.ORGANIZE_ID.eq(ORGANIZE.ORGANIZE_ID))
                    .where(a);
            count = selectConditionStep.fetchOne();
        }
        return count.value1();
    }

    @Override
    public Result<Record> export(BootstrapTableUtils<CreditBean> bootstrapTableUtils) {
        Result<Record> records;
        Condition a = searchCondition(bootstrapTableUtils);
        if (ObjectUtils.isEmpty(a)) {
            SelectJoinStep<Record> selectJoinStep = create.select()
                    .from(CREDIT)
                    .leftJoin(STUDENT)
                    .on(CREDIT.STUDENT_NUMBER.eq(STUDENT.STUDENT_NUMBER))
                    .leftJoin(USERS)
                    .on(STUDENT.USERNAME.eq(USERS.USERNAME))
                    .leftJoin(ORGANIZE)
                    .on(STUDENT.ORGANIZE_ID.eq(ORGANIZE.ORGANIZE_ID));
            sortCondition(bootstrapTableUtils, null, selectJoinStep, JOIN_TYPE);
            records = selectJoinStep.fetch();
        } else {
            SelectConditionStep<Record> selectConditionStep = create.select()
                    .from(CREDIT)
                    .leftJoin(STUDENT)
                    .on(CREDIT.STUDENT_NUMBER.eq(STUDENT.STUDENT_NUMBER))
                    .leftJoin(USERS)
                    .on(STUDENT.USERNAME.eq(USERS.USERNAME))
                    .leftJoin(ORGANIZE)
                    .on(STUDENT.ORGANIZE_ID.eq(ORGANIZE.ORGANIZE_ID))
                    .where(a);
            sortCondition(bootstrapTableUtils, selectConditionStep, null, CONDITION_TYPE);
            records = selectConditionStep.fetch();
        }
        return records;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void save(Credit credit) {
        creditDao.insert(credit);
    }

    @Override
    public void update(Credit credit) {
        creditDao.update(credit);
    }

    @Override
    public void deleteById(int id) {
        creditDao.deleteById(id);
    }

    /**
     * 全局搜索条件
     *
     * @param bootstrapTableUtils datatables工具类
     * @return 搜索条件
     */
    @Override
    public Condition searchCondition(BootstrapTableUtils<CreditBean> bootstrapTableUtils) {
        Condition a = null;
        JSONObject search = bootstrapTableUtils.getSearch();
        if (!ObjectUtils.isEmpty(search)) {
            String realName = StringUtils.trimWhitespace(search.getString("realName"));
            String studentNumber = StringUtils.trimWhitespace(search.getString("studentNumber"));
            if (StringUtils.hasLength(realName)) {
                a = USERS.REAL_NAME.like(SQLQueryUtils.likeAllParam(realName));
            }

            if (StringUtils.hasLength(studentNumber)) {
                if (ObjectUtils.isEmpty(a)) {
                    a = CREDIT.STUDENT_NUMBER.like(SQLQueryUtils.likeAllParam(studentNumber));
                } else {
                    a = a.and(CREDIT.STUDENT_NUMBER.like(SQLQueryUtils.likeAllParam(studentNumber)));
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
    public void sortCondition(BootstrapTableUtils<CreditBean> bootstrapTableUtils, SelectConditionStep<Record> selectConditionStep, SelectJoinStep<Record> selectJoinStep, int type) {
        String orderColumnName = bootstrapTableUtils.getSortName();
        String orderDir = bootstrapTableUtils.getSortOrder();
        boolean isAsc = "asc".equalsIgnoreCase(orderDir);
        SortField[] sortField = null;
        if (StringUtils.hasLength(orderColumnName)) {
            if ("realName".equalsIgnoreCase(orderColumnName)) {
                sortField = new SortField[2];
                if (isAsc) {
                    sortField[0] = USERS.REAL_NAME.asc();
                    sortField[1] = CREDIT.CREDIT_ID.asc();
                } else {
                    sortField[0] = USERS.REAL_NAME.desc();
                    sortField[1] = CREDIT.CREDIT_ID.desc();
                }
            }

            if ("studentNumber".equalsIgnoreCase(orderColumnName)) {
                sortField = new SortField[2];
                if (isAsc) {
                    sortField[0] = CREDIT.STUDENT_NUMBER.asc();
                    sortField[1] = CREDIT.CREDIT_ID.asc();
                } else {
                    sortField[0] = CREDIT.STUDENT_NUMBER.desc();
                    sortField[1] = CREDIT.CREDIT_ID.desc();
                }
            }

            if ("organizeName".equalsIgnoreCase(orderColumnName)) {
                sortField = new SortField[2];
                if (isAsc) {
                    sortField[0] = ORGANIZE.ORGANIZE_NAME.asc();
                    sortField[1] = CREDIT.CREDIT_ID.asc();
                } else {
                    sortField[0] = ORGANIZE.ORGANIZE_NAME.desc();
                    sortField[1] = CREDIT.CREDIT_ID.desc();
                }
            }

            if ("year".equalsIgnoreCase(orderColumnName)) {
                sortField = new SortField[2];
                if (isAsc) {
                    sortField[0] = CREDIT.YEAR.asc();
                    sortField[1] = CREDIT.CREDIT_ID.asc();
                } else {
                    sortField[0] = CREDIT.YEAR.desc();
                    sortField[1] = CREDIT.CREDIT_ID.desc();
                }
            }

            if ("term".equalsIgnoreCase(orderColumnName)) {
                sortField = new SortField[2];
                if (isAsc) {
                    sortField[0] = CREDIT.TERM.asc();
                    sortField[1] = CREDIT.CREDIT_ID.asc();
                } else {
                    sortField[0] = CREDIT.TERM.desc();
                    sortField[1] = CREDIT.CREDIT_ID.desc();
                }
            }

            if ("sports".equalsIgnoreCase(orderColumnName)) {
                sortField = new SortField[2];
                if (isAsc) {
                    sortField[0] = CREDIT.SPORTS.asc();
                    sortField[1] = CREDIT.CREDIT_ID.asc();
                } else {
                    sortField[0] = CREDIT.SPORTS.desc();
                    sortField[1] = CREDIT.CREDIT_ID.desc();
                }
            }

            if ("skills".equalsIgnoreCase(orderColumnName)) {
                sortField = new SortField[2];
                if (isAsc) {
                    sortField[0] = CREDIT.SKILLS.asc();
                    sortField[1] = CREDIT.CREDIT_ID.asc();
                } else {
                    sortField[0] = CREDIT.SKILLS.desc();
                    sortField[1] = CREDIT.CREDIT_ID.desc();
                }
            }

            if ("voluntary".equalsIgnoreCase(orderColumnName)) {
                sortField = new SortField[2];
                if (isAsc) {
                    sortField[0] = CREDIT.VOLUNTARY.asc();
                    sortField[1] = CREDIT.CREDIT_ID.asc();
                } else {
                    sortField[0] = CREDIT.VOLUNTARY.desc();
                    sortField[1] = CREDIT.CREDIT_ID.desc();
                }
            }

            if ("technological".equalsIgnoreCase(orderColumnName)) {
                sortField = new SortField[2];
                if (isAsc) {
                    sortField[0] = CREDIT.TECHNOLOGICAL.asc();
                    sortField[1] = CREDIT.CREDIT_ID.asc();
                } else {
                    sortField[0] = CREDIT.TECHNOLOGICAL.desc();
                    sortField[1] = CREDIT.CREDIT_ID.desc();
                }
            }

            if ("post".equalsIgnoreCase(orderColumnName)) {
                sortField = new SortField[2];
                if (isAsc) {
                    sortField[0] = CREDIT.POST.asc();
                    sortField[1] = CREDIT.CREDIT_ID.asc();
                } else {
                    sortField[0] = CREDIT.POST.desc();
                    sortField[1] = CREDIT.CREDIT_ID.desc();
                }
            }

            if ("ideological".equalsIgnoreCase(orderColumnName)) {
                sortField = new SortField[2];
                if (isAsc) {
                    sortField[0] = CREDIT.IDEOLOGICAL.asc();
                    sortField[1] = CREDIT.CREDIT_ID.asc();
                } else {
                    sortField[0] = CREDIT.IDEOLOGICAL.desc();
                    sortField[1] = CREDIT.CREDIT_ID.desc();
                }
            }

            if ("practical".equalsIgnoreCase(orderColumnName)) {
                sortField = new SortField[2];
                if (isAsc) {
                    sortField[0] = CREDIT.PRACTICAL.asc();
                    sortField[1] = CREDIT.CREDIT_ID.asc();
                } else {
                    sortField[0] = CREDIT.PRACTICAL.desc();
                    sortField[1] = CREDIT.CREDIT_ID.desc();
                }
            }

            if ("work".equalsIgnoreCase(orderColumnName)) {
                sortField = new SortField[2];
                if (isAsc) {
                    sortField[0] = CREDIT.WORK.asc();
                    sortField[1] = CREDIT.CREDIT_ID.asc();
                } else {
                    sortField[0] = CREDIT.WORK.desc();
                    sortField[1] = CREDIT.CREDIT_ID.desc();
                }
            }

            if ("achievement".equalsIgnoreCase(orderColumnName)) {
                sortField = new SortField[2];
                if (isAsc) {
                    sortField[0] = CREDIT.ACHIEVEMENT.asc();
                    sortField[1] = CREDIT.CREDIT_ID.asc();
                } else {
                    sortField[0] = CREDIT.ACHIEVEMENT.desc();
                    sortField[1] = CREDIT.CREDIT_ID.desc();
                }
            }
        }
        sortToFinish(selectConditionStep, selectJoinStep, type, sortField);
    }
}
