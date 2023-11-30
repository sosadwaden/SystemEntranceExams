package com.sosadwaden.mapper;

import com.sosadwaden.dto.FacultyDto;
import com.sosadwaden.entity.Faculty;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReadFacultyMapper implements Mapper<Faculty, FacultyDto> {

    private static final ReadFacultyMapper INSTANCE = new ReadFacultyMapper();

    @Override
    public FacultyDto mapFrom(Faculty faculty) {
        return FacultyDto.builder()
                .id(faculty.getId())
                .name(faculty.getName())
                .facultyCapacity(String.valueOf(faculty.getFacultyCapacity()))
                .description(faculty.getDescription())
                .image(faculty.getImage())
                .build();
    }

    public static ReadFacultyMapper getInstance() {
        return INSTANCE;
    }
}
