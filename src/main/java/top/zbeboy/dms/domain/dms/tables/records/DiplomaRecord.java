/*
 * This file is generated by jOOQ.
*/
package top.zbeboy.dms.domain.dms.tables.records;


import javax.annotation.Generated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record3;
import org.jooq.Row3;
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
public class DiplomaRecord extends UpdatableRecordImpl<DiplomaRecord> implements Record3<Integer, Integer, String> {

    private static final long serialVersionUID = -1625760324;

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
     * Setter for <code>DMS.DIPLOMA.CREDIT_ID</code>.
     */
    public void setCreditId(Integer value) {
        set(1, value);
    }

    /**
     * Getter for <code>DMS.DIPLOMA.CREDIT_ID</code>.
     */
    @NotNull
    public Integer getCreditId() {
        return (Integer) get(1);
    }

    /**
     * Setter for <code>DMS.DIPLOMA.DIPLOMA_NAME</code>.
     */
    public void setDiplomaName(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>DMS.DIPLOMA.DIPLOMA_NAME</code>.
     */
    @NotNull
    @Size(max = 500)
    public String getDiplomaName() {
        return (String) get(2);
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
    // Record3 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row3<Integer, Integer, String> fieldsRow() {
        return (Row3) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row3<Integer, Integer, String> valuesRow() {
        return (Row3) super.valuesRow();
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
    public Field<Integer> field2() {
        return Diploma.DIPLOMA.CREDIT_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field3() {
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
    public Integer component2() {
        return getCreditId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component3() {
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
    public Integer value2() {
        return getCreditId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value3() {
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
    public DiplomaRecord value2(Integer value) {
        setCreditId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DiplomaRecord value3(String value) {
        setDiplomaName(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DiplomaRecord values(Integer value1, Integer value2, String value3) {
        value1(value1);
        value2(value2);
        value3(value3);
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
    public DiplomaRecord(Integer diplomaId, Integer creditId, String diplomaName) {
        super(Diploma.DIPLOMA);

        set(0, diplomaId);
        set(1, creditId);
        set(2, diplomaName);
    }
}
