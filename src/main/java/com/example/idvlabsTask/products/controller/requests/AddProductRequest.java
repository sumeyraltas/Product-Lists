package com.example.idvlabsTask.products.controller.requests;

import com.example.idvlabsTask.products.entity.Product;

public record AddProductRequest(
        String productType,
        Integer availableSlots,
        String productName
) {
    public Product toDomainEntity() {
        return new Product(productName, productType, availableSlots);
    }
}
