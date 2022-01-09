package com.hit.logres.phoneproject.provider.controller;

import com.hit.logres.phoneproject.api.entity.Payment;
import com.hit.logres.phoneproject.provider.mapper.PaymentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping(value = "/Payment")
public class PaymentController {
    @Autowired
    PaymentMapper paymentMapper;

    @RequestMapping(value = "/getAll",method = RequestMethod.GET)
    public List<Payment> getAllPayment() {
        try {
            return paymentMapper.getAllPayment();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    public int insertPayment(@RequestBody Payment payment) {
        try {
            System.out.println(payment.getInvoiceId());
            System.out.println(payment.getId());
            System.out.println(payment.getAmount());
            System.out.println(payment.getTime());
            paymentMapper.insertPayment(payment);
            return payment.getId();
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public Boolean deletePayment(@RequestBody Integer id) {
        try {
            paymentMapper.deletePayment(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @RequestMapping(value = "/findById",method = RequestMethod.POST)
    public Payment findPaymentById(@RequestBody Integer id) {
        try {
            return paymentMapper.findPaymentById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public Boolean updatePayment(@RequestBody Payment payment) {
        try {
            paymentMapper.updatePayment(payment);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @RequestMapping(value = "/findByInvoiceId",method = RequestMethod.POST)
    public List<Payment> findPaymentByInvoiceId(@RequestBody Integer id) {
        try {
            return paymentMapper.findPaymentByInvoiceId(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
