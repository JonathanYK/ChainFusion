package com.jonathanyk.chainfusion.service;

import com.jonathanyk.chainfusion.dto.ProductRequest;
import com.jonathanyk.chainfusion.dto.ProductResponse;
import com.jonathanyk.chainfusion.exception.ResourceNotFoundException;
import com.jonathanyk.chainfusion.model.Product;
import com.jonathanyk.chainfusion.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService implements IProductService {

    private final ProductRepository productRepository;

    public void createProduct(ProductRequest productRequest) {
        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();

        productRepository.save(product);
        log.info("product id: {} saved", product.getId());
    }

    public List<ProductResponse> getAllProducts() {
        List<Product> allProducts = productRepository.findAll();
        return allProducts.stream().map(product -> mapToProductResponse(product)).toList();
    }
    @Override
    public ProductResponse getProductById(String productId) {
        Optional<Product> desiredProduct = Optional.ofNullable(
                productRepository.findById(productId).orElseThrow(() ->
                        new ResourceNotFoundException("Product not found with id: " + productId)));

        return mapToProductResponse(desiredProduct.get());

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
