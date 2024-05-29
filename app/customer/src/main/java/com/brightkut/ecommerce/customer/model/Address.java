package com.brightkut.ecommerce.customer.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Address {
    private String street;
    private String houseNumber;
    private String zipCode;
}
