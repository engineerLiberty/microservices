package com.engineerLiberty.orderservice.service;

import com.engineerLiberty.orderservice.config.WebclientConfig;
import com.engineerLiberty.orderservice.dto.OderLineItemDto;
import com.engineerLiberty.orderservice.dto.OrderRequest;
import com.engineerLiberty.orderservice.model.Order;
import com.engineerLiberty.orderservice.model.OrderLineItems;
import com.engineerLiberty.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
@Log4j2
@Transactional
public class OrderService {
    private final OrderRepository orderRepository;
    private final WebclientConfig webClient;
    public void placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        List<OrderLineItems> orderLineItemsList = orderRequest.getOderLineItemDto().stream()
                .map(this::mapToOrderLineItem)
                .toList();
        order.setOrderLineItemsList(orderLineItemsList);
List<InventoryResponse> inventoryResponses = webClient.webClient().uri("")
        .retrieve()
        .bodyToMono(InventoryResponse.class);
        log.info("OderNumber {}",order.getOrderNumber());
        orderRepository.save(order);
    }

    private OrderLineItems mapToOrderLineItem(OderLineItemDto orderLineItemDto) {
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setId(orderLineItemDto.getId());
        orderLineItems.setPrice(orderLineItemDto.getPrice());
        orderLineItems.setSkuCode(orderLineItemDto.getSkuCode());
        orderLineItems.setQuantity(orderLineItemDto.getQuantity());
        return orderLineItems;
    }
}
