package com.brightkut.ecommerce.rest.internal.model;

public record CustomerResponse(
        String id,
        String firstname,
        String lastname,
        String email
) {
}
