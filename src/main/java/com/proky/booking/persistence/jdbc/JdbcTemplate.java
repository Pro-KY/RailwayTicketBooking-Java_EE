package com.proky.booking.persistence.jdbc;

import com.proky.booking.exception.DataAccessException;
import com.proky.booking.persistence.database.ConnectionWrapper;
import com.proky.booking.persistence.mapper.EntityMapper;
import com.proky.booking.util.properties.SqlProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/*
 *  This class executes SQL queries or updates, iterates over ResultSets and maps it to List or entity of generic type T
 *  through {@code EntityMapper<T>}
 */
public class JdbcTemplate {
    private static final Logger log = LogManager.getLogger(JdbcTemplate.class);
    private static JdbcTemplate instance;
    private final ConnectionWrapper connectionWrapper = ConnectionWrapper.getInstance();

    private JdbcTemplate() {}

    public static JdbcTemplate getInstance() {
        if (instance == null) {
            instance = new JdbcTemplate();
        }
        return instance;
    }

    public <T> List<T> findAll(String sql, EntityMapper<T> entityMapper, Object... parameters) {
        List<T> resultList = new ArrayList<>();

        final Connection connection = connectionWrapper.getConnection();
        final JdbcQuery jdbcQuery = new JdbcQuery(connection, sql);

        try(final ResultSet result = jdbcQuery.select(parameters)) {

            while (result.next()) {
                final T entity = entityMapper.mapToEntity(result);
                resultList.add(entity);
            }
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            throw new DataAccessException(e);
        } finally {
            connectionWrapper.close();
        }

        return resultList;
    }

    public <T> Optional<T> findByQuery(String sql, EntityMapper<T> entityMapper, Object... parameters) {
        final Connection connection = connectionWrapper.getConnection();
        final JdbcQuery jdbcQuery = new JdbcQuery(connection, sql);

        Optional<T> entity = Optional.empty();
        try (ResultSet result = jdbcQuery.select(parameters)) {
            if(result.next()) {
                entity = Optional.ofNullable(entityMapper.mapToEntity(result));
            }
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            throw new DataAccessException(e);
        }  finally {
            connectionWrapper.close();
        }
        return entity;
    }

    public Long countRows(String sql, Object...parameters) {
        final Connection connection = connectionWrapper.getConnection();
        final JdbcQuery jdbcQuery = new JdbcQuery(connection, sql);
        long rowsAmount = 0;
        try (ResultSet result = jdbcQuery.select(parameters)) {
            if (result.next()) {
                rowsAmount = result.getLong(1);
            }
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            throw new DataAccessException(e);
        }  finally {
            connectionWrapper.close();
        }
        return rowsAmount;
    }

    public Long saveOrUpdate(String sql, Object... parameters) {
        long insertedId;
        final Connection connection = connectionWrapper.getConnection();
        final JdbcQuery jdbcQuery = new JdbcQuery(connection, sql);

        try {
            insertedId = jdbcQuery.saveOrUpdate(parameters);
        } catch (SQLException e) {
            throw new DataAccessException(e);
        } finally {
            closeResultSet(jdbcQuery.getResult());
            connectionWrapper.close();
        }
        return insertedId;
    }

    public boolean delete(String sql, Object... parameters) {
        boolean isDeleted;
        final Connection connection = connectionWrapper.getConnection();
        final JdbcQuery jdbcQuery = new JdbcQuery(connection, sql);

        try {
            isDeleted = jdbcQuery.delete(parameters);
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            throw new DataAccessException(e);
        } finally {
            closeResultSet(jdbcQuery.getResult());
            connectionWrapper.close();
        }

        return isDeleted;
    }

    private void closeResultSet(ResultSet resultSet) {
        try {
            if(resultSet != null) {
                resultSet.close();
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new DataAccessException(e);
        }
    }
}
