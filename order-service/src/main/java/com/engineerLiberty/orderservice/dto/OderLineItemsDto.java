package com.engineerLiberty.orderservice.dto;

import lombok.*;

import java.math.BigDecimal;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OderLineItemsDto {
    private String skuCode;
    private BigDecimal price;
    private Integer quantity;
}
