package com.proky.booking.service;

import com.proky.booking.dto.InvoiceDto;
import com.proky.booking.dto.TicketBookingDto;
import com.proky.booking.dto.TrainDto;
import com.proky.booking.persistence.dao.factory.DaoFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class InvoiceService {
    private static final Logger log = LogManager.getLogger(InvoiceService.class);
    private DaoFactory daoFactory;

    InvoiceService(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public InvoiceDto calculateInvoice(TicketBookingDto ticketBookingDto) {
        final Long trainId = Long.parseLong(ticketBookingDto.getTrainId());

        final ServiceFactory serviceFactory = ServiceFactory.getInstance();
        final TrainDto trainDto = serviceFactory.getTrainService().findTrainById(trainId);

        final ModelMapper modelMapper = new ModelMapper();
        final InvoiceDto invoiceDto = modelMapper.map(trainDto, InvoiceDto.class);

        final BigDecimal seatsAmount = BigDecimal.valueOf(Long.parseLong(ticketBookingDto.getSeatsAmount()));
        final BigDecimal seatPrice = trainDto.getTrainSeatPrice();
        final BigDecimal routeLengthFactor = BigDecimal.valueOf(trainDto.getRouteLengthFactor());
        BigDecimal sum = seatsAmount.multiply(seatPrice).multiply(routeLengthFactor).setScale(2, RoundingMode.CEILING);
        invoiceDto.setSum(sum);
        invoiceDto.setSeatsAmount(seatsAmount);

        // if guest just return dto
        // else save data to invoice table and return dto

        return invoiceDto;
    }
}
