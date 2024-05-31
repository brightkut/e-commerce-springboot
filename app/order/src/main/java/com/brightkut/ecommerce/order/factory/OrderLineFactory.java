package com.brightkut.ecommerce.order.factory;

import com.brightkut.ecommerce.order.entity.Order;
import com.brightkut.ecommerce.order.entity.OrderLine;
import com.brightkut.ecommerce.order.dto.OrderLineRequest;
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
