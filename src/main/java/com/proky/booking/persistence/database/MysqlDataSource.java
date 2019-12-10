package com.proky.booking.persistence.database;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * The class is a wrapper of @{code DataSource} used to initialize @{code DataSource} and retrieve database connection.
 *
 * @see DataSource
 */
public class MysqlDataSource {
    private static final Logger log = LogManager.getLogger(MysqlDataSource.class);
    private static MysqlDataSource instance;
    private DataSource dataSource;

    public static MysqlDataSource getInstance() {
        if (instance == null) {
            instance = new MysqlDataSource();
        }
        return instance;
    }

    private MysqlDataSource() {
        initDataSource();
    }

    /**
     * Initialize @{code DataSource} using tomcat connection pool via JNDI mechanism.
     */
    private void initDataSource() {
        try {
            final InitialContext ctx = new InitialContext();
            dataSource = (DataSource) ctx.lookup("java:/comp/env/jdbc/mysql");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    /**
     * get connection to the data source
     *
     * @return a connection to the data source or null if could not get connection from a pool
     */
    public Connection getConnection() {
        Connection conn = null;

        try {
            if (dataSource != null) {
                conn = dataSource.getConnection();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            log.error("Can't get DB connection: {}" + e.toString());
        }

        return conn;
    }
}
