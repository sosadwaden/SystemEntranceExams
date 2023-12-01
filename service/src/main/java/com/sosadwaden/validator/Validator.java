package com.sosadwaden.validator;

public interface Validator<T> {

    ValidationResult isValid(T object);
}
