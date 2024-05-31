package com.brightkut.ecommerce.order.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record PurchaseRequest(
        @NotNull(message = "Product id is required")
        Integer productId,
        @Positive(message = "Quantity should be positive")
        Double quantity
) {
}
