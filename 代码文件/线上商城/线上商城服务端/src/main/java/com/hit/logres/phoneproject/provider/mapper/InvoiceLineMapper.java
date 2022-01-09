package com.hit.logres.phoneproject.provider.mapper;



import com.hit.logres.phoneproject.api.entity.InvoiceLine;

import java.util.List;

public interface InvoiceLineMapper {
    void insertInvoiceLine(InvoiceLine invoiceLine) throws Exception;

    void deleteInvoiceLine(Integer id) throws Exception;

    InvoiceLine findInvoiceLineById(Integer id) throws Exception;

    List<InvoiceLine> getAllInvoiceLine() throws Exception;

    void updateInvoiceLine(InvoiceLine invoiceline) throws Exception;

    List<InvoiceLine> findInvoiceLineByInvoiceId(Integer invoiceId) throws Exception;

    List<InvoiceLine> findInvoiceLineByGoodId(Integer goodId) throws Exception;
}
