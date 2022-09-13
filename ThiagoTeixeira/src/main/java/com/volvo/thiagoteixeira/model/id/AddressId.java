package com.volvo.thiagoteixeira.model.id;

import lombok.Data;

import java.io.Serializable;

@Data
public class AddressId implements Serializable {
    private String zipCode;
    private String number;

    public AddressId(String zipCode, String number) {
        this.zipCode = zipCode;
        this.number = number;
    }

    public AddressId() {
    }
}
