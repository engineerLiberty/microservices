package com.engineerLiberty.repository;

import com.engineerLiberty.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product,String> {
}
