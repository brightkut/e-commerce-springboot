package com.brightkut.ecommerce.payment.factory;

import com.brightkut.ecommerce.payment.entity.Payment;
import com.brightkut.ecommerce.payment.dto.PaymentRequest;
import org.springframework.stereotype.Service;

@Service
public class PaymentFactory {

    public Payment buildPayment(PaymentRequest request) {
        return new Payment()
                .setId(request.id())
                .setAmount(request.amount())
                .setPaymentMethod(request.paymentMethod())
                .setOrderId(request.orderId());
    }
}
