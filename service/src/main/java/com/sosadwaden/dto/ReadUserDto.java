package com.sosadwaden.dto;


import com.sosadwaden.entity.Role;
import lombok.Builder;
import lombok.Value;
import com.sosadwaden.entity.Faculty;
import com.sosadwaden.entity.Status;

import java.time.LocalDate;

@Value
@Builder
public class ReadUserDto {

    Long id;
    String name;
    String surname;
    LocalDate birthday;
    Status status;
    Role role;
    String email;
    Integer score;
    Faculty faculty;
}
