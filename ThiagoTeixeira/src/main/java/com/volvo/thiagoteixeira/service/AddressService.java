package com.volvo.thiagoteixeira.service;

import com.volvo.thiagoteixeira.model.AddressModel;

import java.util.List;

public interface AddressService {

    List<AddressModel> getAddresses();

    AddressModel getAddress(String zipCode);

    boolean updateAddress(String zipCode, AddressModel Address);

    boolean deleteAddress(String zipCode);

    boolean saveAddress(AddressModel address);

}
