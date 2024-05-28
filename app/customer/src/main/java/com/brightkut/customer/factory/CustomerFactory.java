package com.brightkut.customer.factory;

import com.brightkut.customer.model.entity.Customer;
import com.brightkut.customer.model.dto.CustomerRequest;
import com.brightkut.customer.model.dto.CustomerResponse;
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
