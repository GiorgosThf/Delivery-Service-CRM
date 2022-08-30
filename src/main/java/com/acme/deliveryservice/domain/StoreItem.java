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
@Table(name = "STOREITEM")
public class StoreItem extends BaseModel {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @ToString.Exclude
    private Product product;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @ToString.Exclude
    private Store store;

    @Column(nullable = false)
    private Integer stock;

    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal price;


}
