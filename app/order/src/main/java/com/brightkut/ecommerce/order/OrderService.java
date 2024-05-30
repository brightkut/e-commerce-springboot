package com.brightkut.ecommerce.order;

import com.brightkut.ecommerce.customer.CustomerClient;
import com.brightkut.ecommerce.orderline.OrderLineRequest;
import com.brightkut.ecommerce.orderline.OrderLineService;
import com.brightkut.ecommerce.product.ProductClient;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderRepository orderRepository;
    private final OrderFactory orderFactory;
    private final OrderLineService orderLineService;

    public OrderService(
            CustomerClient customerClient,
            ProductClient productClient,
            OrderRepository orderRepository,
            OrderFactory orderFactory,
            OrderLineService orderLineService
    ) {
        this.customerClient = customerClient;
        this.productClient = productClient;
        this.orderRepository = orderRepository;
        this.orderFactory = orderFactory;
        this.orderLineService = orderLineService;
    }

    public Integer createOrder(OrderRequest request) {
        // check the customer (Feign)
        var customer = customerClient.getCustomerById(request.customerId())
                .orElseThrow(()-> new BusinessException("Can not create order :: No customer exist with provided id"));

        // purchase the product --> product-ms (Rest Template)
        productClient.purchaseProducts(request.products());

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

        return null;
    }
}
