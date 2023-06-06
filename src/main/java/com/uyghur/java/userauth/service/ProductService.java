package com.uyghur.java.userauth.service;

import com.uyghur.java.userauth.domain.Product;

import java.util.List;

public interface ProductService {
    Product getProductById(Long id);
    Product findByProductName(String productName);
    List<Product> getProducts();
    Product saveProduct(Product product);

}
