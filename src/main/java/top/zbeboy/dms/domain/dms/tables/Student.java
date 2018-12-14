/*
 * This file is generated by jOOQ.
*/
package top.zbeboy.dms.domain.dms.tables;


import java.sql.Date;
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
import top.zbeboy.dms.domain.dms.tables.records.StudentRecord;


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
public class Student extends TableImpl<StudentRecord> {

    private static final long serialVersionUID = -35430644;

    /**
     * The reference instance of <code>DMS.STUDENT</code>
     */
    public static final Student STUDENT = new Student();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<StudentRecord> getRecordType() {
        return StudentRecord.class;
    }

    /**
     * The column <code>DMS.STUDENT.STUDENT_ID</code>.
     */
    public final TableField<StudentRecord, Integer> STUDENT_ID = createField("STUDENT_ID", org.jooq.impl.SQLDataType.INTEGER.nullable(false).identity(true), this, "");

    /**
     * The column <code>DMS.STUDENT.STUDENT_NUMBER</code>.
     */
    public final TableField<StudentRecord, String> STUDENT_NUMBER = createField("STUDENT_NUMBER", org.jooq.impl.SQLDataType.VARCHAR(20).nullable(false), this, "");

    /**
     * The column <code>DMS.STUDENT.BIRTHDAY</code>.
     */
    public final TableField<StudentRecord, Date> BIRTHDAY = createField("BIRTHDAY", org.jooq.impl.SQLDataType.DATE, this, "");

    /**
     * The column <code>DMS.STUDENT.SEX</code>.
     */
    public final TableField<StudentRecord, String> SEX = createField("SEX", org.jooq.impl.SQLDataType.VARCHAR(2), this, "");

    /**
     * The column <code>DMS.STUDENT.FAMILY_RESIDENCE</code>.
     */
    public final TableField<StudentRecord, String> FAMILY_RESIDENCE = createField("FAMILY_RESIDENCE", org.jooq.impl.SQLDataType.VARCHAR(200), this, "");

    /**
     * The column <code>DMS.STUDENT.POLITICAL_LANDSCAPE_ID</code>.
     */
    public final TableField<StudentRecord, Integer> POLITICAL_LANDSCAPE_ID = createField("POLITICAL_LANDSCAPE_ID", org.jooq.impl.SQLDataType.INTEGER, this, "");

    /**
     * The column <code>DMS.STUDENT.NATION_ID</code>.
     */
    public final TableField<StudentRecord, Integer> NATION_ID = createField("NATION_ID", org.jooq.impl.SQLDataType.INTEGER, this, "");

    /**
     * The column <code>DMS.STUDENT.DORMITORY_NUMBER</code>.
     */
    public final TableField<StudentRecord, String> DORMITORY_NUMBER = createField("DORMITORY_NUMBER", org.jooq.impl.SQLDataType.VARCHAR(15), this, "");

    /**
     * The column <code>DMS.STUDENT.PARENT_NAME</code>.
     */
    public final TableField<StudentRecord, String> PARENT_NAME = createField("PARENT_NAME", org.jooq.impl.SQLDataType.VARCHAR(10), this, "");

    /**
     * The column <code>DMS.STUDENT.PARENT_CONTACT_PHONE</code>.
     */
    public final TableField<StudentRecord, String> PARENT_CONTACT_PHONE = createField("PARENT_CONTACT_PHONE", org.jooq.impl.SQLDataType.VARCHAR(15), this, "");

    /**
     * The column <code>DMS.STUDENT.PLACE_ORIGIN</code>.
     */
    public final TableField<StudentRecord, String> PLACE_ORIGIN = createField("PLACE_ORIGIN", org.jooq.impl.SQLDataType.VARCHAR(200), this, "");

    /**
     * The column <code>DMS.STUDENT.ORGANIZE_ID</code>.
     */
    public final TableField<StudentRecord, Integer> ORGANIZE_ID = createField("ORGANIZE_ID", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>DMS.STUDENT.USERNAME</code>.
     */
    public final TableField<StudentRecord, String> USERNAME = createField("USERNAME", org.jooq.impl.SQLDataType.VARCHAR(64).nullable(false), this, "");

    /**
     * Create a <code>DMS.STUDENT</code> table reference
     */
    public Student() {
        this(DSL.name("STUDENT"), null);
    }

    /**
     * Create an aliased <code>DMS.STUDENT</code> table reference
     */
    public Student(String alias) {
        this(DSL.name(alias), STUDENT);
    }

    /**
     * Create an aliased <code>DMS.STUDENT</code> table reference
     */
    public Student(Name alias) {
        this(alias, STUDENT);
    }

    private Student(Name alias, Table<StudentRecord> aliased) {
        this(alias, aliased, null);
    }

    private Student(Name alias, Table<StudentRecord> aliased, Field<?>[] parameters) {
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
        return Arrays.<Index>asList(Indexes.CONSTRAINT_INDEX_BA, Indexes.CONSTRAINT_INDEX_BAC, Indexes.CONSTRAINT_INDEX_BACA, Indexes.PRIMARY_KEY_BA);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Identity<StudentRecord, Integer> getIdentity() {
        return Keys.IDENTITY_STUDENT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<StudentRecord> getPrimaryKey() {
        return Keys.CONSTRAINT_BA;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<StudentRecord>> getKeys() {
        return Arrays.<UniqueKey<StudentRecord>>asList(Keys.CONSTRAINT_BA, Keys.CONSTRAINT_BAC);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ForeignKey<StudentRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<StudentRecord, ?>>asList(Keys.CONSTRAINT_BACA, Keys.CONSTRAINT_BACA0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Student as(String alias) {
        return new Student(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Student as(Name alias) {
        return new Student(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Student rename(String name) {
        return new Student(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Student rename(Name name) {
        return new Student(name, null);
    }
}
