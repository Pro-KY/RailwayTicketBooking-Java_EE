package com.proky.booking.service;

import com.proky.booking.dto.InvoiceDto;
import com.proky.booking.dto.TicketBookingDto;
import com.proky.booking.persistence.dao.factory.DaoFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class InvoiceService {
    private static final Logger log = LogManager.getLogger(InvoiceService.class);
    private DaoFactory daoFactory;

    InvoiceService(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public InvoiceDto calculateInvoice(TicketBookingDto ticketBookingDto) {
        return null;
    }
}
