package com.engineerLiberty.orderservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
@Entity
@Table(name = "t_orderLineItems")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderLineItems {
    @Id
    private Long id;
    private String skuCode;
    private BigDecimal price;
    private Integer quantity;
}
