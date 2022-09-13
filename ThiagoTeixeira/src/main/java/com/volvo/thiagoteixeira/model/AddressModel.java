package com.volvo.thiagoteixeira.model;

import com.volvo.thiagoteixeira.model.id.AddressId;
import lombok.Data;

import javax.persistence.*;

@Table
@Data
@Entity
@IdClass(AddressId.class)
public class AddressModel {

    @Id
    private String zipCode;

    @Id
    private String number;

}
