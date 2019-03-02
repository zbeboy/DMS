/*
 * This file is generated by jOOQ.
*/
package top.zbeboy.dms.domain.dms.tables.records;


import javax.annotation.Generated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record5;
import org.jooq.Row5;
import org.jooq.impl.UpdatableRecordImpl;

import top.zbeboy.dms.domain.dms.tables.Diploma;


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
public class DiplomaRecord extends UpdatableRecordImpl<DiplomaRecord> implements Record5<Integer, String, String, Integer, String> {

    private static final long serialVersionUID = -657428465;

    /**
     * Setter for <code>DMS.DIPLOMA.DIPLOMA_ID</code>.
     */
    public void setDiplomaId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>DMS.DIPLOMA.DIPLOMA_ID</code>.
     */
    public Integer getDiplomaId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>DMS.DIPLOMA.STUDENT_NUMBER</code>.
     */
    public void setStudentNumber(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>DMS.DIPLOMA.STUDENT_NUMBER</code>.
     */
    @NotNull
    @Size(max = 20)
    public String getStudentNumber() {
        return (String) get(1);
    }

    /**
     * Setter for <code>DMS.DIPLOMA.YEAR</code>.
     */
    public void setYear(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>DMS.DIPLOMA.YEAR</code>.
     */
    @NotNull
    @Size(max = 5)
    public String getYear() {
        return (String) get(2);
    }

    /**
     * Setter for <code>DMS.DIPLOMA.TERM</code>.
     */
    public void setTerm(Integer value) {
        set(3, value);
    }

    /**
     * Getter for <code>DMS.DIPLOMA.TERM</code>.
     */
    @NotNull
    public Integer getTerm() {
        return (Integer) get(3);
    }

    /**
     * Setter for <code>DMS.DIPLOMA.DIPLOMA_NAME</code>.
     */
    public void setDiplomaName(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>DMS.DIPLOMA.DIPLOMA_NAME</code>.
     */
    @NotNull
    @Size(max = 500)
    public String getDiplomaName() {
        return (String) get(4);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record5 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row5<Integer, String, String, Integer, String> fieldsRow() {
        return (Row5) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row5<Integer, String, String, Integer, String> valuesRow() {
        return (Row5) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field1() {
        return Diploma.DIPLOMA.DIPLOMA_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field2() {
        return Diploma.DIPLOMA.STUDENT_NUMBER;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field3() {
        return Diploma.DIPLOMA.YEAR;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field4() {
        return Diploma.DIPLOMA.TERM;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field5() {
        return Diploma.DIPLOMA.DIPLOMA_NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component1() {
        return getDiplomaId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component2() {
        return getStudentNumber();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component3() {
        return getYear();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component4() {
        return getTerm();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component5() {
        return getDiplomaName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value1() {
        return getDiplomaId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value2() {
        return getStudentNumber();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value3() {
        return getYear();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value4() {
        return getTerm();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value5() {
        return getDiplomaName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DiplomaRecord value1(Integer value) {
        setDiplomaId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DiplomaRecord value2(String value) {
        setStudentNumber(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DiplomaRecord value3(String value) {
        setYear(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DiplomaRecord value4(Integer value) {
        setTerm(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DiplomaRecord value5(String value) {
        setDiplomaName(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DiplomaRecord values(Integer value1, String value2, String value3, Integer value4, String value5) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached DiplomaRecord
     */
    public DiplomaRecord() {
        super(Diploma.DIPLOMA);
    }

    /**
     * Create a detached, initialised DiplomaRecord
     */
    public DiplomaRecord(Integer diplomaId, String studentNumber, String year, Integer term, String diplomaName) {
        super(Diploma.DIPLOMA);

        set(0, diplomaId);
        set(1, studentNumber);
        set(2, year);
        set(3, term);
        set(4, diplomaName);
    }
}
