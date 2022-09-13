package com.volvo.thiagoteixeira.controller;

import com.volvo.thiagoteixeira.model.CustomerModel;
import com.volvo.thiagoteixeira.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.volvo.thiagoteixeira.controller.constant.ControllersConstant.*;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<CustomerModel>> getCustomers() {
        return ResponseEntity.ok(customerService.getCustomers());
    }

    @GetMapping(value = "/{document}")
    public ResponseEntity getCustomer(final @PathVariable String document) {
        final CustomerModel customer = customerService.getCustomer(document);

        if (customer.getDocument() == null) {
            return ResponseEntity.badRequest().body(ERROR_MESSAGE_CUSTOMER_NOT_FOUND);
        } else {
            return ResponseEntity.ok(customer);
        }
    }

    @PostMapping
    public ResponseEntity<String> saveCustomer(final @RequestBody CustomerModel customer) {
        if (customerService.saveCustomer(customer)) {
            return ResponseEntity.status(HttpStatus.CREATED).body(String.format(HTTP_OK_CUSTOMER_MESSAGE, "created."));
        } else {
            return ResponseEntity.internalServerError().body(HTTP_INTERNAL_SERVER_ERROR_MESSAGE);
        }
    }

    @DeleteMapping(value = "/{document}")
    public ResponseEntity<String> deleteCustomer(final @PathVariable String document) {
        if (customerService.deleteCustomer(document)) {
            return ResponseEntity.ok().body(String.format(HTTP_OK_CUSTOMER_MESSAGE, "deleted."));
        } else {
            return ResponseEntity.internalServerError().body(HTTP_INTERNAL_SERVER_ERROR_MESSAGE);
        }
    }

    @PutMapping(value = "/{document}")
    public ResponseEntity<String> updateCustomer(final @PathVariable String document, final @RequestBody CustomerModel customer) {
        if (customerService.updateCustomer(document, customer)) {
            return ResponseEntity.ok().body(String.format(HTTP_OK_CUSTOMER_MESSAGE, "updated."));
        } else {
            return ResponseEntity.internalServerError().body(HTTP_INTERNAL_SERVER_ERROR_MESSAGE);
        }
    }

    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }
}
