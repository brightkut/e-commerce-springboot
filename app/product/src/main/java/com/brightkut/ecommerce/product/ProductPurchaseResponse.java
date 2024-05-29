package com.brightkut.ecommerce.product;

import java.math.BigDecimal;

public record ProductPurchaseResponse(
        Integer id,
        String name,
        String description,
        Double availableQuantity,
        BigDecimal price
) {
}
