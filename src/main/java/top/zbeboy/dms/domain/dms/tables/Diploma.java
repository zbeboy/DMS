/*
 * This file is generated by jOOQ.
*/
package top.zbeboy.dms.domain.dms.tables;


import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;

import top.zbeboy.dms.domain.dms.Dms;
import top.zbeboy.dms.domain.dms.Indexes;
import top.zbeboy.dms.domain.dms.Keys;
import top.zbeboy.dms.domain.dms.tables.records.DiplomaRecord;


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
public class Diploma extends TableImpl<DiplomaRecord> {

    private static final long serialVersionUID = -1276620002;

    /**
     * The reference instance of <code>DMS.DIPLOMA</code>
     */
    public static final Diploma DIPLOMA = new Diploma();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<DiplomaRecord> getRecordType() {
        return DiplomaRecord.class;
    }

    /**
     * The column <code>DMS.DIPLOMA.DIPLOMA_ID</code>.
     */
    public final TableField<DiplomaRecord, Integer> DIPLOMA_ID = createField("DIPLOMA_ID", org.jooq.impl.SQLDataType.INTEGER.nullable(false).identity(true), this, "");

    /**
     * The column <code>DMS.DIPLOMA.CREDIT_ID</code>.
     */
    public final TableField<DiplomaRecord, Integer> CREDIT_ID = createField("CREDIT_ID", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>DMS.DIPLOMA.DIPLOMA_NAME</code>.
     */
    public final TableField<DiplomaRecord, String> DIPLOMA_NAME = createField("DIPLOMA_NAME", org.jooq.impl.SQLDataType.VARCHAR(500).nullable(false), this, "");

    /**
     * Create a <code>DMS.DIPLOMA</code> table reference
     */
    public Diploma() {
        this(DSL.name("DIPLOMA"), null);
    }

    /**
     * Create an aliased <code>DMS.DIPLOMA</code> table reference
     */
    public Diploma(String alias) {
        this(DSL.name(alias), DIPLOMA);
    }

    /**
     * Create an aliased <code>DMS.DIPLOMA</code> table reference
     */
    public Diploma(Name alias) {
        this(alias, DIPLOMA);
    }

    private Diploma(Name alias, Table<DiplomaRecord> aliased) {
        this(alias, aliased, null);
    }

    private Diploma(Name alias, Table<DiplomaRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schema getSchema() {
        return Dms.DMS;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.CONSTRAINT_INDEX_8, Indexes.PRIMARY_KEY_8);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Identity<DiplomaRecord, Integer> getIdentity() {
        return Keys.IDENTITY_DIPLOMA;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<DiplomaRecord> getPrimaryKey() {
        return Keys.CONSTRAINT_8;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<DiplomaRecord>> getKeys() {
        return Arrays.<UniqueKey<DiplomaRecord>>asList(Keys.CONSTRAINT_8);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ForeignKey<DiplomaRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<DiplomaRecord, ?>>asList(Keys.CONSTRAINT_8E);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Diploma as(String alias) {
        return new Diploma(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Diploma as(Name alias) {
        return new Diploma(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Diploma rename(String name) {
        return new Diploma(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Diploma rename(Name name) {
        return new Diploma(name, null);
    }
}
