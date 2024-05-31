package com.brightkut.ecommerce.order.dto;

import com.brightkut.ecommerce.order.model.PaymentMethod;

import java.math.BigDecimal;

public record OrderResponse(
        Integer id,
        String reference,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        String customerId
) {
}
