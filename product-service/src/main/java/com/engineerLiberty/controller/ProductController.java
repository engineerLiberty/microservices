package com.engineerLiberty.controller;

import com.engineerLiberty.reponse.ProductResponse;
import com.engineerLiberty.request.ProductRequest;
import com.engineerLiberty.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    @RequestMapping("create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody ProductRequest productRequest) {
        productService.addNewProduct(productRequest);
    }

    @GetMapping("/findProduct")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> findAll() {
        return productService.findAllProduct();
    }
}
