package com.jonathanyk.chainfusion.controller;

import com.jonathanyk.chainfusion.dto.ProductRequest;
import com.jonathanyk.chainfusion.dto.ProductResponse;
import com.jonathanyk.chainfusion.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody ProductRequest productRequest) {
        productService.createProduct(productRequest);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getProducts(@RequestParam(required = false) String productId) {
        if (productId != null) return ResponseEntity.ok(productService.getProductById(productId));
        else return ResponseEntity.ok(productService.getAllProducts());
    }

}
