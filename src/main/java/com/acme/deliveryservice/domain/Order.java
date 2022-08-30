package com.acme.deliveryservice.domain;


import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ACMEORDER")
public class Order extends BaseModel {
    @ManyToOne
    @JoinColumn(name = "CUSTOMER_ID")
    private Customer customer;

    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Date submitDate;

    @ToString.Exclude
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
    private Set<OrderItem> orderItems;


    @Column
    private String paymentMethod;

    @Column
    private BigDecimal cost;


    @Column
    private boolean status;
}