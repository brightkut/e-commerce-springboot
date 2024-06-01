package com.brightkut.ecommerce.payment.dto;

import com.brightkut.ecommerce.payment.model.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(
    Integer id,
    BigDecimal amount,
    PaymentMethod paymentMethod,
    Integer orderId,
    String orderReference,
    Customer customer
) {
}
