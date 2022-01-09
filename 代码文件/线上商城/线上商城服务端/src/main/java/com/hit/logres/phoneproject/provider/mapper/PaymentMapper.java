package com.hit.logres.phoneproject.provider.mapper;




import com.hit.logres.phoneproject.api.entity.Payment;

import java.util.List;

public interface PaymentMapper {
    void insertPayment(Payment payment) throws Exception;

    void deletePayment(Integer id) throws Exception;

    Payment findPaymentById(Integer id) throws Exception;

    List<Payment> getAllPayment() throws Exception;

    void updatePayment(Payment payment) throws Exception;

    List<Payment> findPaymentByInvoiceId(Integer id) throws Exception;

}
