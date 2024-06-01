package com.brightkut.ecommerce.payment.service;

import com.brightkut.ecommerce.kafka.model.PaymentNotification;
import com.brightkut.ecommerce.kafka.PaymentProducer;
import com.brightkut.ecommerce.payment.factory.PaymentFactory;
import com.brightkut.ecommerce.payment.dto.PaymentRequest;
import com.brightkut.ecommerce.payment.repository.PaymentRepository;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentFactory paymentFactory;
    private final PaymentProducer paymentProducer;

    public PaymentService(PaymentRepository paymentRepository, PaymentFactory paymentFactory, PaymentProducer paymentProducer) {
        this.paymentRepository = paymentRepository;
        this.paymentFactory = paymentFactory;
        this.paymentProducer = paymentProducer;
    }

    public Integer createPayment(PaymentRequest request) {
        var payment = paymentRepository.save(paymentFactory.buildPayment(request));

        paymentProducer.sendPaymentNotification(
                new PaymentNotification(
                        request.orderReference(),
                        request.amount(),
                        request.paymentMethod(),
                        request.customer().firstname(),
                        request.customer().lastname(),
                        request.customer().email()
                )
        );
        return null;
    }
}
