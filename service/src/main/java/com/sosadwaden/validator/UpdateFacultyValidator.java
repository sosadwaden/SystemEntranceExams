package com.sosadwaden.validator;

import com.sosadwaden.dto.UpdateFacultyDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UpdateFacultyValidator implements Validator<UpdateFacultyDto> {

    private static final UpdateFacultyValidator INSTANCE = new UpdateFacultyValidator();

    @Override
    public ValidationResult isValid(UpdateFacultyDto updateFacultyDto) {
        ValidationResult validationResult = new ValidationResult();

        if (updateFacultyDto.getName() == null || !updateFacultyDto.getName().matches("^[a-zA-Zа-яА-Я]{2,128}$")) {
            validationResult.add(new RegistrationError("invalid.name", "Имя факультета содержит некорректные символы для имени"));
        }

        if (updateFacultyDto.getFacultyCapacity() == null || !updateFacultyDto.getFacultyCapacity().chars().allMatch(Character::isDigit)) {
            validationResult.add(new RegistrationError("invalid.facultyCapacity", "Число мест на факультете должно быть числом"));
        }

        return validationResult;
    }

    public static UpdateFacultyValidator getInstance() {
        return INSTANCE;
    }
}
