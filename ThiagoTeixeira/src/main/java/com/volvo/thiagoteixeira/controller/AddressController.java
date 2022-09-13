package com.volvo.thiagoteixeira.controller;

import com.volvo.thiagoteixeira.model.AddressModel;
import com.volvo.thiagoteixeira.service.AddressService;
import com.volvo.thiagoteixeira.validator.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.volvo.thiagoteixeira.controller.constant.ControllersConstant.*;

@RestController
@RequestMapping("/addresses")
public class AddressController {
    private AddressService addressService;

    private Validator<AddressModel> addressValidator;

    @GetMapping
    public ResponseEntity<List<AddressModel>> getAddresses() {
        return ResponseEntity.ok(addressService.getAddresses());
    }

    @GetMapping(value = "/{zipCode}")
    public ResponseEntity getAddress(final @PathVariable String zipCode) {
        final AddressModel address = addressService.getAddress(zipCode);

        if (address.getZipCode() == null) {
            return ResponseEntity.badRequest().body(ERROR_MESSAGE_ADDRESS_NOT_FOUND);
        } else {
            return ResponseEntity.ok(address);
        }
    }

    @PostMapping
    public ResponseEntity<String> saveAddress(final @RequestBody AddressModel address) {
        if (addressValidator.validate(address)) {
            if (addressService.saveAddress(address)) {
                return ResponseEntity.status(HttpStatus.CREATED).body(String.format(HTTP_OK_ADDRESS_MESSAGE, "created."));
            } else {
                return ResponseEntity.internalServerError().body(HTTP_INTERNAL_SERVER_ERROR_MESSAGE);
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(HTTP_BAD_REQUEST_MESSAGE);
        }
    }

    @DeleteMapping(value = "/{zipCode}")
    public ResponseEntity<String> deleteAddress(final @PathVariable String zipCode) {
        if (addressService.deleteAddress(zipCode)) {
            return ResponseEntity.ok().body(String.format(HTTP_OK_ADDRESS_MESSAGE, "deleted."));
        } else {
            return ResponseEntity.internalServerError().body(HTTP_INTERNAL_SERVER_ERROR_MESSAGE);
        }
    }

    @PutMapping(value = "/{zipCode}")
    public ResponseEntity<String> updateAddress(final @PathVariable String zipCode, final @RequestBody AddressModel address) {
        if (addressValidator.validate(address)) {
            if (addressService.updateAddress(zipCode, address)) {
                return ResponseEntity.ok().body(String.format(HTTP_OK_ADDRESS_MESSAGE, "updated."));
            } else {
                return ResponseEntity.internalServerError().body(HTTP_INTERNAL_SERVER_ERROR_MESSAGE);
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(HTTP_BAD_REQUEST_MESSAGE);
        }
    }

    @Autowired
    public void setAddressService(AddressService addressService) {
        this.addressService = addressService;
    }

    @Autowired
    public void setAddressValidator(Validator<AddressModel> addressValidator) {
        this.addressValidator = addressValidator;
    }
}
