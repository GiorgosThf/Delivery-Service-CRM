package com.acme.deliveryservice.domain;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;


@Getter
@Setter
@MappedSuperclass
@ToString
public abstract class BaseModel implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

}
