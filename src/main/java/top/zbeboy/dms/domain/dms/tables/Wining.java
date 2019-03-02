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
import top.zbeboy.dms.domain.dms.tables.records.WiningRecord;


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
public class Wining extends TableImpl<WiningRecord> {

    private static final long serialVersionUID = 839440080;

    /**
     * The reference instance of <code>DMS.WINING</code>
     */
    public static final Wining WINING = new Wining();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<WiningRecord> getRecordType() {
        return WiningRecord.class;
    }

    /**
     * The column <code>DMS.WINING.WINING_ID</code>.
     */
    public final TableField<WiningRecord, Integer> WINING_ID = createField("WINING_ID", org.jooq.impl.SQLDataType.INTEGER.nullable(false).identity(true), this, "");

    /**
     * The column <code>DMS.WINING.CREDIT_ID</code>.
     */
    public final TableField<WiningRecord, Integer> CREDIT_ID = createField("CREDIT_ID", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>DMS.WINING.WINING_CONTENT</code>.
     */
    public final TableField<WiningRecord, String> WINING_CONTENT = createField("WINING_CONTENT", org.jooq.impl.SQLDataType.VARCHAR(500).nullable(false), this, "");

    /**
     * The column <code>DMS.WINING.WINING_SCORE</code>.
     */
    public final TableField<WiningRecord, Double> WINING_SCORE = createField("WINING_SCORE", org.jooq.impl.SQLDataType.DOUBLE, this, "");

    /**
     * Create a <code>DMS.WINING</code> table reference
     */
    public Wining() {
        this(DSL.name("WINING"), null);
    }

    /**
     * Create an aliased <code>DMS.WINING</code> table reference
     */
    public Wining(String alias) {
        this(DSL.name(alias), WINING);
    }

    /**
     * Create an aliased <code>DMS.WINING</code> table reference
     */
    public Wining(Name alias) {
        this(alias, WINING);
    }

    private Wining(Name alias, Table<WiningRecord> aliased) {
        this(alias, aliased, null);
    }

    private Wining(Name alias, Table<WiningRecord> aliased, Field<?>[] parameters) {
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
        return Arrays.<Index>asList(Indexes.CONSTRAINT_INDEX_98, Indexes.PRIMARY_KEY_98);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Identity<WiningRecord, Integer> getIdentity() {
        return Keys.IDENTITY_WINING;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<WiningRecord> getPrimaryKey() {
        return Keys.CONSTRAINT_98;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<WiningRecord>> getKeys() {
        return Arrays.<UniqueKey<WiningRecord>>asList(Keys.CONSTRAINT_98);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ForeignKey<WiningRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<WiningRecord, ?>>asList(Keys.CONSTRAINT_989);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Wining as(String alias) {
        return new Wining(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Wining as(Name alias) {
        return new Wining(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Wining rename(String name) {
        return new Wining(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Wining rename(Name name) {
        return new Wining(name, null);
    }
}
