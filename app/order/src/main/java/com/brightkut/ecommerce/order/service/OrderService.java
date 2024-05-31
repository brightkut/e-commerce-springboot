package com.brightkut.ecommerce.order.service;

import com.brightkut.ecommerce.order.dto.OrderResponse;
import com.brightkut.ecommerce.rest.internal.CustomerClient;
import com.brightkut.ecommerce.exception.BusinessException;
import com.brightkut.ecommerce.kafka.model.OrderConfirmation;
import com.brightkut.ecommerce.kafka.OrderProducer;
import com.brightkut.ecommerce.order.factory.OrderFactory;
import com.brightkut.ecommerce.order.repository.OrderRepository;
import com.brightkut.ecommerce.order.dto.OrderRequest;
import com.brightkut.ecommerce.order.dto.PurchaseRequest;
import com.brightkut.ecommerce.order.dto.OrderLineRequest;
import com.brightkut.ecommerce.rest.internal.ProductClient;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderRepository orderRepository;
    private final OrderFactory orderFactory;
    private final OrderLineService orderLineService;
    private final OrderProducer orderProducer;

    public OrderService(
            CustomerClient customerClient,
            ProductClient productClient,
            OrderRepository orderRepository,
            OrderFactory orderFactory,
            OrderLineService orderLineService,
            OrderProducer orderProducer
    ) {
        this.customerClient = customerClient;
        this.productClient = productClient;
        this.orderRepository = orderRepository;
        this.orderFactory = orderFactory;
        this.orderLineService = orderLineService;
        this.orderProducer = orderProducer;
    }

    public Integer createOrder(OrderRequest request) {
        // check the customer (Feign)
        var customer = customerClient.getCustomerById(request.customerId())
                .orElseThrow(()-> new BusinessException("Can not create order :: No customer exist with provided id"));

        // purchase the product --> product-ms (Rest Template)
        var purchasedProducts = productClient.purchaseProducts(request.products());

        // persist order
        var order = orderRepository.save(orderFactory.buildOrder(request));

        // persist order lines
        for (PurchaseRequest purchaseRequest: request.products()){
            orderLineService.saveOrderLine(
                    new OrderLineRequest(
                            null,
                            order.getId(),
                            purchaseRequest.productId(),
                            purchaseRequest.quantity()
                    )
            );
        }

        // start payment process

        // send order confirmation --> notification-ms (kafka)
        orderProducer.sendOrderConfirmation(
                new OrderConfirmation(
                        request.reference(),
                        request.amount(),
                        request.paymentMethod(),
                        customer,
                        purchasedProducts
                )
        );

        return order.getId();
    }

    public List<OrderResponse> getAllOrders() {
        return orderRepository.findAll()
                .stream()
                .map(orderFactory::fromOrder)
                .toList();
    }

    public OrderResponse getOrderById(Integer orderId) {
        return orderRepository.findById(orderId)
                .map(orderFactory::fromOrder)
                .orElseThrow(()->new EntityNotFoundException("No order found with id " + orderId));
    }
}
