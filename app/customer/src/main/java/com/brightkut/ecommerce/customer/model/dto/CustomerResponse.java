package com.brightkut.ecommerce.customer.model.dto;

import com.brightkut.ecommerce.customer.model.entity.Address;

public record CustomerResponse (
        String id,
        String firstname,
        String lastname,
        String email,
        Address address
){
}
