package com.iamgkt.springaoplog.util;

import com.iamgkt.springaoplog.entity.Product;
import com.iamgkt.springaoplog.service.ProductService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MockDataLoader {

  @Bean
  public CommandLineRunner loadMockData(ProductService productService) {
    return args -> {
        productService.saveProduct(new Product(null, "Product A", "Description A", 10.0, 100));
        productService.saveProduct(new Product(null, "Product B", "Description B", 20.0, 200));
        productService.saveProduct(new Product(null, "Product C", "Description C", 30.0, 300));
    };
  }
}
