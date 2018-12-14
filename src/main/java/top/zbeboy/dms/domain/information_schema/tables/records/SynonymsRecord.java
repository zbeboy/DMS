/*
 * This file is generated by jOOQ.
*/
package top.zbeboy.dms.domain.information_schema.tables.records;


import javax.annotation.Generated;
import javax.validation.constraints.Size;

import org.jooq.Field;
import org.jooq.Record9;
import org.jooq.Row9;
import org.jooq.impl.TableRecordImpl;

import top.zbeboy.dms.domain.information_schema.tables.Synonyms;


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
public class SynonymsRecord extends TableRecordImpl<SynonymsRecord> implements Record9<String, String, String, String, String, String, String, String, Integer> {

    private static final long serialVersionUID = 708458738;

    /**
     * Setter for <code>INFORMATION_SCHEMA.SYNONYMS.SYNONYM_CATALOG</code>.
     */
    public void setSynonymCatalog(String value) {
        set(0, value);
    }

    /**
     * Getter for <code>INFORMATION_SCHEMA.SYNONYMS.SYNONYM_CATALOG</code>.
     */
    @Size(max = 2147483647)
    public String getSynonymCatalog() {
        return (String) get(0);
    }

    /**
     * Setter for <code>INFORMATION_SCHEMA.SYNONYMS.SYNONYM_SCHEMA</code>.
     */
    public void setSynonymSchema(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>INFORMATION_SCHEMA.SYNONYMS.SYNONYM_SCHEMA</code>.
     */
    @Size(max = 2147483647)
    public String getSynonymSchema() {
        return (String) get(1);
    }

    /**
     * Setter for <code>INFORMATION_SCHEMA.SYNONYMS.SYNONYM_NAME</code>.
     */
    public void setSynonymName(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>INFORMATION_SCHEMA.SYNONYMS.SYNONYM_NAME</code>.
     */
    @Size(max = 2147483647)
    public String getSynonymName() {
        return (String) get(2);
    }

    /**
     * Setter for <code>INFORMATION_SCHEMA.SYNONYMS.SYNONYM_FOR</code>.
     */
    public void setSynonymFor(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>INFORMATION_SCHEMA.SYNONYMS.SYNONYM_FOR</code>.
     */
    @Size(max = 2147483647)
    public String getSynonymFor() {
        return (String) get(3);
    }

    /**
     * Setter for <code>INFORMATION_SCHEMA.SYNONYMS.SYNONYM_FOR_SCHEMA</code>.
     */
    public void setSynonymForSchema(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>INFORMATION_SCHEMA.SYNONYMS.SYNONYM_FOR_SCHEMA</code>.
     */
    @Size(max = 2147483647)
    public String getSynonymForSchema() {
        return (String) get(4);
    }

    /**
     * Setter for <code>INFORMATION_SCHEMA.SYNONYMS.TYPE_NAME</code>.
     */
    public void setTypeName(String value) {
        set(5, value);
    }

    /**
     * Getter for <code>INFORMATION_SCHEMA.SYNONYMS.TYPE_NAME</code>.
     */
    @Size(max = 2147483647)
    public String getTypeName() {
        return (String) get(5);
    }

    /**
     * Setter for <code>INFORMATION_SCHEMA.SYNONYMS.STATUS</code>.
     */
    public void setStatus(String value) {
        set(6, value);
    }

    /**
     * Getter for <code>INFORMATION_SCHEMA.SYNONYMS.STATUS</code>.
     */
    @Size(max = 2147483647)
    public String getStatus() {
        return (String) get(6);
    }

    /**
     * Setter for <code>INFORMATION_SCHEMA.SYNONYMS.REMARKS</code>.
     */
    public void setRemarks(String value) {
        set(7, value);
    }

    /**
     * Getter for <code>INFORMATION_SCHEMA.SYNONYMS.REMARKS</code>.
     */
    @Size(max = 2147483647)
    public String getRemarks() {
        return (String) get(7);
    }

    /**
     * Setter for <code>INFORMATION_SCHEMA.SYNONYMS.ID</code>.
     */
    public void setId(Integer value) {
        set(8, value);
    }

    /**
     * Getter for <code>INFORMATION_SCHEMA.SYNONYMS.ID</code>.
     */
    public Integer getId() {
        return (Integer) get(8);
    }

    // -------------------------------------------------------------------------
    // Record9 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row9<String, String, String, String, String, String, String, String, Integer> fieldsRow() {
        return (Row9) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row9<String, String, String, String, String, String, String, String, Integer> valuesRow() {
        return (Row9) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field1() {
        return Synonyms.SYNONYMS.SYNONYM_CATALOG;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field2() {
        return Synonyms.SYNONYMS.SYNONYM_SCHEMA;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field3() {
        return Synonyms.SYNONYMS.SYNONYM_NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field4() {
        return Synonyms.SYNONYMS.SYNONYM_FOR;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field5() {
        return Synonyms.SYNONYMS.SYNONYM_FOR_SCHEMA;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field6() {
        return Synonyms.SYNONYMS.TYPE_NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field7() {
        return Synonyms.SYNONYMS.STATUS;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field8() {
        return Synonyms.SYNONYMS.REMARKS;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field9() {
        return Synonyms.SYNONYMS.ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component1() {
        return getSynonymCatalog();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component2() {
        return getSynonymSchema();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component3() {
        return getSynonymName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component4() {
        return getSynonymFor();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component5() {
        return getSynonymForSchema();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component6() {
        return getTypeName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component7() {
        return getStatus();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component8() {
        return getRemarks();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component9() {
        return getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value1() {
        return getSynonymCatalog();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value2() {
        return getSynonymSchema();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value3() {
        return getSynonymName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value4() {
        return getSynonymFor();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value5() {
        return getSynonymForSchema();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value6() {
        return getTypeName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value7() {
        return getStatus();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value8() {
        return getRemarks();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value9() {
        return getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SynonymsRecord value1(String value) {
        setSynonymCatalog(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SynonymsRecord value2(String value) {
        setSynonymSchema(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SynonymsRecord value3(String value) {
        setSynonymName(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SynonymsRecord value4(String value) {
        setSynonymFor(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SynonymsRecord value5(String value) {
        setSynonymForSchema(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SynonymsRecord value6(String value) {
        setTypeName(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SynonymsRecord value7(String value) {
        setStatus(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SynonymsRecord value8(String value) {
        setRemarks(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SynonymsRecord value9(Integer value) {
        setId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SynonymsRecord values(String value1, String value2, String value3, String value4, String value5, String value6, String value7, String value8, Integer value9) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        value8(value8);
        value9(value9);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached SynonymsRecord
     */
    public SynonymsRecord() {
        super(Synonyms.SYNONYMS);
    }

    /**
     * Create a detached, initialised SynonymsRecord
     */
    public SynonymsRecord(String synonymCatalog, String synonymSchema, String synonymName, String synonymFor, String synonymForSchema, String typeName, String status, String remarks, Integer id) {
        super(Synonyms.SYNONYMS);

        set(0, synonymCatalog);
        set(1, synonymSchema);
        set(2, synonymName);
        set(3, synonymFor);
        set(4, synonymForSchema);
        set(5, typeName);
        set(6, status);
        set(7, remarks);
        set(8, id);
    }
}
