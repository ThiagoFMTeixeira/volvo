package com.volvo.thiagoteixeira.controller.constant;

public interface ControllersConstant {

    String HTTP_BAD_REQUEST_MESSAGE = "Your request couldn't be processed, please check yours request's body and try again.";
    String HTTP_INTERNAL_SERVER_ERROR_MESSAGE = "An unexpected error has occurred while trying to process your request.";
    String HTTP_OK_ADDRESS_MESSAGE = "Your request has been successfully processed. An address was %s ";
    String HTTP_OK_CUSTOMER_MESSAGE = "Your request has been successfully processed. A customer was %s ";
    String ERROR_MESSAGE_ADDRESS_NOT_FOUND = "Couldn't find an address for the given id";
    String ERROR_MESSAGE_CUSTOMER_NOT_FOUND = "Couldn't find an customer for the given id";

}
