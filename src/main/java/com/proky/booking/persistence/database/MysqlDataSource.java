package com.proky.booking.persistence.database;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class MysqlDataSource {
    private static MysqlDataSource instance;
    private DataSource dataSource;

    public static MysqlDataSource getInstance() {
        if (instance == null) {
            instance = new MysqlDataSource();
        }
        return instance;
    }

    private MysqlDataSource() {
        System.out.println("MysqlDataSource private const is called");
        initDataSource();
    }

    private void initDataSource() {
        try {
            final InitialContext ctx = new InitialContext();
            dataSource = (DataSource) ctx.lookup("java:/comp/env/jdbc/mysql");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        Connection conn = null;

        try {
            if (dataSource != null) {
                conn = dataSource.getConnection();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Can't get DB connection: " + e.toString());
        }

        return conn;
    }
}
