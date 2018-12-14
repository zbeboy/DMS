/*
 * This file is generated by jOOQ.
*/
package top.zbeboy.dms.domain.information_schema.tables.pojos;


import java.io.Serializable;

import javax.annotation.Generated;
import javax.validation.constraints.Size;


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
public class Columns implements Serializable {

    private static final long serialVersionUID = 256386656;

    private String  tableCatalog;
    private String  tableSchema;
    private String  tableName;
    private String  columnName;
    private Integer ordinalPosition;
    private String  columnDefault;
    private String  isNullable;
    private Integer dataType;
    private Integer characterMaximumLength;
    private Integer characterOctetLength;
    private Integer numericPrecision;
    private Integer numericPrecisionRadix;
    private Integer numericScale;
    private String  characterSetName;
    private String  collationName;
    private String  typeName;
    private Integer nullable;
    private Boolean isComputed;
    private Integer selectivity;
    private String  checkConstraint;
    private String  sequenceName;
    private String  remarks;
    private Short   sourceDataType;
    private String  columnType;
    private String  columnOnUpdate;

    public Columns() {}

    public Columns(Columns value) {
        this.tableCatalog = value.tableCatalog;
        this.tableSchema = value.tableSchema;
        this.tableName = value.tableName;
        this.columnName = value.columnName;
        this.ordinalPosition = value.ordinalPosition;
        this.columnDefault = value.columnDefault;
        this.isNullable = value.isNullable;
        this.dataType = value.dataType;
        this.characterMaximumLength = value.characterMaximumLength;
        this.characterOctetLength = value.characterOctetLength;
        this.numericPrecision = value.numericPrecision;
        this.numericPrecisionRadix = value.numericPrecisionRadix;
        this.numericScale = value.numericScale;
        this.characterSetName = value.characterSetName;
        this.collationName = value.collationName;
        this.typeName = value.typeName;
        this.nullable = value.nullable;
        this.isComputed = value.isComputed;
        this.selectivity = value.selectivity;
        this.checkConstraint = value.checkConstraint;
        this.sequenceName = value.sequenceName;
        this.remarks = value.remarks;
        this.sourceDataType = value.sourceDataType;
        this.columnType = value.columnType;
        this.columnOnUpdate = value.columnOnUpdate;
    }

    public Columns(
        String  tableCatalog,
        String  tableSchema,
        String  tableName,
        String  columnName,
        Integer ordinalPosition,
        String  columnDefault,
        String  isNullable,
        Integer dataType,
        Integer characterMaximumLength,
        Integer characterOctetLength,
        Integer numericPrecision,
        Integer numericPrecisionRadix,
        Integer numericScale,
        String  characterSetName,
        String  collationName,
        String  typeName,
        Integer nullable,
        Boolean isComputed,
        Integer selectivity,
        String  checkConstraint,
        String  sequenceName,
        String  remarks,
        Short   sourceDataType,
        String  columnType,
        String  columnOnUpdate
    ) {
        this.tableCatalog = tableCatalog;
        this.tableSchema = tableSchema;
        this.tableName = tableName;
        this.columnName = columnName;
        this.ordinalPosition = ordinalPosition;
        this.columnDefault = columnDefault;
        this.isNullable = isNullable;
        this.dataType = dataType;
        this.characterMaximumLength = characterMaximumLength;
        this.characterOctetLength = characterOctetLength;
        this.numericPrecision = numericPrecision;
        this.numericPrecisionRadix = numericPrecisionRadix;
        this.numericScale = numericScale;
        this.characterSetName = characterSetName;
        this.collationName = collationName;
        this.typeName = typeName;
        this.nullable = nullable;
        this.isComputed = isComputed;
        this.selectivity = selectivity;
        this.checkConstraint = checkConstraint;
        this.sequenceName = sequenceName;
        this.remarks = remarks;
        this.sourceDataType = sourceDataType;
        this.columnType = columnType;
        this.columnOnUpdate = columnOnUpdate;
    }

    @Size(max = 2147483647)
    public String getTableCatalog() {
        return this.tableCatalog;
    }

    public void setTableCatalog(String tableCatalog) {
        this.tableCatalog = tableCatalog;
    }

    @Size(max = 2147483647)
    public String getTableSchema() {
        return this.tableSchema;
    }

    public void setTableSchema(String tableSchema) {
        this.tableSchema = tableSchema;
    }

    @Size(max = 2147483647)
    public String getTableName() {
        return this.tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    @Size(max = 2147483647)
    public String getColumnName() {
        return this.columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public Integer getOrdinalPosition() {
        return this.ordinalPosition;
    }

    public void setOrdinalPosition(Integer ordinalPosition) {
        this.ordinalPosition = ordinalPosition;
    }

    @Size(max = 2147483647)
    public String getColumnDefault() {
        return this.columnDefault;
    }

    public void setColumnDefault(String columnDefault) {
        this.columnDefault = columnDefault;
    }

    @Size(max = 2147483647)
    public String getIsNullable() {
        return this.isNullable;
    }

    public void setIsNullable(String isNullable) {
        this.isNullable = isNullable;
    }

    public Integer getDataType() {
        return this.dataType;
    }

    public void setDataType(Integer dataType) {
        this.dataType = dataType;
    }

    public Integer getCharacterMaximumLength() {
        return this.characterMaximumLength;
    }

    public void setCharacterMaximumLength(Integer characterMaximumLength) {
        this.characterMaximumLength = characterMaximumLength;
    }

    public Integer getCharacterOctetLength() {
        return this.characterOctetLength;
    }

    public void setCharacterOctetLength(Integer characterOctetLength) {
        this.characterOctetLength = characterOctetLength;
    }

    public Integer getNumericPrecision() {
        return this.numericPrecision;
    }

    public void setNumericPrecision(Integer numericPrecision) {
        this.numericPrecision = numericPrecision;
    }

    public Integer getNumericPrecisionRadix() {
        return this.numericPrecisionRadix;
    }

    public void setNumericPrecisionRadix(Integer numericPrecisionRadix) {
        this.numericPrecisionRadix = numericPrecisionRadix;
    }

    public Integer getNumericScale() {
        return this.numericScale;
    }

    public void setNumericScale(Integer numericScale) {
        this.numericScale = numericScale;
    }

    @Size(max = 2147483647)
    public String getCharacterSetName() {
        return this.characterSetName;
    }

    public void setCharacterSetName(String characterSetName) {
        this.characterSetName = characterSetName;
    }

    @Size(max = 2147483647)
    public String getCollationName() {
        return this.collationName;
    }

    public void setCollationName(String collationName) {
        this.collationName = collationName;
    }

    @Size(max = 2147483647)
    public String getTypeName() {
        return this.typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Integer getNullable() {
        return this.nullable;
    }

    public void setNullable(Integer nullable) {
        this.nullable = nullable;
    }

    public Boolean getIsComputed() {
        return this.isComputed;
    }

    public void setIsComputed(Boolean isComputed) {
        this.isComputed = isComputed;
    }

    public Integer getSelectivity() {
        return this.selectivity;
    }

    public void setSelectivity(Integer selectivity) {
        this.selectivity = selectivity;
    }

    @Size(max = 2147483647)
    public String getCheckConstraint() {
        return this.checkConstraint;
    }

    public void setCheckConstraint(String checkConstraint) {
        this.checkConstraint = checkConstraint;
    }

    @Size(max = 2147483647)
    public String getSequenceName() {
        return this.sequenceName;
    }

    public void setSequenceName(String sequenceName) {
        this.sequenceName = sequenceName;
    }

    @Size(max = 2147483647)
    public String getRemarks() {
        return this.remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Short getSourceDataType() {
        return this.sourceDataType;
    }

    public void setSourceDataType(Short sourceDataType) {
        this.sourceDataType = sourceDataType;
    }

    @Size(max = 2147483647)
    public String getColumnType() {
        return this.columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }

    @Size(max = 2147483647)
    public String getColumnOnUpdate() {
        return this.columnOnUpdate;
    }

    public void setColumnOnUpdate(String columnOnUpdate) {
        this.columnOnUpdate = columnOnUpdate;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Columns (");

        sb.append(tableCatalog);
        sb.append(", ").append(tableSchema);
        sb.append(", ").append(tableName);
        sb.append(", ").append(columnName);
        sb.append(", ").append(ordinalPosition);
        sb.append(", ").append(columnDefault);
        sb.append(", ").append(isNullable);
        sb.append(", ").append(dataType);
        sb.append(", ").append(characterMaximumLength);
        sb.append(", ").append(characterOctetLength);
        sb.append(", ").append(numericPrecision);
        sb.append(", ").append(numericPrecisionRadix);
        sb.append(", ").append(numericScale);
        sb.append(", ").append(characterSetName);
        sb.append(", ").append(collationName);
        sb.append(", ").append(typeName);
        sb.append(", ").append(nullable);
        sb.append(", ").append(isComputed);
        sb.append(", ").append(selectivity);
        sb.append(", ").append(checkConstraint);
        sb.append(", ").append(sequenceName);
        sb.append(", ").append(remarks);
        sb.append(", ").append(sourceDataType);
        sb.append(", ").append(columnType);
        sb.append(", ").append(columnOnUpdate);

        sb.append(")");
        return sb.toString();
    }
}
