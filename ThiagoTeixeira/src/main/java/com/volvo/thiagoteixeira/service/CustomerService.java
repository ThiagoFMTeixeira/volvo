package com.volvo.thiagoteixeira.service;

import com.volvo.thiagoteixeira.model.CustomerModel;

import java.util.List;

public interface CustomerService {

    List<CustomerModel> getCustomersByZipCode(String zipCode);

    List<CustomerModel> getCustomers();

    CustomerModel getCustomer(String document);

    boolean updateCustomer(String document, CustomerModel customer);

    boolean deleteCustomer(String document);

    boolean saveCustomer(CustomerModel customer);

}
