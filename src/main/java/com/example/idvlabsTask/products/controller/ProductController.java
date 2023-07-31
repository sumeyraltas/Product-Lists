package com.example.idvlabsTask.products.controller;

import com.example.idvlabsTask.common.response.MessageResponse;
import com.example.idvlabsTask.products.controller.requests.AddProductRequest;
import com.example.idvlabsTask.products.controller.requests.UpdateProductRequest;
import com.example.idvlabsTask.products.controller.responses.ListProductResponse;
import com.example.idvlabsTask.products.service.ProductService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
@Validated
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public MessageResponse addProduct(@Valid @RequestBody AddProductRequest addRoomsRequest) {
        return productService.addProduct(addRoomsRequest.toDomainEntity());
    }

    @PutMapping("{id}")
    public MessageResponse updateProduct(@Valid @RequestBody UpdateProductRequest updateProductRequest, @PathVariable Long id) {
        return productService.updateProduct(id, updateProductRequest.toDomainEntity());
    }

    @DeleteMapping("{id}")
    public MessageResponse deleteProduct(@PathVariable Long id) {
        return productService.deleteProductById(id);
    }

    @GetMapping("{id}")
    public ListProductResponse getProductById(@PathVariable Long id) {
        return new ListProductResponse(productService.getById(id));
    }

    @GetMapping
    public List<ListProductResponse> getAllProduct() {
        return productService.getAllProducts()
                .stream()
                .map(product -> new ListProductResponse(product))
                .toList();
    }
}
