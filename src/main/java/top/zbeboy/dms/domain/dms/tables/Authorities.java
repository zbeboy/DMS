/*
 * This file is generated by jOOQ.
*/
package top.zbeboy.dms.domain.dms.tables;


import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
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
import top.zbeboy.dms.domain.dms.tables.records.AuthoritiesRecord;


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
public class Authorities extends TableImpl<AuthoritiesRecord> {

    private static final long serialVersionUID = 1164356833;

    /**
     * The reference instance of <code>DMS.AUTHORITIES</code>
     */
    public static final Authorities AUTHORITIES = new Authorities();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<AuthoritiesRecord> getRecordType() {
        return AuthoritiesRecord.class;
    }

    /**
     * The column <code>DMS.AUTHORITIES.USERNAME</code>.
     */
    public final TableField<AuthoritiesRecord, String> USERNAME = createField("USERNAME", org.jooq.impl.SQLDataType.VARCHAR(64).nullable(false), this, "");

    /**
     * The column <code>DMS.AUTHORITIES.AUTHORITY</code>.
     */
    public final TableField<AuthoritiesRecord, String> AUTHORITY = createField("AUTHORITY", org.jooq.impl.SQLDataType.VARCHAR(50).nullable(false), this, "");

    /**
     * Create a <code>DMS.AUTHORITIES</code> table reference
     */
    public Authorities() {
        this(DSL.name("AUTHORITIES"), null);
    }

    /**
     * Create an aliased <code>DMS.AUTHORITIES</code> table reference
     */
    public Authorities(String alias) {
        this(DSL.name(alias), AUTHORITIES);
    }

    /**
     * Create an aliased <code>DMS.AUTHORITIES</code> table reference
     */
    public Authorities(Name alias) {
        this(alias, AUTHORITIES);
    }

    private Authorities(Name alias, Table<AuthoritiesRecord> aliased) {
        this(alias, aliased, null);
    }

    private Authorities(Name alias, Table<AuthoritiesRecord> aliased, Field<?>[] parameters) {
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
        return Arrays.<Index>asList(Indexes.CONSTRAINT_INDEX_A, Indexes.PRIMARY_KEY_A);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<AuthoritiesRecord> getPrimaryKey() {
        return Keys.CONSTRAINT_AB;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<AuthoritiesRecord>> getKeys() {
        return Arrays.<UniqueKey<AuthoritiesRecord>>asList(Keys.CONSTRAINT_AB);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ForeignKey<AuthoritiesRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<AuthoritiesRecord, ?>>asList(Keys.CONSTRAINT_A);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Authorities as(String alias) {
        return new Authorities(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Authorities as(Name alias) {
        return new Authorities(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Authorities rename(String name) {
        return new Authorities(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Authorities rename(Name name) {
        return new Authorities(name, null);
    }
}
