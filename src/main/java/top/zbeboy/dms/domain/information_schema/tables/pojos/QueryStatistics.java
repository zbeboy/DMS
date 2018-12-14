/*
 * This file is generated by jOOQ.
*/
package top.zbeboy.dms.domain.information_schema.tables.pojos;


import java.io.Serializable;

import javax.annotation.Generated;
import javax.validation.constraints.Size;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.10.8"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class QueryStatistics implements Serializable {

    private static final long serialVersionUID = 1050622284;

    private String  sqlStatement;
    private Integer executionCount;
    private Double  minExecutionTime;
    private Double  maxExecutionTime;
    private Double  cumulativeExecutionTime;
    private Double  averageExecutionTime;
    private Double  stdDevExecutionTime;
    private Integer minRowCount;
    private Integer maxRowCount;
    private Long    cumulativeRowCount;
    private Double  averageRowCount;
    private Double  stdDevRowCount;

    public QueryStatistics() {}

    public QueryStatistics(QueryStatistics value) {
        this.sqlStatement = value.sqlStatement;
        this.executionCount = value.executionCount;
        this.minExecutionTime = value.minExecutionTime;
        this.maxExecutionTime = value.maxExecutionTime;
        this.cumulativeExecutionTime = value.cumulativeExecutionTime;
        this.averageExecutionTime = value.averageExecutionTime;
        this.stdDevExecutionTime = value.stdDevExecutionTime;
        this.minRowCount = value.minRowCount;
        this.maxRowCount = value.maxRowCount;
        this.cumulativeRowCount = value.cumulativeRowCount;
        this.averageRowCount = value.averageRowCount;
        this.stdDevRowCount = value.stdDevRowCount;
    }

    public QueryStatistics(
        String  sqlStatement,
        Integer executionCount,
        Double  minExecutionTime,
        Double  maxExecutionTime,
        Double  cumulativeExecutionTime,
        Double  averageExecutionTime,
        Double  stdDevExecutionTime,
        Integer minRowCount,
        Integer maxRowCount,
        Long    cumulativeRowCount,
        Double  averageRowCount,
        Double  stdDevRowCount
    ) {
        this.sqlStatement = sqlStatement;
        this.executionCount = executionCount;
        this.minExecutionTime = minExecutionTime;
        this.maxExecutionTime = maxExecutionTime;
        this.cumulativeExecutionTime = cumulativeExecutionTime;
        this.averageExecutionTime = averageExecutionTime;
        this.stdDevExecutionTime = stdDevExecutionTime;
        this.minRowCount = minRowCount;
        this.maxRowCount = maxRowCount;
        this.cumulativeRowCount = cumulativeRowCount;
        this.averageRowCount = averageRowCount;
        this.stdDevRowCount = stdDevRowCount;
    }

    @Size(max = 2147483647)
    public String getSqlStatement() {
        return this.sqlStatement;
    }

    public void setSqlStatement(String sqlStatement) {
        this.sqlStatement = sqlStatement;
    }

    public Integer getExecutionCount() {
        return this.executionCount;
    }

    public void setExecutionCount(Integer executionCount) {
        this.executionCount = executionCount;
    }

    public Double getMinExecutionTime() {
        return this.minExecutionTime;
    }

    public void setMinExecutionTime(Double minExecutionTime) {
        this.minExecutionTime = minExecutionTime;
    }

    public Double getMaxExecutionTime() {
        return this.maxExecutionTime;
    }

    public void setMaxExecutionTime(Double maxExecutionTime) {
        this.maxExecutionTime = maxExecutionTime;
    }

    public Double getCumulativeExecutionTime() {
        return this.cumulativeExecutionTime;
    }

    public void setCumulativeExecutionTime(Double cumulativeExecutionTime) {
        this.cumulativeExecutionTime = cumulativeExecutionTime;
    }

    public Double getAverageExecutionTime() {
        return this.averageExecutionTime;
    }

    public void setAverageExecutionTime(Double averageExecutionTime) {
        this.averageExecutionTime = averageExecutionTime;
    }

    public Double getStdDevExecutionTime() {
        return this.stdDevExecutionTime;
    }

    public void setStdDevExecutionTime(Double stdDevExecutionTime) {
        this.stdDevExecutionTime = stdDevExecutionTime;
    }

    public Integer getMinRowCount() {
        return this.minRowCount;
    }

    public void setMinRowCount(Integer minRowCount) {
        this.minRowCount = minRowCount;
    }

    public Integer getMaxRowCount() {
        return this.maxRowCount;
    }

    public void setMaxRowCount(Integer maxRowCount) {
        this.maxRowCount = maxRowCount;
    }

    public Long getCumulativeRowCount() {
        return this.cumulativeRowCount;
    }

    public void setCumulativeRowCount(Long cumulativeRowCount) {
        this.cumulativeRowCount = cumulativeRowCount;
    }

    public Double getAverageRowCount() {
        return this.averageRowCount;
    }

    public void setAverageRowCount(Double averageRowCount) {
        this.averageRowCount = averageRowCount;
    }

    public Double getStdDevRowCount() {
        return this.stdDevRowCount;
    }

    public void setStdDevRowCount(Double stdDevRowCount) {
        this.stdDevRowCount = stdDevRowCount;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("QueryStatistics (");

        sb.append(sqlStatement);
        sb.append(", ").append(executionCount);
        sb.append(", ").append(minExecutionTime);
        sb.append(", ").append(maxExecutionTime);
        sb.append(", ").append(cumulativeExecutionTime);
        sb.append(", ").append(averageExecutionTime);
        sb.append(", ").append(stdDevExecutionTime);
        sb.append(", ").append(minRowCount);
        sb.append(", ").append(maxRowCount);
        sb.append(", ").append(cumulativeRowCount);
        sb.append(", ").append(averageRowCount);
        sb.append(", ").append(stdDevRowCount);

        sb.append(")");
        return sb.toString();
    }
}
