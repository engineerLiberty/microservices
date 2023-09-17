package com.engineerLiberty.orderservice.service;
import com.engineerLiberty.orderservice.dto.InventoryResponse;
import com.engineerLiberty.orderservice.dto.OderLineItemDto;
import com.engineerLiberty.orderservice.dto.OrderRequest;
import com.engineerLiberty.orderservice.model.Order;
import com.engineerLiberty.orderservice.model.OrderLineItems;
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

    private final WebClient webClient;
    public void placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        List<OrderLineItems> orderLineItemsList = orderRequest.getOderLineItemDto().stream()
                .map(this::mapToOrderLineItem)
                .toList();
        order.setOrderLineItemsList(orderLineItemsList);

        log.info("OderNumber {}",order.getOrderNumber());

      List<String> skuCodes = order.getOrderLineItemsList().stream().map(OrderLineItems::getSkuCode).toList();

      InventoryResponse[] inventoryResponseArray = webClient.get()
               .uri("http://localhost:8081/api/inventory",
                       uriBuilder -> uriBuilder
                                       .queryParam("skuCode", skuCodes)
                                       .build())
               .retrieve()
               .bodyToMono(InventoryResponse[].class)
               .block();

        assert inventoryResponseArray != null;
        boolean allProductIsInStock = Arrays.stream(inventoryResponseArray).allMatch(InventoryResponse::isInStock);
          if (allProductIsInStock) {
              log.info("Order booked");
              orderRepository.save(order);
          } else {
              throw new IllegalArgumentException("Order not in stock");
          }
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
