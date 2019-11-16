package com.proky.booking.persistence.database;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;

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

    public void useInTransaction(boolean useInTransaction) {
        log.debug("useInTransaction = {}", useInTransaction);
        usedInTransactionThreadLocal.set(useInTransaction);
//        log.debug("usedInTransactionThreadLocal.get() = {}", usedInTransactionThreadLocal.get());
        setAutoCommit(!useInTransaction);
    }

    public void setReadOnly(boolean readOnly) {
        try {
            getConnection().setReadOnly(readOnly);
        } catch (SQLException e) {
            log.error("couldn't set readOnly mode to connection");
        }
    }

    private void setAutoCommit(boolean autoCommit) {
        try {
            getConnection().setAutoCommit(autoCommit);
        } catch (SQLException e) {
            log.error("can't set AutoCommit mode to connection");
        }
    }

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