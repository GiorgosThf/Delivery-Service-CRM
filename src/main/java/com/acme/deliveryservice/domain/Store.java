package com.acme.deliveryservice.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@Table(name = "store")
@Getter
@Setter
@Builder
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Store extends BaseModel {

    @Column
    @NotBlank
    private String storeName;

    @Column
    private String emailAddress;

    @Column
    private String address;

    @Column
    private String city;

    @Column
    private String phoneNumber;

    @Column
    private Boolean isActive;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    @ToString.Exclude
    private Set<StoreItem> storeItems;


}