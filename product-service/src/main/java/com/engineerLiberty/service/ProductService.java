package com.engineerLiberty.service;

import com.engineerLiberty.model.Product;
import com.engineerLiberty.reponse.ProductResponse;
import com.engineerLiberty.repository.ProductRepository;
import com.engineerLiberty.request.ProductRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    public void addNewProduct(ProductRequest productRequest) {
        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();
            productRepository.save(product);
    }

    public List<ProductResponse> findAllProduct() {
        List<Product> productResponseList = productRepository.findAll();
      return   productResponseList.stream().map(this::mapToProductResponse).toList();
    }

    private ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .description(product.getDescription())
                .name(product.getName())
                .price(product.getPrice())
                .build();
    }
}
