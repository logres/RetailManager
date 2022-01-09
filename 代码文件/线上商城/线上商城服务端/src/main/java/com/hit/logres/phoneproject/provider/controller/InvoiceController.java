package com.hit.logres.phoneproject.provider.controller;

import com.hit.logres.phoneproject.api.entity.Invoice;
import com.hit.logres.phoneproject.api.entity.InvoiceLine;
import com.hit.logres.phoneproject.provider.mapper.InvoiceLineMapper;
import com.hit.logres.phoneproject.provider.mapper.InvoiceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/Invoice")
public class InvoiceController {
    @Autowired
    InvoiceMapper invoiceMapper;

    @Autowired
    InvoiceLineMapper invoiceLineMapper;

    @RequestMapping(value = "/getAll",method = RequestMethod.GET)
    public List<Invoice> getAllInvoice() {
        try {
            return invoiceMapper.getAllInvoice();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    public int insertInvoice(@RequestBody Invoice user) {
        try {
            invoiceMapper.insertInvoice(user);
            return user.getId();
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public Boolean deleteInvoice(@RequestBody Integer id) {
        try {
            invoiceMapper.deleteInvoice(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @RequestMapping(value = "/findById",method = RequestMethod.POST)
    public Invoice findInvoiceById(@RequestBody Integer id) {
        try {
            return invoiceMapper.findInvoiceById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public Boolean updateInvoice(@RequestBody Invoice customer) {
        try {
            invoiceMapper.updateInvoice(customer);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @RequestMapping(value = "/findByUserId",method = RequestMethod.POST)
    public List<Invoice> findInvoiceByUserId(@RequestBody Integer id) {
        try{
            return invoiceMapper.findInvoiceByUserId(id);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value = "/findByCustomerId",method = RequestMethod.POST)
    public List<Invoice> findInvoiceByCustomerId(@RequestBody Integer id) {
        try{
            return invoiceMapper.findInvoiceByCustomerId(id);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value = "/findByGoodId",method = RequestMethod.POST)
    public List<Invoice> findInvoiceByGoodId(@RequestBody Integer id) {
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


//    public List<Invoice> findInvoiceByTime(Timestamp start, Timestamp end) {
//        try{
//            return invoiceMapper.findInvoiceByTimestamp(start,end);
//        }catch (Exception e){
//            e.printStackTrace();
//            return null;
//        }
//    }

}
