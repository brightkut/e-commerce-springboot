package com.brightkut.ecommerce.order;

import org.springframework.stereotype.Service;

@Service
public class OrderFactory {
    public Order buildOrder(OrderRequest request) {
        return new Order()
                .setId(request.id())
                .setTotalAmount(request.amount())
                .setPaymentMethod(request.paymentMethod())
                .setReference(request.reference())
                .setCustomerId(request.customerId());
    }
}
