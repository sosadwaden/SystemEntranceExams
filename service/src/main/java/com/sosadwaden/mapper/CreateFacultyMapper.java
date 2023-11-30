package com.sosadwaden.mapper;

import com.sosadwaden.dto.CreateFacultyDto;
import com.sosadwaden.entity.Faculty;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateFacultyMapper implements Mapper<CreateFacultyDto, Faculty> {

    private static final CreateFacultyMapper INSTANCE = new CreateFacultyMapper();
    private static final String IMAGE_FOLDER = "faculties/";

    @Override
    public Faculty mapFrom(CreateFacultyDto createFacultyDto) {
        return Faculty.builder()
                .name(createFacultyDto.getName())
                .facultyCapacity(Integer.valueOf(createFacultyDto.getFacultyCapacity()))
                .description(createFacultyDto.getDescription())
                .image(IMAGE_FOLDER + createFacultyDto.getImage().getSubmittedFileName())
                .build();
    }

    public static CreateFacultyMapper getInstance() {
        return INSTANCE;
    }
}
