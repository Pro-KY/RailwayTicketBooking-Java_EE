package com.proky.booking.persistence.dao.mysql;

import com.proky.booking.persistence.dao.IInvoiceDao;
import com.proky.booking.persistence.dao.ITrainDao;
import com.proky.booking.persistence.entity.Invoice;
import com.proky.booking.persistence.entity.Station;
import com.proky.booking.persistence.entity.Train;
import com.proky.booking.persistence.jdbc.JdbcTemplate;
import com.proky.booking.persistence.mapper.*;
import com.proky.booking.util.properties.SqlProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Optional;

import static com.proky.booking.util.properties.SqlProperties.*;

public class InvoiceDaoImpl implements IInvoiceDao {
    private static InvoiceDaoImpl instance;
    private JdbcTemplate jdbcTemplate;
    private static final Logger log = LogManager.getLogger(InvoiceDaoImpl.class);

    private InvoiceDaoImpl() {
        jdbcTemplate = JdbcTemplate.getInstance();
    }

    public static InvoiceDaoImpl getInstance() {
        if (instance == null) {
            instance = new InvoiceDaoImpl();
        }
        return instance;
    }

    @Override
    public Long save(Invoice entity) {
        Object[] params = {entity.getUser().getId(), entity.getTrain().getId(), entity.getSeatsAmount(), entity.getPrice(), entity.getDateTime()};
        final String query = SqlProperties.getQuery(SAVE_INVOICE);
        return jdbcTemplate.saveOrUpdate(query, params);
    }

    @Override
    public Long update(Invoice entity) {
        return null;
    }

    @Override
    public boolean delete(Invoice entity) {
        return false;
    }

    @Override
    public Optional<Invoice> findById(Long id) {
        return Optional.empty();
    }
}
