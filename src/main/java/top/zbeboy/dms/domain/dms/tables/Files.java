/*
 * This file is generated by jOOQ.
*/
package top.zbeboy.dms.domain.dms.tables;


import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
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
import top.zbeboy.dms.domain.dms.tables.records.FilesRecord;


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
public class Files extends TableImpl<FilesRecord> {

    private static final long serialVersionUID = -1565774984;

    /**
     * The reference instance of <code>DMS.FILES</code>
     */
    public static final Files FILES = new Files();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<FilesRecord> getRecordType() {
        return FilesRecord.class;
    }

    /**
     * The column <code>DMS.FILES.FILE_ID</code>.
     */
    public final TableField<FilesRecord, String> FILE_ID = createField("FILE_ID", org.jooq.impl.SQLDataType.VARCHAR(64).nullable(false), this, "");

    /**
     * The column <code>DMS.FILES.FILE_SIZE</code>.
     */
    public final TableField<FilesRecord, Long> FILE_SIZE = createField("FILE_SIZE", org.jooq.impl.SQLDataType.BIGINT, this, "");

    /**
     * The column <code>DMS.FILES.CONTENT_TYPE</code>.
     */
    public final TableField<FilesRecord, String> CONTENT_TYPE = createField("CONTENT_TYPE", org.jooq.impl.SQLDataType.VARCHAR(20), this, "");

    /**
     * The column <code>DMS.FILES.ORIGINAL_FILE_NAME</code>.
     */
    public final TableField<FilesRecord, String> ORIGINAL_FILE_NAME = createField("ORIGINAL_FILE_NAME", org.jooq.impl.SQLDataType.VARCHAR(300), this, "");

    /**
     * The column <code>DMS.FILES.NEW_NAME</code>.
     */
    public final TableField<FilesRecord, String> NEW_NAME = createField("NEW_NAME", org.jooq.impl.SQLDataType.VARCHAR(300), this, "");

    /**
     * The column <code>DMS.FILES.RELATIVE_PATH</code>.
     */
    public final TableField<FilesRecord, String> RELATIVE_PATH = createField("RELATIVE_PATH", org.jooq.impl.SQLDataType.VARCHAR(800), this, "");

    /**
     * The column <code>DMS.FILES.EXT</code>.
     */
    public final TableField<FilesRecord, String> EXT = createField("EXT", org.jooq.impl.SQLDataType.VARCHAR(20), this, "");

    /**
     * Create a <code>DMS.FILES</code> table reference
     */
    public Files() {
        this(DSL.name("FILES"), null);
    }

    /**
     * Create an aliased <code>DMS.FILES</code> table reference
     */
    public Files(String alias) {
        this(DSL.name(alias), FILES);
    }

    /**
     * Create an aliased <code>DMS.FILES</code> table reference
     */
    public Files(Name alias) {
        this(alias, FILES);
    }

    private Files(Name alias, Table<FilesRecord> aliased) {
        this(alias, aliased, null);
    }

    private Files(Name alias, Table<FilesRecord> aliased, Field<?>[] parameters) {
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
        return Arrays.<Index>asList(Indexes.PRIMARY_KEY_3);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<FilesRecord> getPrimaryKey() {
        return Keys.CONSTRAINT_3;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<FilesRecord>> getKeys() {
        return Arrays.<UniqueKey<FilesRecord>>asList(Keys.CONSTRAINT_3);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Files as(String alias) {
        return new Files(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Files as(Name alias) {
        return new Files(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Files rename(String name) {
        return new Files(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Files rename(Name name) {
        return new Files(name, null);
    }
}
