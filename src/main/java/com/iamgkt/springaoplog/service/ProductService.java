package com.iamgkt.springaoplog.service;

import com.iamgkt.springaoplog.entity.Product;
import com.iamgkt.springaoplog.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;


    // Create or Update a Product
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    // Retrieve all Products
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Retrieve a Product by ID
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    // Delete a Product by ID
    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }
}