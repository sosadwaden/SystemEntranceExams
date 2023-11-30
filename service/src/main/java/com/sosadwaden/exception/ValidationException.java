package com.sosadwaden.exception;

import com.sosadwaden.validator.RegistrationError;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class ValidationException extends RuntimeException {

    private final List<RegistrationError> errors;
}
