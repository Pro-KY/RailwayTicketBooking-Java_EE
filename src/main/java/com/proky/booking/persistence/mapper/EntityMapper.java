package com.proky.booking.persistence.mapper;

import java.sql.ResultSet;

/**
 * The class extracts values from a ResultSet and map to an entity of T type
 *
 * @param <T> type of an entity
 */
public abstract class EntityMapper<T> {
    T mappedEntity;
    public abstract T mapToEntity(ResultSet resultSet);

    public EntityMapper() { }
    String[] columnNames;

    public EntityMapper(String[] columnNames) {
        this.columnNames = columnNames;
    }

    void setColumnNameAliasAtIndex(String colName, int index) {
        columnNames[index] = colName;
    }

}
