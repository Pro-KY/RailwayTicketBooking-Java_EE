package com.proky.booking.service;

import com.proky.booking.util.AnnotationChecker;
import com.proky.booking.persistence.transaction.Transactional;
import com.proky.booking.persistence.dao.factory.DaoFactory;
import com.proky.booking.persistence.dao.factory.MysqlDaoFactory;
import com.proky.booking.persistence.transaction.TransactionalProxy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;

public class ServiceFactory {
    private static final Logger log = LogManager.getLogger(ServiceFactory.class);
    private Class<TrainService> trainServiceClass = TrainService.class;
    private Class<StationService> stationServiceClass = StationService.class;
    private Class<InvoiceService> invoiceServiceClass = InvoiceService.class;
    private Class<UserService> userServiceClass = UserService.class;

    private static ServiceFactory mInstance;
    private HashMap<Class<?>, Object> servicesMap = new HashMap<>();
    private static DaoFactory daoFactory = MysqlDaoFactory.getInstance();


    private ServiceFactory() {
        final TrainService trainService = new TrainService(daoFactory);
        final StationService stationService = new StationService(daoFactory);
        final InvoiceService invoiceService = new InvoiceService(daoFactory);
        final UserService userService = new UserService(daoFactory);

        servicesMap.put(trainServiceClass, trainService);
        servicesMap.put(stationServiceClass, stationService);
        servicesMap.put(invoiceServiceClass, invoiceService);
        servicesMap.put(userServiceClass, userService);
    }

    public static ServiceFactory getInstance() {
        if (mInstance == null) {
            mInstance = new ServiceFactory();
        }
        return mInstance;
    }


    /**
     * Checks whether instance contain methods marked as Transactional and creates a proxy
     * object based on passed instance as an argument
     *
     * @param aClass an auxiliary object to cast proxy object to required type T
     * @param serviceObject previously created service object
     * @param <T> type of a service object
     * @return a proxy or simple service object
     */
    private static <T> T getService(T serviceObject, Class<? extends T> aClass) {
        AnnotationChecker annotationChecker = AnnotationChecker.getInstance();
        final boolean isPresent = annotationChecker.isAnnotationInCLass(aClass, annotation -> (annotation instanceof Transactional));

        T service;

        if (isPresent) {
            TransactionalProxy tTransactionalProxy = new TransactionalProxy(daoFactory);
            service = aClass.cast(tTransactionalProxy.createProxy(aClass));
        } else {
            service = serviceObject;
        }
        return service;
    }

    private <T> T getServiceFromMap(Class<? extends T> aClass) {
        return aClass.cast(servicesMap.get(aClass));
    }

    public TrainService getTrainService() {
        return getService(getServiceFromMap(trainServiceClass), trainServiceClass);
    }

    public StationService getStationService() {
        return getService(getServiceFromMap(stationServiceClass), stationServiceClass);
    }

    public InvoiceService getInvoiceService() {
        return getService(getServiceFromMap(invoiceServiceClass), invoiceServiceClass);
    }

    public UserService getUserService() {
        return getService(getServiceFromMap(userServiceClass), userServiceClass);
    }
}
