package com.proky.booking.service;

import com.proky.booking.dto.InvoiceDto;
import com.proky.booking.dto.TicketBookingDto;
import com.proky.booking.dto.UserDto;
import com.proky.booking.persistence.dao.IInvoiceDao;
import com.proky.booking.persistence.dao.ITrainDao;
import com.proky.booking.persistence.dao.factory.DaoFactory;
import com.proky.booking.persistence.entity.Invoice;
import com.proky.booking.persistence.entity.Train;
import com.proky.booking.stub.TrainDataStubProvider;
import com.proky.booking.stub.UserDataStubProvider;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class InvoiceServiceTest {

    @Mock
    private DaoFactory daoFactory;

    @Mock
    private ITrainDao trainDao;

    @Mock
    private IInvoiceDao invoiceDao;

    @InjectMocks
    private InvoiceService invoiceService;

    private final TrainDataStubProvider trainDataStubProvider = TrainDataStubProvider.getInstance();
    private final UserDataStubProvider userDataStubProvider = UserDataStubProvider.getInstance();

    @Test
    public void calculateInvoicePriceWithCorrectSeatsAmount() {
        final TicketBookingDto ticketBookingDto = new TicketBookingDto.Builder().seatsAmount("2").trainId("1").build();
        final Train trainStub = trainDataStubProvider.getTrainStub();
        final UserDto userDto = userDataStubProvider.getUserDto();

        when(daoFactory.getTrainDao()).thenReturn(trainDao);
        when(trainDao.findById(anyLong())).thenReturn(Optional.of(trainStub));

        final InvoiceDto actualInvoiceDto = invoiceService.calculateInvoice(ticketBookingDto, userDto);

        final BigDecimal seatPrice = trainStub.getTrainType().getSeatPrice();
        final double routeLengthFactor = trainStub.getRoute().getRouteLengthFactor();
        final BigDecimal seatsAmount = BigDecimal.valueOf(Long.parseLong(ticketBookingDto.getSeatsAmount()));

        BigDecimal expectedPrice = seatsAmount
                .multiply(seatPrice)
                .multiply(BigDecimal.valueOf(routeLengthFactor))
                .setScale(2, RoundingMode.CEILING);

        final BigDecimal actualPrice = actualInvoiceDto.getSum();

        assertEquals(expectedPrice, actualPrice);
        verify(trainDao, times(1)).findById(anyLong());
    }

    @Test
    public void calculateInvoicePriceWithIncorrectSeatsAmount() {
        final TicketBookingDto ticketBookingDto = new TicketBookingDto.Builder().seatsAmount("0").trainId("1").build();
        final Train trainStub = trainDataStubProvider.getTrainStub();
        final UserDto userDto = userDataStubProvider.getUserDto();

        when(daoFactory.getTrainDao()).thenReturn(trainDao);
        when(trainDao.findById(anyLong())).thenReturn(Optional.of(trainStub));

        final BigDecimal seatPrice = trainStub.getTrainType().getSeatPrice();
        final double routeLengthFactor = trainStub.getRoute().getRouteLengthFactor();
        final BigDecimal seatsAmount = BigDecimal.valueOf(Long.parseLong("2"));

        BigDecimal expectedPrice = seatsAmount
                .multiply(seatPrice)
                .multiply(BigDecimal.valueOf(routeLengthFactor))
                .setScale(2, RoundingMode.CEILING);

        final InvoiceDto actualInvoiceDto = invoiceService.calculateInvoice(ticketBookingDto, userDto);
        final BigDecimal actualPrice = actualInvoiceDto.getSum();

        assertNotEquals(expectedPrice, actualPrice);
        verify(trainDao, times(1)).findById(anyLong());
    }

    @Test
    public void saveInvoiceTest() {
        when(daoFactory.getInvoiceDao()).thenReturn(invoiceDao);
        final InvoiceDto invoiceDto = new InvoiceDto();
        invoiceDto.setSeatsAmount(new BigDecimal(100.00));
        invoiceService.saveInvoice(invoiceDto);
        verify(invoiceDao, times(1)).save(any(Invoice.class));
    }

}
