package com.engineerLiberty.repository;


import com.engineerLiberty.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
