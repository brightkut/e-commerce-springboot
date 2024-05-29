package com.brightkut.ecommerce.customer.dto;

import com.brightkut.ecommerce.customer.model.Address;

public record CustomerResponse (
        String id,
        String firstname,
        String lastname,
        String email,
        Address address
){
}
