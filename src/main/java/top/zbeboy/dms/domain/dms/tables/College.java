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
import top.zbeboy.dms.domain.dms.tables.records.CollegeRecord;


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
public class College extends TableImpl<CollegeRecord> {

    private static final long serialVersionUID = -1059710457;

    /**
     * The reference instance of <code>DMS.COLLEGE</code>
     */
    public static final College COLLEGE = new College();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<CollegeRecord> getRecordType() {
        return CollegeRecord.class;
    }

    /**
     * The column <code>DMS.COLLEGE.COLLEGE_ID</code>.
     */
    public final TableField<CollegeRecord, Integer> COLLEGE_ID = createField("COLLEGE_ID", org.jooq.impl.SQLDataType.INTEGER.nullable(false).identity(true), this, "");

    /**
     * The column <code>DMS.COLLEGE.COLLEGE_NAME</code>.
     */
    public final TableField<CollegeRecord, String> COLLEGE_NAME = createField("COLLEGE_NAME", org.jooq.impl.SQLDataType.VARCHAR(200).nullable(false), this, "");

    /**
     * The column <code>DMS.COLLEGE.COLLEGE_ADDRESS</code>.
     */
    public final TableField<CollegeRecord, String> COLLEGE_ADDRESS = createField("COLLEGE_ADDRESS", org.jooq.impl.SQLDataType.VARCHAR(500).nullable(false), this, "");

    /**
     * The column <code>DMS.COLLEGE.SCHOOL_ID</code>.
     */
    public final TableField<CollegeRecord, Integer> SCHOOL_ID = createField("SCHOOL_ID", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>DMS.COLLEGE.COLLEGE_IS_DEL</code>.
     */
    public final TableField<CollegeRecord, Boolean> COLLEGE_IS_DEL = createField("COLLEGE_IS_DEL", org.jooq.impl.SQLDataType.BOOLEAN, this, "");

    /**
     * Create a <code>DMS.COLLEGE</code> table reference
     */
    public College() {
        this(DSL.name("COLLEGE"), null);
    }

    /**
     * Create an aliased <code>DMS.COLLEGE</code> table reference
     */
    public College(String alias) {
        this(DSL.name(alias), COLLEGE);
    }

    /**
     * Create an aliased <code>DMS.COLLEGE</code> table reference
     */
    public College(Name alias) {
        this(alias, COLLEGE);
    }

    private College(Name alias, Table<CollegeRecord> aliased) {
        this(alias, aliased, null);
    }

    private College(Name alias, Table<CollegeRecord> aliased, Field<?>[] parameters) {
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
        return Arrays.<Index>asList(Indexes.CONSTRAINT_INDEX_6, Indexes.PRIMARY_KEY_63);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Identity<CollegeRecord, Integer> getIdentity() {
        return Keys.IDENTITY_COLLEGE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<CollegeRecord> getPrimaryKey() {
        return Keys.CONSTRAINT_6;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<CollegeRecord>> getKeys() {
        return Arrays.<UniqueKey<CollegeRecord>>asList(Keys.CONSTRAINT_6);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ForeignKey<CollegeRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<CollegeRecord, ?>>asList(Keys.CONSTRAINT_63);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public College as(String alias) {
        return new College(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public College as(Name alias) {
        return new College(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public College rename(String name) {
        return new College(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public College rename(Name name) {
        return new College(name, null);
    }
}
