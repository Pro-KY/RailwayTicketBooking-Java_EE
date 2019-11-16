package com.proky.booking.persistence.transaction;

import com.proky.booking.annotation.Transactional;
import com.proky.booking.exception.TransactionException;
import com.proky.booking.persistence.dao.factory.DaoFactory;
import com.proky.booking.util.properties.MessageProperties;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.proky.booking.util.properties.MessageProperties.TRANSACTION_ERROR;

public class TransactionalProxy {
    private static final Logger log = LogManager.getLogger(TransactionalProxy.class);

    private DaoFactory daoFactory;

    public TransactionalProxy(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public Object createProxy(Class<?> tClass) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(tClass);
        enhancer.setCallback(getMethodInterceptorCallback());

        return enhancer.create(new Class[]{DaoFactory.class}, new Object[] {daoFactory});
    }

    private MethodInterceptor getMethodInterceptorCallback() {
        return (obj, method, args, proxy) -> {
            Object object;

            if(method.isAnnotationPresent(Transactional.class)) {
                final Transactional annotation = method.getAnnotation(Transactional.class);
                final boolean readOnly = annotation.readOnly();

                TransactionManager tm = MysqlTransactionManager.getInstance();
                tm.startTransaction();
                tm.setReadOnly(readOnly);
                log.debug("run in proxytest.transaction start");
                try {
                    object =  proxy.invokeSuper(obj, args);
                    tm.commit();
                } catch (Throwable throwable) {
                    tm.rollback();
                    throw new TransactionException(MessageProperties.getMessage(TRANSACTION_ERROR), throwable.getCause());
                } finally {
                    tm.setReadOnly(false);
                    tm.endTransaction();
                }

                log.debug("run in proxytest.transaction endTransaction");
            } else {
                log.debug("just simple method call");
                object =  proxy.invokeSuper(obj, args);
            }
            return object;
        };
    }
}
