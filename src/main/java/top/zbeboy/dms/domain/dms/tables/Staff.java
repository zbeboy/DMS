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
import top.zbeboy.dms.domain.dms.tables.records.StaffRecord;


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
public class Staff extends TableImpl<StaffRecord> {

    private static final long serialVersionUID = -1081508555;

    /**
     * The reference instance of <code>DMS.STAFF</code>
     */
    public static final Staff STAFF = new Staff();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<StaffRecord> getRecordType() {
        return StaffRecord.class;
    }

    /**
     * The column <code>DMS.STAFF.STAFF_ID</code>.
     */
    public final TableField<StaffRecord, Integer> STAFF_ID = createField("STAFF_ID", org.jooq.impl.SQLDataType.INTEGER.nullable(false).identity(true), this, "");

    /**
     * The column <code>DMS.STAFF.STAFF_NUMBER</code>.
     */
    public final TableField<StaffRecord, String> STAFF_NUMBER = createField("STAFF_NUMBER", org.jooq.impl.SQLDataType.VARCHAR(20).nullable(false), this, "");

    /**
     * The column <code>DMS.STAFF.SEX</code>.
     */
    public final TableField<StaffRecord, String> SEX = createField("SEX", org.jooq.impl.SQLDataType.VARCHAR(2), this, "");

    /**
     * The column <code>DMS.STAFF.POLITICAL_LANDSCAPE_ID</code>.
     */
    public final TableField<StaffRecord, Integer> POLITICAL_LANDSCAPE_ID = createField("POLITICAL_LANDSCAPE_ID", org.jooq.impl.SQLDataType.INTEGER, this, "");

    /**
     * The column <code>DMS.STAFF.DEPARTMENT_ID</code>.
     */
    public final TableField<StaffRecord, Integer> DEPARTMENT_ID = createField("DEPARTMENT_ID", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>DMS.STAFF.USERNAME</code>.
     */
    public final TableField<StaffRecord, String> USERNAME = createField("USERNAME", org.jooq.impl.SQLDataType.VARCHAR(64).nullable(false), this, "");

    /**
     * Create a <code>DMS.STAFF</code> table reference
     */
    public Staff() {
        this(DSL.name("STAFF"), null);
    }

    /**
     * Create an aliased <code>DMS.STAFF</code> table reference
     */
    public Staff(String alias) {
        this(DSL.name(alias), STAFF);
    }

    /**
     * Create an aliased <code>DMS.STAFF</code> table reference
     */
    public Staff(Name alias) {
        this(alias, STAFF);
    }

    private Staff(Name alias, Table<StaffRecord> aliased) {
        this(alias, aliased, null);
    }

    private Staff(Name alias, Table<StaffRecord> aliased, Field<?>[] parameters) {
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
        return Arrays.<Index>asList(Indexes.CONSTRAINT_INDEX_4B, Indexes.CONSTRAINT_INDEX_4B8, Indexes.CONSTRAINT_INDEX_4B8C, Indexes.PRIMARY_KEY_4B);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Identity<StaffRecord, Integer> getIdentity() {
        return Keys.IDENTITY_STAFF;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<StaffRecord> getPrimaryKey() {
        return Keys.CONSTRAINT_4B;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<StaffRecord>> getKeys() {
        return Arrays.<UniqueKey<StaffRecord>>asList(Keys.CONSTRAINT_4B, Keys.CONSTRAINT_4B8);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ForeignKey<StaffRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<StaffRecord, ?>>asList(Keys.CONSTRAINT_4B8C, Keys.CONSTRAINT_4B8CA);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Staff as(String alias) {
        return new Staff(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Staff as(Name alias) {
        return new Staff(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Staff rename(String name) {
        return new Staff(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Staff rename(Name name) {
        return new Staff(name, null);
    }
}
