package com.brightkut.ecommerce.order.factory;

import com.brightkut.ecommerce.order.dto.OrderRequest;
import com.brightkut.ecommerce.order.dto.OrderResponse;
import com.brightkut.ecommerce.order.entity.Order;
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

    public OrderResponse fromOrder(Order order) {
        return new OrderResponse(
                order.getId(),
                order.getReference(),
                order.getTotalAmount(),
                order.getPaymentMethod(),
                order.getCustomerId()
        );
    }
}
