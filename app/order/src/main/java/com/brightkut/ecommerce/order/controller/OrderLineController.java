package com.brightkut.ecommerce.order.controller;

import com.brightkut.ecommerce.order.dto.OrderLineResponse;
import com.brightkut.ecommerce.order.service.OrderLineService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order-lines")
public class OrderLineController {
    private final OrderLineService orderLineService;

    public OrderLineController(OrderLineService orderLineService) {
        this.orderLineService = orderLineService;
    }

    @GetMapping("/orders/{orderId}")
    public ResponseEntity<List<OrderLineResponse>> getOrderLineByOrderId(@PathVariable Integer orderId) {
        return ResponseEntity.ok(orderLineService.getOrderLineById(orderId));
    }
}
