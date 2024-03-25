package com.jonathanyk.ProductService.service;

import com.jonathanyk.ProductService.dto.ProductRequest;
import com.jonathanyk.ProductService.dto.ProductResponse;
import com.jonathanyk.chainCommons.exception.ResourceNotFoundException;
import com.jonathanyk.ProductService.model.Product;
import com.jonathanyk.ProductService.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService implements IProductService {

    private final ProductRepository productRepository;

    public String createProduct(ProductRequest productRequest) {
        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();

        productRepository.save(product);

        String retString = String.format("product saved, its id: %s", product.getId());
        log.info(retString);
        return retString;
    }

    public List<ProductResponse> getAllProducts() {
        List<Product> allProducts = productRepository.findAll();
        return allProducts.stream().map(product -> mapToProductResponse(product)).toList();
    }
    @Override
    public ProductResponse getProductById(String productId) {
        Optional<Product> desiredProduct = productRepository.findById(productId);

        return desiredProduct.map(this::mapToProductResponse).orElseThrow( () ->
                new ResourceNotFoundException("Product not found with id: " + productId));
    }

    private ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }
}
