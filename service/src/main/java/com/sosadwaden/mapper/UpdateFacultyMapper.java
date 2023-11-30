package com.sosadwaden.mapper;

import com.sosadwaden.dto.UpdateFacultyDto;
import com.sosadwaden.entity.Faculty;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UpdateFacultyMapper implements Mapper<UpdateFacultyDto, Faculty> {

    private static final UpdateFacultyMapper INSTANCE = new UpdateFacultyMapper();
    private static final String IMAGE_FOLDER = "faculties/";

    @Override
    public Faculty mapFrom(UpdateFacultyDto updateFacultyDto) {
        return Faculty.builder()
                .id(Long.valueOf(updateFacultyDto.getId()))
                .name(updateFacultyDto.getName())
                .facultyCapacity(Integer.valueOf(updateFacultyDto.getFacultyCapacity()))
                .description(updateFacultyDto.getDescription())
                .image(IMAGE_FOLDER + updateFacultyDto.getImage().getSubmittedFileName())
                .build();
    }

    public static UpdateFacultyMapper getInstance() {
        return INSTANCE;
    }

}
