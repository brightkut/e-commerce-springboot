package com.brightkut.ecommerce.kafka.model;

import com.brightkut.ecommerce.rest.internal.model.CustomerResponse;
import com.brightkut.ecommerce.order.model.PaymentMethod;
import com.brightkut.ecommerce.rest.internal.model.PurchaseResponse;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customer,
        List<PurchaseResponse> products
) {
}
