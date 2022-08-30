package com.acme.deliveryservice.domain;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "product")
@Getter
@Setter
@ToString(callSuper = true)
public class Product extends BaseModel {

    @Column
    @NotBlank
    private String productName ;

    @Column
    @NotNull
    private Double price ;

    @Column
    @NotNull
    private Boolean isInStock = true;


}