package com.brightkut.ecommerce.rest.internal.model;

import com.brightkut.ecommerce.order.model.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        CustomerResponse customer
) {
}
