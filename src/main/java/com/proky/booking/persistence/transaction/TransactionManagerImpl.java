package com.proky.booking.persistence.transaction;

import com.proky.booking.persistence.database.ConnectionWrapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;

public class TransactionManagerImpl implements TransactionManager {
    private static TransactionManagerImpl mInstance;
    private ConnectionWrapper connectionWrapper;
    private static final Logger log = LogManager.getLogger(TransactionManagerImpl.class);

    private TransactionManagerImpl() {
        connectionWrapper = ConnectionWrapper.getInstance();
    }

    public static TransactionManagerImpl getInstance() {
        if (mInstance == null) {
            mInstance = new TransactionManagerImpl();
        }
        return mInstance;
    }

    @Override
    public void setReadOnly(boolean readOnly) {
//        log.debug("set readOnly transaction mode: {}", readOnly);
        connectionWrapper.setReadOnly(readOnly);
    }

    @Override
    public void startTransaction() {
        connectionWrapper.useInTransaction(true);
    }

    @Override
    public void commit() {
        try {
            connectionWrapper.getConnection().commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void endTransaction() {
        connectionWrapper.useInTransaction(false);
        connectionWrapper.close();
    }

    @Override
    public void rollback() {
        try {
            connectionWrapper.getConnection().rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
