package com.sosadwaden.service;

import com.sosadwaden.dao.FacultyDao;
import com.sosadwaden.dto.CreateFacultyDto;
import com.sosadwaden.dto.FacultyDto;
import com.sosadwaden.dto.UpdateFacultyDto;
import com.sosadwaden.entity.Faculty;
import com.sosadwaden.exception.ValidationException;
import com.sosadwaden.mapper.CreateFacultyMapper;
import com.sosadwaden.mapper.ReadFacultyMapper;
import com.sosadwaden.mapper.UpdateFacultyMapper;
import com.sosadwaden.validator.CreateFacultyValidator;
import com.sosadwaden.validator.UpdateFacultyValidator;
import com.sosadwaden.validator.ValidationResult;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.util.List;

import static java.util.stream.Collectors.toList;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FacultyService {

    private final FacultyDao facultyDao = FacultyDao.getInstance();
    private final CreateFacultyValidator createFacultyValidator = CreateFacultyValidator.getInstance();
    private final UpdateFacultyValidator updateFacultyValidator = UpdateFacultyValidator.getInstance();
    private final ReadFacultyMapper readFacultyMapper = ReadFacultyMapper.getInstance();
    private final CreateFacultyMapper createFacultyMapper = CreateFacultyMapper.getInstance();
    private final UpdateFacultyMapper updateFacultyMapper = UpdateFacultyMapper.getInstance();
    private final ImageService imageService = ImageService.getInstance();
    private static final FacultyService INSTANCE = new FacultyService();

    public static FacultyService getInstance() {
        return INSTANCE;
    }

    public List<FacultyDto> findAll() {
        return facultyDao.findAll().stream()
                .map(readFacultyMapper::mapFrom)
                .collect(toList());
    }


    public FacultyDto findById(Long id) {
        return readFacultyMapper.mapFrom(facultyDao.findById(id).get());
        // TODO разобраться здесь с Optional и как его обрабатывать
    }

    public Long create(CreateFacultyDto createFacultyDto) {
        // validation
        ValidationResult validationResult = createFacultyValidator.isValid(createFacultyDto);
        if (!validationResult.isValid()) {
            throw new ValidationException(validationResult.getErrors());
        }
        // map
        Faculty faculty = createFacultyMapper.mapFrom(createFacultyDto);
        // facultyDao
        try {
            imageService.upload(faculty.getImage(), createFacultyDto.getImage().getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        facultyDao.save(faculty);
        // return
        return faculty.getId();
    }

    public void update(UpdateFacultyDto updateFacultyDto) {
        //validation
        ValidationResult validationResult = updateFacultyValidator.isValid(updateFacultyDto);
        if (!validationResult.isValid()) {
            throw new ValidationException(validationResult.getErrors());
        }
        // map
        Faculty faculty = updateFacultyMapper.mapFrom(updateFacultyDto);
        // dao
        facultyDao.update(faculty);
    }

    public boolean delete(Long facultyId) {
        return facultyDao.delete(facultyId);
    }

}
