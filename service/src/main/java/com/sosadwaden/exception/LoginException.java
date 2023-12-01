package com.sosadwaden.exception;

import com.sosadwaden.login.LoginError;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class LoginException extends RuntimeException {

    private final LoginError error;

}
