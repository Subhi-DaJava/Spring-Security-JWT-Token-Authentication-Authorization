package com.uyghur.java.userauth.controller;

import com.uyghur.java.userauth.domain.Product;
import com.uyghur.java.userauth.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getProducts());
    }

    @PostMapping("/save")
    //@PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<Product> saveProduct(@RequestBody @Valid Product product) {
        return new ResponseEntity<>(productService.saveProduct(product), HttpStatus.CREATED);
    }

    @GetMapping("/product-by-id/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<Product> getById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @GetMapping("/product-by-name/{productName}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<Product> getByProductName(@PathVariable String productName) {
        return ResponseEntity.ok(productService.findByProductName(productName));
    }
}
