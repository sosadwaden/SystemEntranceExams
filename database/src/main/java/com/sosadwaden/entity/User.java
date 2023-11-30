package com.sosadwaden.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    private Long id;
    private String name;
    private String surname;
    private LocalDate birthday;
    private Status status;
    private Role role;
    private String email;
    private String password;
    private Integer score;
    private Faculty faculty;
}
