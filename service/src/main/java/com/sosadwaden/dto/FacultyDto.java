package com.sosadwaden.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class FacultyDto {

    Long id;
    String name;
    String facultyCapacity;
    String description;
    String image;
}
