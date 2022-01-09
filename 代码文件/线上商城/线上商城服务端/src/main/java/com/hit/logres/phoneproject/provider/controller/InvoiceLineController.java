package com.hit.logres.phoneproject.provider.controller;

import com.hit.logres.phoneproject.api.entity.InvoiceLine;
import com.hit.logres.phoneproject.provider.mapper.InvoiceLineMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/InvoiceLine")
public class InvoiceLineController {
    @Autowired
    InvoiceLineMapper invoicelineMapper;

    @RequestMapping(value = "/getAll",method = RequestMethod.GET)
    public List<InvoiceLine> getAllInvoiceLine() {
        try {
            return invoicelineMapper.getAllInvoiceLine();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    public int insertInvoiceLine(@RequestBody InvoiceLine user) {
        try {
            invoicelineMapper.insertInvoiceLine(user);
            return user.getId();
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public Boolean deleteInvoiceLine(@RequestBody Integer id) {
        try {
            invoicelineMapper.deleteInvoiceLine(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @RequestMapping(value = "/findById",method = RequestMethod.POST)
    public InvoiceLine findInvoiceLineById(@RequestBody Integer id) {
        try {
            return invoicelineMapper.findInvoiceLineById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public Boolean updateInvoiceLine(@RequestBody InvoiceLine customer) {
        try {
            invoicelineMapper.updateInvoiceLine(customer);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @RequestMapping(value = "/findByInvoiceId",method = RequestMethod.POST)
    public List<InvoiceLine> findInvoiceLineByInvoiceId(@RequestBody Integer id) {
        try {
            return invoicelineMapper.findInvoiceLineByInvoiceId(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value = "/findByGoodId",method = RequestMethod.POST)
    public List<InvoiceLine> findInvoiceLineByGoodId(@RequestBody Integer id) {
        try{
            return invoicelineMapper.findInvoiceLineByGoodId(id);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
