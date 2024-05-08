package com.site.ecommerce.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

import com.site.ecommerce.entities.Order;

public interface OrderRepository extends MongoRepository<Order, String> {}