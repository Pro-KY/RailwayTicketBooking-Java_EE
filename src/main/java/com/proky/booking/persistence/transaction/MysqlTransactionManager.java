package com.proky.booking.persistence.transaction;

import com.proky.booking.persistence.database.ConnectionWrapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;

public class MysqlTransactionManager implements TransactionManager {
    private static MysqlTransactionManager mInstance;
    private ConnectionWrapper connectionWrapper;
    private static final Logger log = LogManager.getLogger(MysqlTransactionManager.class);

    private MysqlTransactionManager() {
        connectionWrapper = ConnectionWrapper.getInstance();
    }

    public static MysqlTransactionManager getInstance() {
        if (mInstance == null) {
            mInstance = new MysqlTransactionManager();
        }
        return mInstance;
    }

    @Override
    public void setReadOnly(boolean readOnly) {
        log.info("set readOnly - {}", readOnly);
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
        log.info("END OF TRANSACTION!");
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
