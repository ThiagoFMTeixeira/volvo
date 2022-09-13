package com.volvo.thiagoteixeira.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Table
@Entity
public class CustomerModel {

    @Id
    @Column(unique = true)
    private String document;
    private String name;
    private Integer age;
    private LocalDateTime registrationTime;
    private LocalDateTime lastUpdateTime;

    @ManyToMany
    Set<AddressModel> addresses;

}
