package com.sosadwaden.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CreateUserDto {

    String name;
    String surname;
    String birthday;
    String status;
    String role;
    String email;
    String password;
}
