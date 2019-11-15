package com.proky.booking.service;

import com.proky.booking.dto.InvoiceDto;
import com.proky.booking.dto.TicketBookingDto;
import com.proky.booking.persistence.dao.factory.DaoFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class InvoiceCalculationService {
    private static final Logger log = LogManager.getLogger(InvoiceCalculationService.class);
    private DaoFactory daoFactory;

    InvoiceCalculationService(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public InvoiceDto calculateInvoice(TicketBookingDto ticketBookingDto) {
        return null;
    }
}
