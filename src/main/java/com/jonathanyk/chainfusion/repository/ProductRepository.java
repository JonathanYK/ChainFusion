package com.jonathanyk.chainfusion.repository;

import com.jonathanyk.chainfusion.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
}
