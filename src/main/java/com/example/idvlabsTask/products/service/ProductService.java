package com.example.idvlabsTask.products.service;

import com.example.idvlabsTask.common.response.MessageResponse;
import com.example.idvlabsTask.common.response.ResponseType;
import com.example.idvlabsTask.products.entity.Product;
import com.example.idvlabsTask.products.repository.ProductRepository;
import javax.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public MessageResponse addProduct(Product product) {

        productRepository.save(product);
        return new MessageResponse(ResponseType.SUCCESS, "SUCCESS");
    }

    public MessageResponse deleteProductById(Long id){
        productRepository.deleteById(id);
        return new
                MessageResponse(ResponseType.SUCCESS,"Deleted");
    }

    @GetMapping("{id}")
    public Product getById(@PathVariable Long id) {
        return productRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("No records find"));
    }

    public MessageResponse updateProduct(Long id, Product updateProduct){
        Product product = productRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("" +
                        "Product not found"));
        product.update(updateProduct);
        productRepository.save(product);
        return new MessageResponse(ResponseType.SUCCESS,"Updated");
    }

    public List<Product> getAllProducts() {
        List<Product> listProduct = productRepository.findAll();
        return listProduct;
    }

}