package com.acme.deliveryservice.domain;

import lombok.*;
import javax.validation.constraints.NotBlank;
import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "customer")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class Customer extends BaseModel {

    @Column
    @NotBlank
    private String firstName ;

    @Column
    @NotBlank
    private  String lastName;

    @Column
    private String emailAddress;

    @Column
    private String address;

    @Column
    private String city;

    @Column
    private String country;

    @Column
    private String phoneNumber;

    @Column
    @NotNull
    private Boolean active;



}