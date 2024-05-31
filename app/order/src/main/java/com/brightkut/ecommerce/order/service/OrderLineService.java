package com.brightkut.ecommerce.order.service;

import com.brightkut.ecommerce.order.factory.OrderLineFactory;
import com.brightkut.ecommerce.order.repository.OrderLineRepository;
import com.brightkut.ecommerce.order.dto.OrderLineRequest;
import org.springframework.stereotype.Service;

@Service
public class OrderLineService {

    private final OrderLineRepository orderLineRepository;
    private final OrderLineFactory orderLineFactory;

    public OrderLineService(OrderLineRepository orderLineRepository, OrderLineFactory orderLineFactory) {
        this.orderLineRepository = orderLineRepository;
        this.orderLineFactory = orderLineFactory;
    }

    public Integer saveOrderLine(OrderLineRequest request) {
        var order = orderLineFactory.buildOrderLine(request);

        return orderLineRepository.save(order).getId();
    }
}
