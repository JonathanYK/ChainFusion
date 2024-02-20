package com.jonathanyk.ProductService.service;

import com.jonathanyk.ProductService.dto.ProductRequest;
import com.jonathanyk.ProductService.dto.ProductResponse;

import java.util.List;

public interface IProductService {

    String createProduct(ProductRequest productRequest);
    List<ProductResponse> getAllProducts();
    ProductResponse getProductById(String productId);

    }
