package com.brightkut.ecommerce.payment.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record Customer(
       String id,
       @NotNull(message = "firstname is required")
       String firstname,
       @NotNull(message = "lastname is required")
       String lastname,
       @NotNull(message = "email is required")
       @Email(message = "email is invalid format")
       String email
) {
}
