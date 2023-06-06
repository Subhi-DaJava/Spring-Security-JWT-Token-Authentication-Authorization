package com.uyghur.java.userauth.service.impl;

import com.uyghur.java.userauth.domain.Product;
import com.uyghur.java.userauth.exception.ProductNotFoundException;
import com.uyghur.java.userauth.repository.ProductRepository;
import com.uyghur.java.userauth.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository repository;
    @Override
    public Product getProductById(Long id) {
        Product productById = repository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with the given id:{%d}".formatted(id)));
        log.info("Product has been successfully retrieved in ProductServiceImpl");
        return productById;
    }

    @Override
    public Product findByProductName(String productName) {
        Product productByName = repository.findByProductName(productName)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with the given product name:{%s}".formatted(productName)));
        log.info("Product has been successfully retrieved in ProductServiceImpl");
        return productByName;
    }

    @Override
    public List<Product> getProducts() {
        return repository.findAll();
    }

    @Override
    public Product saveProduct(Product product) {
        Product productSaved = repository.save(product);
        log.info("New Product with productName:{%s} has been successfully saved".formatted(product.getProductName()));
        return productSaved;
    }
}
