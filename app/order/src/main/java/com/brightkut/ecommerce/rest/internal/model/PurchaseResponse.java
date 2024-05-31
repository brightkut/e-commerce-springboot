package com.brightkut.ecommerce.rest.internal.model;

import java.math.BigDecimal;

public record PurchaseResponse(
        Integer id,
        String name,
        String description,
        Double availableQuantity,
        BigDecimal price
) {
}
