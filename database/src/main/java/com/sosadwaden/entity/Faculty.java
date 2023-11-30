package com.sosadwaden.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Faculty {

    private Long id;
    private String name;
    private Integer facultyCapacity;
    private String description;
    private String image;
}
