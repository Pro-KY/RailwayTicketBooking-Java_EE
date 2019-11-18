package com.proky.booking.service;

import com.proky.booking.dto.InvoiceDto;
import com.proky.booking.dto.TicketBookingDto;
import com.proky.booking.dto.TrainDto;
import com.proky.booking.persistence.dao.IInvoiceDao;
import com.proky.booking.persistence.dao.factory.DaoFactory;
import com.proky.booking.persistence.entity.Invoice;
import com.proky.booking.persistence.entity.Train;
import com.proky.booking.persistence.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.*;
import org.modelmapper.convention.MatchingStrategies;

import javax.print.DocFlavor;
import javax.print.attribute.standard.Destination;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.Objects;

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

        return invoiceDto;
    }

    public void saveInvoice(final InvoiceDto invoiceDto) {
        final Invoice invoice = new Invoice(
                new User(invoiceDto.getUserId()),
                new Train(invoiceDto.getTrainId()),
                invoiceDto.getSeatsAmount().intValue(),
                invoiceDto.getSum(),
                new Timestamp(System.currentTimeMillis()));
        log.info("invoice, {}", invoice);

        final IInvoiceDao invoiceDao = daoFactory.getInvoiceDao();
        invoiceDao.save(invoice);
    }
}
