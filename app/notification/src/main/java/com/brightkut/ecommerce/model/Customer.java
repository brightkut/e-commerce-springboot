package com.brightkut.ecommerce.model;

public record Customer(
        String id,
        String firstname,
        String lastname,
        String email
) {
}
