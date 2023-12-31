package com.engineerLiberty.service;

import com.engineerLiberty.model.Product;
import com.engineerLiberty.reponse.ProductResponse;
//import com.engineerLiberty.repository.ProductRepository;
import com.engineerLiberty.repository.ProductRepository;
import com.engineerLiberty.request.ProductRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Log4j2
public class ProductService {
    private final ProductRepository productRepository;
    public String addNewProduct(ProductRequest productRequest) {
        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();
            productRepository.save(product);
            return "Data saved to Database";
    }

    public List<ProductResponse> findAllProduct() {
        List<Product> productResponseList = productRepository.findAll();
      return   productResponseList.stream().map(this::mapToProductResponse).toList();
    }

    private ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder()
                .id(String.valueOf(product.getId()))
                .description(product.getDescription())
                .name(product.getName())
                .price(product.getPrice())
                .build();
    }
}
