package top.zbeboy.dms.service.plugin;

import org.jooq.*;
import org.springframework.util.ObjectUtils;
import top.zbeboy.dms.web.util.BootstrapTableUtils;

public class BootstrapTablesPlugin<T> {

    public static final int CONDITION_TYPE = 0;

    public static final int JOIN_TYPE = 1;

    public static final int ON_CONDITION_TYPE = 2;

    /**
     * 查询全部数据
     *
     * @param bootstrapTableUtils bootstrapTable工具类
     * @param create              jooq create.
     * @param table               jooq table.
     * @return 全部数据
     */
    public Result<Record> dataPagingQueryAll(BootstrapTableUtils<T> bootstrapTableUtils, DSLContext create, TableLike table) {
        Result<Record> records;
        Condition a = searchCondition(bootstrapTableUtils);
        if (ObjectUtils.isEmpty(a)) {
            SelectJoinStep<Record> selectJoinStep = create.select()
                    .from(table);
            sortCondition(bootstrapTableUtils, null, selectJoinStep, JOIN_TYPE);
            pagination(bootstrapTableUtils, null, selectJoinStep, JOIN_TYPE);
            records = selectJoinStep.fetch();
        } else {
            SelectConditionStep<Record> selectConditionStep = create.select()
                    .from(table)
                    .where(a);
            sortCondition(bootstrapTableUtils, selectConditionStep, null, CONDITION_TYPE);
            pagination(bootstrapTableUtils, selectConditionStep, null, CONDITION_TYPE);
            records = selectConditionStep.fetch();
        }
        return records;
    }

    /**
     * 查询全部数据 with 额外条件
     *
     * @param bootstrapTableUtils bootstrapTable工具类
     * @param create              jooq create.
     * @param table               jooq table.
     * @param extraCondition      额外条件
     * @return 全部数据
     */
    public Result<Record> dataPagingQueryAllWithCondition(BootstrapTableUtils<T> bootstrapTableUtils, DSLContext create, TableLike table, Condition extraCondition) {
        Result<Record> records;
        Condition a = searchCondition(bootstrapTableUtils);
        if (ObjectUtils.isEmpty(a)) {
            SelectConditionStep<Record> selectConditionStep = create.select()
                    .from(table)
                    .where(extraCondition);
            sortCondition(bootstrapTableUtils, selectConditionStep, null, CONDITION_TYPE);
            pagination(bootstrapTableUtils, selectConditionStep, null, CONDITION_TYPE);
            records = selectConditionStep.fetch();
        } else {
            SelectConditionStep<Record> selectConditionStep = create.select()
                    .from(table)
                    .where(extraCondition.and(a));
            sortCondition(bootstrapTableUtils, selectConditionStep, null, CONDITION_TYPE);
            pagination(bootstrapTableUtils, selectConditionStep, null, CONDITION_TYPE);
            records = selectConditionStep.fetch();
        }
        return records;
    }

    /**
     * 查询全部数据 with 额外条件，无分页，做导出数据用
     *
     * @param bootstrapTableUtils bootstrapTable工具类
     * @param create              jooq create.
     * @param table               jooq table.
     * @param extraCondition      额外条件
     * @return 全部数据
     */
    public Result<Record> dataPagingQueryAllWithConditionNoPage(BootstrapTableUtils<T> bootstrapTableUtils, DSLContext create, TableLike table, Condition extraCondition) {
        Result<Record> records;
        Condition a = searchCondition(bootstrapTableUtils);
        if (ObjectUtils.isEmpty(a)) {
            SelectConditionStep<Record> selectConditionStep = create.select()
                    .from(table)
                    .where(extraCondition);
            records = selectConditionStep.fetch();
        } else {
            SelectConditionStep<Record> selectConditionStep = create.select()
                    .from(table)
                    .where(extraCondition.and(a));
            records = selectConditionStep.fetch();
        }
        return records;
    }

    /**
     * 统计全部
     *
     * @param create jooq create.
     * @param table  jooq table.
     * @return 统计
     */
    public int statisticsAll(DSLContext create, TableLike table) {
        return create.selectCount()
                .from(table)
                .fetchOne().value1();
    }

    /**
     * 统计全部 with 额外条件
     *
     * @param create         jooq create.
     * @param table          jooq table.
     * @param extraCondition 额外条件
     * @return 统计
     */
    public int statisticsAllWithCondition(DSLContext create, TableLike table, Condition extraCondition) {
        return create.selectCount()
                .from(table)
                .where(extraCondition)
                .fetchOne().value1();
    }

    /**
     * 根据条件统计
     *
     * @param bootstrapTableUtils bootstrapTable工具类
     * @param create              jooq create.
     * @param table               jooq table.
     * @return 统计
     */
    public int statisticsWithCondition(BootstrapTableUtils<T> bootstrapTableUtils, DSLContext create, TableLike table) {
        Record1<Integer> count;
        Condition a = searchCondition(bootstrapTableUtils);
        if (ObjectUtils.isEmpty(a)) {
            SelectJoinStep<Record1<Integer>> selectJoinStep = create.selectCount()
                    .from(table);
            count = selectJoinStep.fetchOne();
        } else {
            SelectConditionStep<Record1<Integer>> selectConditionStep = create.selectCount()
                    .from(table)
                    .where(a);
            count = selectConditionStep.fetchOne();
        }
        return count.value1();
    }

    /**
     * 根据条件统计
     *
     * @param bootstrapTableUtils bootstrapTable工具类
     * @param create              jooq create.
     * @param table               jooq table.
     * @param extraCondition      额外条件
     * @return 统计
     */
    public int statisticsWithCondition(BootstrapTableUtils<T> bootstrapTableUtils, DSLContext create, TableLike table, Condition extraCondition) {
        Record1<Integer> count;
        Condition a = searchCondition(bootstrapTableUtils);
        if (ObjectUtils.isEmpty(a)) {
            SelectConditionStep<Record1<Integer>> selectConditionStep = create.selectCount()
                    .from(table)
                    .where(extraCondition);
            count = selectConditionStep.fetchOne();
        } else {
            SelectConditionStep<Record1<Integer>> selectConditionStep = create.selectCount()
                    .from(table)
                    .where(extraCondition.and(a));
            count = selectConditionStep.fetchOne();
        }
        return count.value1();
    }

    /**
     * 查询条件，需要自行覆盖
     *
     * @param bootstrapTableUtils bootstrapTable工具类
     * @return 查询条件
     */
    public Condition searchCondition(BootstrapTableUtils<T> bootstrapTableUtils) {
        return null;
    }

    /**
     * 排序方式，需要自行覆盖
     *
     * @param bootstrapTableUtils bootstrapTable工具类
     * @param selectConditionStep 条件1
     * @param selectJoinStep      条件2
     * @param type                类型
     */
    public void sortCondition(BootstrapTableUtils<T> bootstrapTableUtils, SelectConditionStep<Record> selectConditionStep, SelectJoinStep<Record> selectJoinStep, int type) {
    }

    /**
     * 排序辅助,调用此方法前请先调用cleanSortParam以避免对象污染所造成的排序混乱
     *
     * @param selectConditionStep 条件1
     * @param selectJoinStep      条件2
     * @param type                类型
     */
    protected void sortToFinish(SelectConditionStep<Record> selectConditionStep, SelectJoinStep<Record> selectJoinStep, int type, SortField[] sortField) {
        if (!ObjectUtils.isEmpty(sortField)) {
            if (type == CONDITION_TYPE) {
                selectConditionStep.orderBy(sortField);
            }

            if (type == JOIN_TYPE) {
                selectJoinStep.orderBy(sortField);
            }
        }
    }

    /**
     * 分页方式
     *
     * @param bootstrapTableUtils bootstrapTable工具类
     * @param selectConditionStep 条件1
     * @param selectJoinStep      条件2
     * @param type                类型
     */
    public void pagination(BootstrapTableUtils<T> bootstrapTableUtils, SelectConditionStep<Record> selectConditionStep, SelectJoinStep<Record> selectJoinStep, int type) {
        if (bootstrapTableUtils.getPageNumber() <= 0) {
            bootstrapTableUtils.setPageNumber(1);
        }
        int length = bootstrapTableUtils.getPageSize();
        int start = (bootstrapTableUtils.getPageNumber() - 1) * length;

        if (type == CONDITION_TYPE) {
            selectConditionStep.limit(start, length);
        }

        if (type == JOIN_TYPE) {
            selectJoinStep.limit(start, length);
        }
    }
}
