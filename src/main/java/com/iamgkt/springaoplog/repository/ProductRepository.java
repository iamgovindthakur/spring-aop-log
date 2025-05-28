package com.iamgkt.springaoplog.repository;

import com.iamgkt.springaoplog.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {}
