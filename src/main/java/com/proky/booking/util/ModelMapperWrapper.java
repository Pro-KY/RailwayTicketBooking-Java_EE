package com.proky.booking.util;

import com.proky.booking.dto.TrainDto;
import com.proky.booking.persistence.entity.Train;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

public class ModelMapperWrapper {
    private static ModelMapperWrapper mInstance;
    private final ModelMapper modelMapper = new ModelMapper();

    private ModelMapperWrapper() {
        modelMapper.addMappings(customTrainPropertyMap());
    }

    public static ModelMapperWrapper getInstance() {
        if (mInstance == null) {
            mInstance = new ModelMapperWrapper();
        }
        return mInstance;
    }

    public ModelMapper getModelMapper() {
        return modelMapper;
    }

    private PropertyMap<Train, TrainDto> customTrainPropertyMap() {
        return new PropertyMap<Train, TrainDto>() {
            @Override
            protected void configure() {
                map().setArrivalStationId(source.getRoute().getArrivalStation().getId());
                map().setDepartureStationId(source.getRoute().getDepartureStation().getId());
            }
        };
    }
}
