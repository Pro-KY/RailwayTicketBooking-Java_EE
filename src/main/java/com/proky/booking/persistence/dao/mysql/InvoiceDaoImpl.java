package com.proky.booking.persistence.dao.mysql;

import com.proky.booking.persistence.dao.IInvoiceDao;
import com.proky.booking.persistence.entity.Invoice;
import com.proky.booking.persistence.jdbc.JdbcTemplate;
import com.proky.booking.persistence.mapper.InvoiceMapper;
import com.proky.booking.persistence.mapper.TrainMapper;
import com.proky.booking.persistence.mapper.UserMapper;
import com.proky.booking.util.properties.SqlProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
        final String query = SqlProperties.getValue(SAVE_INVOICE);
        return jdbcTemplate.saveOrUpdate(query, params);
    }

    @Override
    public Long update(Invoice entity) {
        Object[] params = {entity.getUser().getId(), entity.getTrain().getId(), entity.getSeatsAmount(), entity.getPrice(), entity.getDateTime(), entity.getId()};

        String sqlQuery = SqlProperties.getValue(UPDATE_INVOICE);
        final JdbcTemplate jdbcTemplate = JdbcTemplate.getInstance();
        return jdbcTemplate.saveOrUpdate(sqlQuery, params);
    }

    @Override
    public boolean delete(Invoice entity) {
        final String sqlQuery = SqlProperties.getValue(DELETE_INVOICE);
        return jdbcTemplate.delete(sqlQuery, entity.getId());
    }

    @Override
    public Optional<Invoice> findById(Long id) {
        final String sqlQuery = SqlProperties.getValue(FIND_INVOICE_BY_ID);
        final InvoiceMapper invoiceMapper = new InvoiceMapper(true);
        invoiceMapper.mapTrainRelation(new TrainMapper(true));
        invoiceMapper.mapUserRelation(new UserMapper(true));
        return jdbcTemplate.findByQuery(sqlQuery, invoiceMapper, id);
    }
}
