package com.uyghur.java.userauth.repository;

import com.uyghur.java.userauth.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByProductName(String productName);
}
