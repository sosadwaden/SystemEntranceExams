package com.sosadwaden.validator;

import com.sosadwaden.dto.CreateFacultyDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateFacultyValidator implements Validator<CreateFacultyDto> {

    private static final CreateFacultyValidator INSTANCE = new CreateFacultyValidator();
    private static final String[] IMAGE_FORMATS = {"jpg", "jpeg", "png"};

    @Override
    public ValidationResult isValid(CreateFacultyDto createFacultyDto) {

        ValidationResult validationResult = new ValidationResult();

        if (createFacultyDto.getName() == null || !createFacultyDto.getName().matches("^[a-zA-Zа-яА-Я]{2,128}$")) {
            validationResult.add(new RegistrationError("invalid.name", "Имя факультета содержит некорректные символы для имени"));
        }

        if (createFacultyDto.getFacultyCapacity() == null || !createFacultyDto.getFacultyCapacity().chars().allMatch(Character::isDigit)) {
            validationResult.add(new RegistrationError("invalid.facultyCapacity", "Число мест на факультете должно быть числом"));
        }

        //TODO переделать
//        if (!Stream.of(IMAGE_FORMATS).anyMatch(createFacultyDto.getImage().getName()::endsWith)) {
//            validationResult.add(new RegistrationError("invalid.image", "Invalid data format for the image"));
//        }

        return validationResult;
    }

    public static CreateFacultyValidator getInstance() {
        return INSTANCE;
    }
}
