package com.brightkut.ecommerce.customer.model.entity;

import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Accessors(chain = true)
@Document
public class Customer {
    @Id
    private String id;
    private String firstname;
    private String lastname;
    private String email;
    private Address address;
}
