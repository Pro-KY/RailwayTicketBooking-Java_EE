package com.proky.booking.persistence.transaction;


public interface TransactionManager {
    void startTransaction();
    void rollback();
    void commit();
    void endTransaction();
    void setReadOnly(boolean readOnly);
}
