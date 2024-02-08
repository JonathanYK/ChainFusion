package com.jonathanyk.chainfusion.service;

import com.jonathanyk.chainfusion.dto.ProductRequest;
import com.jonathanyk.chainfusion.dto.ProductResponse;

import java.util.List;

public interface IProductService {

    void createProduct(ProductRequest productRequest);
    List<ProductResponse> getAllProducts();
    ProductResponse getProductById(String productId);

    }
