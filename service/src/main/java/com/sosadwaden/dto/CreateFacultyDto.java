package com.sosadwaden.dto;

import jakarta.servlet.http.Part;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CreateFacultyDto {

    String name;
    String facultyCapacity;
    String description;
    Part image;
}
