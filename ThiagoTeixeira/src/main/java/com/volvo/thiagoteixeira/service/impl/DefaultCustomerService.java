package com.volvo.thiagoteixeira.service.impl;

import com.volvo.thiagoteixeira.dao.AddressDao;
import com.volvo.thiagoteixeira.dao.CustomerDao;
import com.volvo.thiagoteixeira.model.AddressModel;
import com.volvo.thiagoteixeira.model.CustomerModel;
import com.volvo.thiagoteixeira.service.CustomerService;
import com.volvo.thiagoteixeira.validator.impl.AddressValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import static com.volvo.thiagoteixeira.service.constant.ServicesConstant.ERROR_MESSAGE_UNEXPECTED;

@Slf4j
@Service
public class DefaultCustomerService implements CustomerService {

    private AddressDao addressDao;
    private CustomerDao customerDao;

    private AddressValidator addressValidator;

    @Override
    public List<CustomerModel> getCustomers() {
        try {
            return customerDao.findAll();
        } catch (final Exception ex) {
            log.error(String.format(ERROR_MESSAGE_UNEXPECTED, "list customers"), ex);
            return new LinkedList<>();
        }
    }

    @Override
    public CustomerModel getCustomer(final String document) {
        try {
            return customerDao.findById(document).orElse(new CustomerModel());
        } catch (final Exception ex) {
            log.error(String.format(ERROR_MESSAGE_UNEXPECTED, "get a customer"), ex);
            return new CustomerModel();
        }
    }

    @Override
    public boolean updateCustomer(final String document, final CustomerModel customer) {
        if (customerDao.existsById(document)) {
            try {
                prepareToSave(customer, true);
                customerDao.save(customer);
                return true;
            } catch (final Exception ex) {
                log.error(String.format(ERROR_MESSAGE_UNEXPECTED, "update a customer"), ex);
            }
        }

        return false;
    }

    @Override
    public boolean deleteCustomer(final String document) {
        try {
            customerDao.delete(getCustomer(document));
            return true;
        } catch (final Exception ex) {
            log.error(String.format(ERROR_MESSAGE_UNEXPECTED, "delete a customer"), ex);
            return false;
        }
    }

    @Override
    public boolean saveCustomer(CustomerModel customer) {
        try {
            prepareToSave(customer, false);
            customerDao.save(customer);
            return true;
        } catch (Exception ex) {
            log.error(String.format(ERROR_MESSAGE_UNEXPECTED, "save a customer"), ex);
            return false;
        }
    }

    private void prepareToSave(final CustomerModel customer, final boolean isUpdate) {
        if (isUpdate) {
            customer.setRegistrationTime(customerDao.findById(customer.getDocument()).map(CustomerModel::getRegistrationTime).orElse(LocalDateTime.now()));
        } else {
            customer.setRegistrationTime(LocalDateTime.now());
        }
        customer.setLastUpdateTime(LocalDateTime.now());
        for (final Iterator<AddressModel> itt = customer.getAddresses().iterator(); itt.hasNext(); ) {
            final AddressModel address = itt.next();
            if (addressValidator.validate(address)) {
                addressDao.save(address);
            } else {
                itt.remove();
            }
        }
    }

    @Autowired
    public void setCustomerDao(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    @Autowired
    public void setAddressDao(AddressDao addressDao) {
        this.addressDao = addressDao;
    }

    @Autowired
    public void setAddressValidator(AddressValidator addressValidator) {
        this.addressValidator = addressValidator;
    }
}
