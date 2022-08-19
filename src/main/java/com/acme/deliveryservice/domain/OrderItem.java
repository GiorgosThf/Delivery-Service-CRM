package com.acme.deliveryservice.domain;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ORDERITEM")
@SequenceGenerator(name = "idGenerator", sequenceName = "ORDER_ITEMS_SEQ")
public class OrderItem extends BaseModel {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @ToString.Exclude
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @ToString.Exclude
    private Order order;

    @Column(nullable = false)
    private Integer quantity;

    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal price;

    @Column(precision = 10, scale = 2)
    private BigDecimal totalCost;
}
