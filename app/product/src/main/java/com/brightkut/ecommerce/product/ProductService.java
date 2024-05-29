package com.brightkut.ecommerce.product;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductFactory productFactory;

    public ProductService(ProductRepository productRepository, ProductFactory productFactory) {
        this.productRepository = productRepository;
        this.productFactory = productFactory;
    }

    public Integer createProduct(ProductRequest request) {
        return null;
    }

    public List<ProductPurchaseResponse> purchaseProduct() {
        return null;
    }

    public ProductResponse findProductById() {
        return null;
    }

    public List<ProductResponse> findAllProducts() {
        return null;
    }
}
