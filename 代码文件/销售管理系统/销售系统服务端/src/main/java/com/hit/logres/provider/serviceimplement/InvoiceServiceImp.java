package com.hit.logres.provider.serviceimplement;

import com.hit.logres.api.entity.Invoice;
import com.hit.logres.api.entity.InvoiceLine;
import com.hit.logres.provider.mapper.InvoiceLineMapper;
import com.hit.logres.provider.mapper.InvoiceMapper;
import com.hit.logres.api.service.InvoiceService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Service("InvoiceService")
@DubboService
public class InvoiceServiceImp implements InvoiceService {

    @Autowired
    InvoiceMapper invoiceMapper;

    @Autowired
    InvoiceLineMapper invoiceLineMapper;

    @Override
    public List<Invoice> getAllInvoice() {
        try {
            return invoiceMapper.getAllInvoice();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int insertInvoice(Invoice user) {
        try {
            invoiceMapper.insertInvoice(user);
            return user.getId();
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public Boolean deleteInvoice(Integer id) {
        try {
            invoiceMapper.deleteInvoice(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Invoice findInvoiceById(Integer id) {
        try {
            return invoiceMapper.findInvoiceById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Boolean updateInvoice(Invoice customer) {
        try {
            invoiceMapper.updateInvoice(customer);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Invoice> findInvoiceByUserId(Integer id) {
        try{
            return invoiceMapper.findInvoiceByUserId(id);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Invoice> findInvoiceByCustomerId(Integer id) {
        try{
            return invoiceMapper.findInvoiceByCustomerId(id);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Invoice> findInvoiceByGoodId(Integer id) {
        List<Invoice> res = new ArrayList<Invoice>();
        try{
            List<InvoiceLine> lines = invoiceLineMapper.findInvoiceLineByGoodId(id);
            for(InvoiceLine il:lines){
                res.add(invoiceMapper.findInvoiceById(il.getInvoiceId()));
            }
            return res;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Invoice> findInvoiceByTime(Timestamp start, Timestamp end) {
        try{
            return invoiceMapper.findInvoiceByTimestamp(start,end);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
