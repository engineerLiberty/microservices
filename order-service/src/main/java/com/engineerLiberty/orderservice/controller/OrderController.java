package com.engineerLiberty.orderservice.controller;

import com.engineerLiberty.orderservice.dto.OrderRequest;
import com.engineerLiberty.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("placeOrder")
    public String placeOder(@RequestBody OrderRequest oderRequest) {
        orderService.placeOrder(oderRequest);
        return "Order placed successful";
    }
}
