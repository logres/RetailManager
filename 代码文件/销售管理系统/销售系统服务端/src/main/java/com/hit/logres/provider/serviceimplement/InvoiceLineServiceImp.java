package com.hit.logres.provider.serviceimplement;

import com.hit.logres.api.entity.InvoiceLine;
import com.hit.logres.provider.mapper.InvoiceLineMapper;
import com.hit.logres.api.service.InvoiceLineService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@org.springframework.stereotype.Service("InvoiceLineService")
@DubboService
public class InvoiceLineServiceImp implements InvoiceLineService {

    @Autowired
    InvoiceLineMapper invoicelineMapper;

    @Override
    public List<InvoiceLine> getAllInvoiceLine() {
        try {
            return invoicelineMapper.getAllInvoiceLine();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int insertInvoiceLine(InvoiceLine user) {
        try {
            invoicelineMapper.insertInvoiceLine(user);
            return user.getId();
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public Boolean deleteInvoiceLine(Integer id) {
        try {
            invoicelineMapper.deleteInvoiceLine(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public InvoiceLine findInvoiceLineById(Integer id) {
        try {
            return invoicelineMapper.findInvoiceLineById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Boolean updateInvoiceLine(InvoiceLine customer) {
        try {
            invoicelineMapper.updateInvoiceLine(customer);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<InvoiceLine> findInvoiceLineByInvoiceId(Integer id) {
        try {
            return invoicelineMapper.findInvoiceLineByInvoiceId(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<InvoiceLine> findInvoiceLineByGoodId(Integer id) {
        try{
            return invoicelineMapper.findInvoiceLineByGoodId(id);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}