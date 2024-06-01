package com.brightkut.ecommerce.kafka.model;

import com.brightkut.ecommerce.payment.model.PaymentMethod;

import java.math.BigDecimal;

public record PaymentNotification(
        String orderReference,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        String customerFirstname,
        String customerLastname,
        String customerEmail
) {
}
