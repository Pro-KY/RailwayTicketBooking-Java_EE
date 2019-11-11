package com.proky.booking.service;

import com.proky.booking.annotation.AnnotationChecker;
import com.proky.booking.annotation.Transactional;
import com.proky.booking.persistence.dao.factory.DaoFactory;
import com.proky.booking.persistence.dao.factory.MysqlDaoFactory;
import com.proky.booking.persistence.transaction.TransactionalProxy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;

public class ServiceFactory {
    private static final Logger log = LogManager.getLogger(ServiceFactory.class);
    private Class<SignUpService> signUpServiceClass = SignUpService.class;
    private Class<SignInService> signInServiceClass = SignInService.class;
    private Class<TrainService> trainServiceClass = TrainService.class;
    private Class<StationService> stationServiceClass = StationService.class;

    private static ServiceFactory mInstance;
    private HashMap<Class<?>, Object> servicesMap = new HashMap<>();
    private static DaoFactory daoFactory = MysqlDaoFactory.getInstance();


    private ServiceFactory() {
//        DaoFactory daoFactory = MysqlDaoFactory.getInstance();
        final SignUpService signUpService = new SignUpService(daoFactory);
        final SignInService signInService = new SignInService(daoFactory);
        final TrainService trainService = new TrainService(daoFactory);
        final StationService stationService = new StationService(daoFactory);

        servicesMap.put(signUpServiceClass, signUpService);
        servicesMap.put(signInServiceClass, signInService);
        servicesMap.put(trainServiceClass, trainService);
        servicesMap.put(stationServiceClass, stationService);
    }

    public static ServiceFactory getInstance() {
        if (mInstance == null) {
            mInstance = new ServiceFactory();
        }
        return mInstance;
    }


    private static <T> T getService(T instance, Class<? extends T> aClass) {
        AnnotationChecker annotationChecker = new AnnotationChecker();
        final boolean isPresent = annotationChecker.isAnnotationInCLass(aClass, annotation -> (annotation instanceof Transactional));

        T service;

        if (isPresent) {
//            final MysqlDaoFactory daoFactory = MysqlDaoFactory.getInstance();
            TransactionalProxy tTransactionalProxy = new TransactionalProxy(daoFactory);
            service = aClass.cast(tTransactionalProxy.createProxy(aClass));
            log.info("create proxy");
        } else {
            service = instance;
            log.info("create instance");
        }
        return service;
    }


    private <T> T getServiceFromMap(Class<? extends T> aClass) {
        return aClass.cast(servicesMap.get(aClass));
    }


    public SignUpService getSignUpService() {
        return getService(getServiceFromMap(signUpServiceClass), signUpServiceClass);
    }

    public SignInService getSignInService() {
        return getService(getServiceFromMap(signInServiceClass), signInServiceClass);
    }

    public TrainService getFindTrainService() {
        return getService(getServiceFromMap(trainServiceClass), trainServiceClass);
    }

    public StationService getStationService() {
        return getService(getServiceFromMap(stationServiceClass), stationServiceClass);
    }

}
