package com.brightkut.ecommerce.rest.internal;

import com.brightkut.ecommerce.rest.internal.model.CustomerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(
        name = "customer-service",
        url = "${application.config.customer-url}"
)
public interface CustomerClient {
    @GetMapping("/{customerId}")
    Optional<CustomerResponse> getCustomerById(@PathVariable String customerId);
}
