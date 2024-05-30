package com.brightkut.ecommerce.order;

import com.brightkut.ecommerce.customer.CustomerClient;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private final CustomerClient customerClient;

    public OrderService(CustomerClient customerClient) {
        this.customerClient = customerClient;
    }

    public Integer createOrder(OrderRequest request) {
        // check the customer
        var customer = customerClient.getCustomerById(request.customerId())
                .orElseThrow(()-> new BusinessException("Can not create order :: No customer exist with provided id"));

        // purchase the product --> product-ms

        // persist order

        // persist order lines

        // start payment process

        // send order confirmation --> notification-ms (kafka)

        return null;
    }
}
