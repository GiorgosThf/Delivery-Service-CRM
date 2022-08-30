package com.acme.deliveryservice.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
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
    @Email(regexp = ".+@.+\\..+")
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

    @OneToOne
    private User user;


}