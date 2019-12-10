package com.proky.booking.persistence.database;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;

/**
 * The class wraps a @{code Connection} to the data source for usage in in the multi-threading environment.
 */
public class ConnectionWrapper implements AutoCloseable {
    private static final Logger log = LogManager.getLogger(ConnectionWrapper.class);
    private static ConnectionWrapper mInstance;
    private ThreadLocal<Connection> connectionThreadLocal = ThreadLocal.withInitial(() -> MysqlDataSource.getInstance().getConnection());
    private ThreadLocal<Boolean> usedInTransactionThreadLocal = ThreadLocal.withInitial(() -> false);

    private ConnectionWrapper() {}

    public static ConnectionWrapper getInstance() {
        if (mInstance == null) {
            mInstance = new ConnectionWrapper();
        }
        return mInstance;
    }

    /**
     * Binds new database connection to the current thread of execution if connection is not already bound and
     * get the connection.
     *
     * @return a connection to a database
     */
    public Connection getConnection() {
        initConnection();
        final Connection connection = connectionThreadLocal.get();
//        log.debug("get connection = {}", connection.toString());
        return connection;
    }

    private void initConnection() {
        if (Objects.isNull(connectionThreadLocal.get())) {
//            log.debug("connection is null = {}", Objects.isNull(connectionThreadLocal.get()));
            connectionThreadLocal.set(MysqlDataSource.getInstance().getConnection());
        } else {
//            log.debug("connection is not null");
        }
    }

    /**
     * Set @{code boolean} flag that indicates whether a connection should be used in in a transaction
     *
     * @param useInTransaction true - use in a transaction, otherwise false
     */
    public void useInTransaction(boolean useInTransaction) {
//        log.debug("useInTransaction = {}", useInTransaction);
        usedInTransactionThreadLocal.set(useInTransaction);
//        log.debug("usedInTransactionThreadLocal.get() = {}", usedInTransactionThreadLocal.get());
        setAutoCommit(!useInTransaction);
    }

    /**
     * Enables or disables database connection read mode
     *
     * @param readOnly true - enable read only mode, false - disable read only mode
     */
    public void setReadOnly(boolean readOnly) {
        try {
            getConnection().setReadOnly(readOnly);
        } catch (SQLException e) {
            log.error("couldn't set readOnly mode to connection");
        }
    }

    /**
     * Changes database connection auto commit mode
     *
     * @param autoCommit true - enable auto commit, false - disable it
     */
    private void setAutoCommit(boolean autoCommit) {
        try {
            getConnection().setAutoCommit(autoCommit);
        } catch (SQLException e) {
            log.error("can't set AutoCommit mode to connection");
        }
    }

    /**
     * Return a database connection to connection pool and unbind from the current thread of execution
     */
    @Override
    public void close() {
        if (!usedInTransactionThreadLocal.get()) {
//            log.debug("usedInTransactionThreadLocal.get() = {}", usedInTransactionThreadLocal.get());
            try {
                final Connection connection = connectionThreadLocal.get();
//                log.debug("close connection = {}", connection.toString());
                connection.close();
//                connectionThreadLocal.get().close();
            } catch (SQLException e) {
//                log.error("failed to close a connection", e);
            } finally {
//                log.debug("release resources");
                connectionThreadLocal.remove();
                usedInTransactionThreadLocal.remove();
            }
        }
    }
}