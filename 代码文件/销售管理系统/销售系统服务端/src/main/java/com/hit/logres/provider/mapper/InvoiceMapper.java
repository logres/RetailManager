package com.hit.logres.provider.mapper;

import com.hit.logres.api.entity.Invoice;
import org.apache.ibatis.annotations.Param;

import java.sql.Timestamp;
import java.util.List;

public interface InvoiceMapper{
    void insertInvoice(Invoice invoice) throws Exception;

    void deleteInvoice(Integer id) throws Exception;

    Invoice findInvoiceById(Integer id) throws Exception;

    List<Invoice> getAllInvoice() throws Exception;

    void updateInvoice(Invoice invoice) throws Exception;

    List<Invoice> findInvoiceByUserId(Integer UserId);

    List<Invoice> findInvoiceByCustomerId(Integer CustomerId);

    List<Invoice> findInvoiceByTimestamp(@Param("start")Timestamp start, @Param("end")Timestamp end);

}
