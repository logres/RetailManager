package com.hit.logres.provider.serviceimplement;

import com.hit.logres.api.entity.Payment;
import com.hit.logres.provider.mapper.PaymentMapper;
import com.hit.logres.api.service.PaymentService;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@org.springframework.stereotype.Service("PaymentService")
@DubboService
public class PaymentServiceImp implements PaymentService {

    @Autowired
    PaymentMapper paymentMapper;

    @Override
    public List<Payment> getAllPayment() {
        try {
            return paymentMapper.getAllPayment();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int insertPayment(Payment user) {
        try {
            paymentMapper.insertPayment(user);
            return user.getId();
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public Boolean deletePayment(Integer id) {
        try {
            paymentMapper.deletePayment(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Payment findPaymentById(Integer id) {
        try {
            return paymentMapper.findPaymentById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Boolean updatePayment(Payment customer) {
        try {
            paymentMapper.updatePayment(customer);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Payment> findPaymentByInvoiceId(Integer id) {
        try {
            return paymentMapper.findPaymentByInvoiceId(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}