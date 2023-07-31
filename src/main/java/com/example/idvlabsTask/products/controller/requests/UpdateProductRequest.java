package com.example.idvlabsTask.products.controller.requests;


import com.example.idvlabsTask.products.entity.Product;

public record UpdateProductRequest(
        Integer availableSlots,
        String productName
) {
    public Product toDomainEntity() {
        return new Product(availableSlots, productName);
    }
}
