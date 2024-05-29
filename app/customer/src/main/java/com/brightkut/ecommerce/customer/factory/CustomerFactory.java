package com.brightkut.ecommerce.customer.factory;

import com.brightkut.ecommerce.customer.dto.CustomerResponse;
import com.brightkut.ecommerce.customer.entity.Customer;
import com.brightkut.ecommerce.customer.dto.CustomerRequest;
import org.springframework.stereotype.Service;

@Service
public class CustomerFactory {

    public Customer buildCustomer(CustomerRequest request) {
        if(request == null) {
            return null;
        }

        return new Customer()
                .setId(request.id())
                .setFirstname(request.firstname())
                .setLastname(request.lastname())
                .setEmail(request.email())
                .setAddress(request.address());
    }

    public CustomerResponse fromCustomer(Customer customer) {
        return new CustomerResponse(
                customer.getId(),
                customer.getFirstname(),
                customer.getLastname(),
                customer.getEmail(),
                customer.getAddress()
        );
    }
}
