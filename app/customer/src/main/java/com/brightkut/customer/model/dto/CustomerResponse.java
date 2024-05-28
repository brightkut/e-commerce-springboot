package com.brightkut.customer.model.dto;

import com.brightkut.customer.model.entity.Address;

public record CustomerResponse (
        String id,
        String firstname,
        String lastname,
        String email,
        Address address
){
}
