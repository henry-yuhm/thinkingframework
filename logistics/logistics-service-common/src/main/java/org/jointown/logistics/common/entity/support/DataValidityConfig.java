package org.jointown.logistics.common.entity.support;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "sys_data_validity_config")
@IdClass(value = DataValidityConfig.DataValidityConfigPk.class)
public class DataValidityConfig {
    @Id
    @Column(name = "table_schema")
    private String tableSchema;

    @Id
    @Column(name = "table_name")
    private String tableName;

    @Id
    @Column(name = "column_name")
    private String columnName;

    @Column(name = "is_nullable")
    private String isNullable;

    @Column(name = "is_numeric")
    private String isNumeric;

    @Column(name = "data_length")
    private int dataLength;

    @Column(name = "date_format")
    private String dateFormat;

    public DataValidityConfig() {
    }

    public String getTableSchema() {
        return tableSchema;
    }

    public void setTableSchema(String tableSchema) {
        this.tableSchema = tableSchema;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getIsNullable() {
        return isNullable;
    }

    public void setIsNullable(String isNullable) {
        this.isNullable = isNullable;
    }

    public String getIsNumeric() {
        return isNumeric;
    }

    public void setIsNumeric(String isNumeric) {
        this.isNumeric = isNumeric;
    }

    public int getDataLength() {
        return dataLength;
    }

    public void setDataLength(int dataLength) {
        this.dataLength = dataLength;
    }

    public String getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    public static class DataValidityConfigPk implements Serializable {
        private String tableSchema;

        private String tableName;

        private String columnName;

        public DataValidityConfigPk() {
        }

        public String getTableSchema() {
            return tableSchema;
        }

        public void setTableSchema(String tableSchema) {
            this.tableSchema = tableSchema;
        }

        public String getTableName() {
            return tableName;
        }

        public void setTableName(String tableName) {
            this.tableName = tableName;
        }

        public String getColumnName() {
            return columnName;
        }

        public void setColumnName(String columnName) {
            this.columnName = columnName;
        }

        @Override
        public int hashCode() {
            return (this.tableSchema + this.tableName + this.columnName).hashCode();
        }

        @Override
        public boolean equals(Object object) {
            if (this == object) {
                return true;
            }

            if (object == null) {
                return false;
            }

            if (this.getClass() != object.getClass()) {
                return false;
            }

            DataValidityConfigPk dataValidityConfigPk = (DataValidityConfigPk) object;

            if (this.tableSchema.equals(dataValidityConfigPk.tableSchema) &&
                    this.tableName.equals(dataValidityConfigPk.tableName) &&
                    this.columnName.equals(dataValidityConfigPk.columnName)) {
                return true;
            } else {
                return false;
            }
        }

        @Override
        public String toString() {
            return this.tableSchema + "_" + this.tableName + "_" + this.columnName;
        }
    }
}