package com.proky.booking.service;

import com.proky.booking.persistence.dao.factory.DaoFactory;
import com.proky.booking.persistence.dao.factory.MysqlDaoFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;

public class ServiceFactory {
    private static final Logger log = LogManager.getLogger(ServiceFactory.class);
    private Class<SignUpService> signUpServiceClass = SignUpService.class;

    private static ServiceFactory mInstance;
    private HashMap<Class<?>, Object> servicesMap = new HashMap<>();

    private ServiceFactory() {
        DaoFactory daoFactory = MysqlDaoFactory.getInstance();
        final SignUpService signUpService = new SignUpService(daoFactory);

        servicesMap.put(signUpServiceClass, signUpService);
    }

    public static ServiceFactory getInstance() {
        if (mInstance == null) {
            mInstance = new ServiceFactory();
        }
        return mInstance;
    }

    private <T> T getServiceFromMap(Class<? extends T> aClass) {
        return aClass.cast(servicesMap.get(aClass));
    }


    public SignUpService getSignUpService() {
        return getServiceFromMap(signUpServiceClass);
    }

}
