package com.brightkut.ecommerce.orderline;

import com.brightkut.ecommerce.order.Order;
import org.springframework.stereotype.Service;

@Service
public class OrderLineFactory {

    public OrderLine buildOrderLine(OrderLineRequest request) {
        return new OrderLine()
                .setId(request.id())
                .setProductId(request.productId())
                .setOrder(new Order().setId(request.orderId()))
                .setQuantity(request.quantity());
    }
}
