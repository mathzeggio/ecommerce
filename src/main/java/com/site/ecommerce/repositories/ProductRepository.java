package com.site.ecommerce.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

import com.site.ecommerce.entities.Product;

public interface ProductRepository extends MongoRepository<Product, String> {
    List<Product> findByName(String name);
}

