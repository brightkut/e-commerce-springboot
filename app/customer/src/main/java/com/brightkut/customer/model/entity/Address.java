package com.brightkut.customer.model.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Accessors(chain = true)
@Document
public class Address {
    private String street;
    private String houseNumber;
    private String zipCode;
}
