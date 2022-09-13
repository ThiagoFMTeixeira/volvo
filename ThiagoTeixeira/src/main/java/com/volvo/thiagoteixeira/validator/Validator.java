package com.volvo.thiagoteixeira.validator;

public interface Validator<T> {

    boolean validate(T object);

}
