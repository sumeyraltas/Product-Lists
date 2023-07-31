package com.example.idvlabsTask.products.controller.responses;

import com.example.idvlabsTask.products.entity.Product;

public  record ListProductResponse(
        Long id,
        String productType,
        Integer availableSlots,
        String productName
) {

public ListProductResponse(Product product) {
        this(product.getId(),
                product.getProductType(), product.getAvailableSlots(), product.getProductName()
        );
        }
}