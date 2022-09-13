package com.volvo.thiagoteixeira.service.impl;

import com.volvo.thiagoteixeira.dao.AddressDao;
import com.volvo.thiagoteixeira.model.AddressModel;
import com.volvo.thiagoteixeira.service.AddressService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

import static com.volvo.thiagoteixeira.service.constant.ServicesConstant.ERROR_MESSAGE_UNEXPECTED;

@Slf4j
@Service
public class DefaultAddressService implements AddressService {

    private AddressDao addressDao;

    @Override
    public List<AddressModel> getAddresses() {
        try {
            return addressDao.findAll();
        } catch (final Exception ex) {
            log.error(String.format(ERROR_MESSAGE_UNEXPECTED, "list addresses"), ex);
            return new LinkedList<>();
        }
    }

    @Override
    public AddressModel getAddress(final String zipCode) {
        try {
            return addressDao.findById(zipCode).orElse(new AddressModel());
        } catch (final Exception ex) {
            log.error(String.format(ERROR_MESSAGE_UNEXPECTED, "get an address"), ex);
            return new AddressModel();
        }
    }

    @Override
    public boolean updateAddress(final String zipCode, final AddressModel address) {
        if (addressDao.existsById(zipCode)) {
            try {
                addressDao.save(address);
                return true;
            } catch (final Exception ex) {
                log.error(String.format(ERROR_MESSAGE_UNEXPECTED, "update an address"), ex);
            }
        }

        return false;
    }

    @Override
    public boolean deleteAddress(final String zipCode) {
        try {
            addressDao.delete(getAddress(zipCode));
            return true;
        } catch (final Exception ex) {
            log.error(String.format(ERROR_MESSAGE_UNEXPECTED, "delete an address"), ex);
            return false;
        }
    }

    @Override
    public boolean saveAddress(final AddressModel address) {
        try {
            addressDao.save(address);
            return true;
        } catch (final Exception ex) {
            log.error(String.format(ERROR_MESSAGE_UNEXPECTED, "save an address"), ex);
            return false;
        }
    }

    @Autowired
    public void setAddressDao(AddressDao addressDao) {
        this.addressDao = addressDao;
    }
}
