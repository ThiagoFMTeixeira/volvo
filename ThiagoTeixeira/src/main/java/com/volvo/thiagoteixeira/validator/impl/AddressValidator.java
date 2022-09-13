package com.volvo.thiagoteixeira.validator.impl;

import com.volvo.thiagoteixeira.model.AddressModel;
import com.volvo.thiagoteixeira.validator.Validator;
import org.springframework.stereotype.Component;

@Component
public class AddressValidator implements Validator<AddressModel> {

    @Override
    public boolean validate(AddressModel address) {
        return address.getZipCode().matches("[0-9]{5}-[0-9]{3}");
    }

}
