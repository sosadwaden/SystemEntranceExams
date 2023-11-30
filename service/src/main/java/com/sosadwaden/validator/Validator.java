package com.sosadwaden.validator;

import com.sosadwaden.validator.ValidationResult;

public interface Validator<T> {

    ValidationResult isValid(T object);
}
