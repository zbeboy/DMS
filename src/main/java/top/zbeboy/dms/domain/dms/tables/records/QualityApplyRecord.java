/*
 * This file is generated by jOOQ.
*/
package top.zbeboy.dms.domain.dms.tables.records;


import java.sql.Timestamp;

import javax.annotation.Generated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record6;
import org.jooq.Row6;
import org.jooq.impl.UpdatableRecordImpl;

import top.zbeboy.dms.domain.dms.tables.QualityApply;


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
public class QualityApplyRecord extends UpdatableRecordImpl<QualityApplyRecord> implements Record6<String, Integer, String, Integer, Timestamp, String> {

    private static final long serialVersionUID = -563555977;

    /**
     * Setter for <code>DMS.QUALITY_APPLY.QUALITY_APPLY_ID</code>.
     */
    public void setQualityApplyId(String value) {
        set(0, value);
    }

    /**
     * Getter for <code>DMS.QUALITY_APPLY.QUALITY_APPLY_ID</code>.
     */
    @NotNull
    @Size(max = 64)
    public String getQualityApplyId() {
        return (String) get(0);
    }

    /**
     * Setter for <code>DMS.QUALITY_APPLY.STUDENT_ID</code>.
     */
    public void setStudentId(Integer value) {
        set(1, value);
    }

    /**
     * Getter for <code>DMS.QUALITY_APPLY.STUDENT_ID</code>.
     */
    @NotNull
    public Integer getStudentId() {
        return (Integer) get(1);
    }

    /**
     * Setter for <code>DMS.QUALITY_APPLY.APPLY_CONTENT</code>.
     */
    public void setApplyContent(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>DMS.QUALITY_APPLY.APPLY_CONTENT</code>.
     */
    @Size(max = 100)
    public String getApplyContent() {
        return (String) get(2);
    }

    /**
     * Setter for <code>DMS.QUALITY_APPLY.APPLY_STATE</code>.
     */
    public void setApplyState(Integer value) {
        set(3, value);
    }

    /**
     * Getter for <code>DMS.QUALITY_APPLY.APPLY_STATE</code>.
     */
    public Integer getApplyState() {
        return (Integer) get(3);
    }

    /**
     * Setter for <code>DMS.QUALITY_APPLY.APPLY_DATE</code>.
     */
    public void setApplyDate(Timestamp value) {
        set(4, value);
    }

    /**
     * Getter for <code>DMS.QUALITY_APPLY.APPLY_DATE</code>.
     */
    @NotNull
    public Timestamp getApplyDate() {
        return (Timestamp) get(4);
    }

    /**
     * Setter for <code>DMS.QUALITY_APPLY.QUALITY_RELEASE_ID</code>.
     */
    public void setQualityReleaseId(String value) {
        set(5, value);
    }

    /**
     * Getter for <code>DMS.QUALITY_APPLY.QUALITY_RELEASE_ID</code>.
     */
    @NotNull
    @Size(max = 64)
    public String getQualityReleaseId() {
        return (String) get(5);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Record1<String> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record6 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row6<String, Integer, String, Integer, Timestamp, String> fieldsRow() {
        return (Row6) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row6<String, Integer, String, Integer, Timestamp, String> valuesRow() {
        return (Row6) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field1() {
        return QualityApply.QUALITY_APPLY.QUALITY_APPLY_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field2() {
        return QualityApply.QUALITY_APPLY.STUDENT_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field3() {
        return QualityApply.QUALITY_APPLY.APPLY_CONTENT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field4() {
        return QualityApply.QUALITY_APPLY.APPLY_STATE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Timestamp> field5() {
        return QualityApply.QUALITY_APPLY.APPLY_DATE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field6() {
        return QualityApply.QUALITY_APPLY.QUALITY_RELEASE_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component1() {
        return getQualityApplyId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component2() {
        return getStudentId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component3() {
        return getApplyContent();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component4() {
        return getApplyState();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp component5() {
        return getApplyDate();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component6() {
        return getQualityReleaseId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value1() {
        return getQualityApplyId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value2() {
        return getStudentId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value3() {
        return getApplyContent();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value4() {
        return getApplyState();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp value5() {
        return getApplyDate();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value6() {
        return getQualityReleaseId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public QualityApplyRecord value1(String value) {
        setQualityApplyId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public QualityApplyRecord value2(Integer value) {
        setStudentId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public QualityApplyRecord value3(String value) {
        setApplyContent(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public QualityApplyRecord value4(Integer value) {
        setApplyState(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public QualityApplyRecord value5(Timestamp value) {
        setApplyDate(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public QualityApplyRecord value6(String value) {
        setQualityReleaseId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public QualityApplyRecord values(String value1, Integer value2, String value3, Integer value4, Timestamp value5, String value6) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached QualityApplyRecord
     */
    public QualityApplyRecord() {
        super(QualityApply.QUALITY_APPLY);
    }

    /**
     * Create a detached, initialised QualityApplyRecord
     */
    public QualityApplyRecord(String qualityApplyId, Integer studentId, String applyContent, Integer applyState, Timestamp applyDate, String qualityReleaseId) {
        super(QualityApply.QUALITY_APPLY);

        set(0, qualityApplyId);
        set(1, studentId);
        set(2, applyContent);
        set(3, applyState);
        set(4, applyDate);
        set(5, qualityReleaseId);
    }
}
