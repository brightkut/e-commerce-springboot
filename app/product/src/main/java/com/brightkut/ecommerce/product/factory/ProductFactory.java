package com.brightkut.ecommerce.product.factory;

import com.brightkut.ecommerce.category.Category;
import com.brightkut.ecommerce.product.dto.ProductPurchaseResponse;
import com.brightkut.ecommerce.product.dto.ProductRequest;
import com.brightkut.ecommerce.product.dto.ProductResponse;
import com.brightkut.ecommerce.product.entity.Product;
import org.springframework.stereotype.Service;

@Service
public class ProductFactory {

    public Product buildProduct(ProductRequest request) {
        return new Product()
                .setId(request.id())
                .setName(request.name())
                .setDescription(request.description())
                .setAvailableQuantity(request.availableQuantity())
                .setPrice(request.price())
                .setCategory(
                        new Category()
                                .setId(request.categoryId())
                );
    }

    public ProductResponse fromProduct(Product product) {
        return new ProductResponse(
                    product.getId(),
                    product.getName(),
                    product.getDescription(),
                    product.getAvailableQuantity(),
                    product.getPrice(),
                    product.getCategory().getId(),
                    product.getCategory().getName(),
                    product.getCategory().getDescription()
                );
    }

    public ProductPurchaseResponse toProductPurchaseResponse(Product product, Double quantity) {
        return new ProductPurchaseResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                quantity,
                product.getPrice()
        );
    }
}
