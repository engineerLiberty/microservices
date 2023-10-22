package com.engineerLiberty.orderservice.service;

import com.engineerLiberty.orderservice.dto.InventoryResponse;
import com.engineerLiberty.orderservice.dto.OderLineItemsDto;
import com.engineerLiberty.orderservice.dto.OrderRequest;
import com.engineerLiberty.orderservice.model.Orders;
import com.engineerLiberty.orderservice.model.OrderItem;
import com.engineerLiberty.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Log4j2
@Transactional
public class OrderService {
    private final OrderRepository orderRepository;

    private final WebClient.Builder webClientBuilder;

    public void placeOrder(OrderRequest orderRequest) {
        Orders order = new Orders();
        order.setOrderNumber(UUID.randomUUID().toString());
        List<OrderItem> orderLineItemsList = orderRequest.getOderLineItemsDto().stream().map(this::mapToOrderLineItem).toList();
        order.setOrderItems(orderLineItemsList);
        log.info("OderNumber {}", order.getOrderNumber());
        List<String> skuCodes = order.getOrderItems().stream().map(OrderItem::getSkuCode).toList();

        InventoryResponse[] inventoryResponseArray = webClientBuilder.build().get()
                .uri("http://inventory-service/api/inventory",
                        uriBuilder -> uriBuilder.queryParam("skuCode",skuCodes).build())
                .retrieve().bodyToMono(InventoryResponse[].class)
                .block();
        assert inventoryResponseArray != null;
        boolean isInStock = Arrays.stream(inventoryResponseArray).allMatch(InventoryResponse::isInStock);

        if (isInStock) {
            orderRepository.save(order);
        }
    }

    private OrderItem mapToOrderLineItem(OderLineItemsDto orderItems) {
        return OrderItem.builder()
                .price(orderItems.getPrice())
                .quantity(orderItems.getQuantity())
                .skuCode(orderItems.getSkuCode())
                .build();
    }
}