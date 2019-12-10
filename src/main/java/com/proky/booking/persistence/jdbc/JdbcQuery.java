package com.proky.booking.persistence.jdbc;


import com.proky.booking.exception.DataAccessException;

import java.sql.*;

/**
 * The class specifying a basic set of JDBC operations.
 * It executes core JDBC workflow: create statements, set query parameters, return extracted results.
 */
class JdbcQuery {
    private ResultSet rs;
    private PreparedStatement ps;
    private Statement statement;
    private Connection connection;
    private String sql;
    private int rowsAffected;

    ResultSet getResult() {
        return rs;
    }

    JdbcQuery(Connection connection, String sql) {
        this.connection = connection;
        this.sql = sql;
    }

    private void setParameters(Object... parameters) throws SQLException {
        if (parameters != null) {
            for (int i = 0; i < parameters.length; i++) {
                ps.setObject(i+1, parameters[i]);
            }
        }
    }

     Long saveOrUpdate(Object... parameters) throws SQLException {
        performModifyingQuery(parameters);
        long generatedKey = 0L;

        if(rs.next()) {
            generatedKey = rs.getLong(1);
        }

        return generatedKey;
    }

    boolean delete(Object... parameters) throws SQLException {
        performModifyingQuery(parameters);
        return rowsAffected > 0;
    }

    private void performModifyingQuery(Object... parameters) throws SQLException {
        ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        setParameters(parameters);
        rowsAffected = ps.executeUpdate();
        rs = ps.getGeneratedKeys();
    }

    ResultSet select(Object... parameters) throws SQLException {
        ps = connection.prepareStatement(sql);
        setParameters(parameters);
        rs = ps.executeQuery();
        return rs;
    }

    ResultSet select() {
        try {
            statement = connection.createStatement();
            rs = statement.executeQuery(sql);
            return rs;
        } catch (SQLException e) {
            throw new DataAccessException(e);
        }
    }

    boolean modifyAll() throws SQLException{
        ps = connection.prepareStatement(sql);
        rowsAffected = ps.executeUpdate();

        return rowsAffected > 0;
    }

    Statement getStatement() {
        return statement;
    }

    PreparedStatement getPreparedStatement() {
        return ps;
    }
}

//    private void setParameters(Object... parameters) {
//        if (parameters != null) {
//            try {
//                for (int i = 0; i < parameters.length; i++) {
//                    ps.setObject(i+1, parameters[i]);
//                }
//            } catch (SQLException e) {
//                throw new DataAccessException(e);
//            }
//        }
//    }
//
//    Long saveOrUpdate(Object... parameters) {
//        performModifyingQuery(parameters);
//        long generatedKey = 0L;
//
//        try {
//            if(rs.next()) {
//                generatedKey = rs.getLong(1);
//            }
//        } catch (SQLException e) {
//            throw new DataAccessException(e);
//        }
//
//        return generatedKey;
//    }
//
//    boolean delete(Object... parameters) {
//        performModifyingQuery(parameters);
//        return rowsAffected > 0;
//    }
//
//    private void performModifyingQuery(Object... parameters) {
//        try {
//            ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
//            setParameters(parameters);
//            rowsAffected = ps.executeUpdate();
//            rs = ps.getGeneratedKeys();
//        } catch (SQLException e) {
//            throw new DataAccessException(e);
//        }
//    }
//
//    ResultSet select(Object... parameters) {
//        try {
//            ps = connection.prepareStatement(sql);
//            setParameters(parameters);
//            rs = ps.executeQuery();
//            return rs;
//        } catch (SQLException e) {
//            throw new DataAccessException(e);
//        }
//    }
//
//    ResultSet select() {
//        try {
//            statement = connection.createStatement();
//            rs = statement.executeQuery(sql);
//            return rs;
//        } catch (SQLException e) {
//            throw new DataAccessException(e);
//        }
//    }
//
//    boolean modifyAll() {
//        try {
//            ps = connection.prepareStatement(sql);
//            rowsAffected = ps.executeUpdate();
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//
//        return rowsAffected > 0;
//    }
