package com.brightkut.ecommerce.orderline;

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
