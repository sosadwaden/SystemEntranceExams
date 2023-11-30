package com.sosadwaden.validator;

import com.sosadwaden.dto.CreateFacultyDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateFacultyValidator implements Validator<CreateFacultyDto> {

    private static final CreateFacultyValidator INSTANCE = new CreateFacultyValidator();
    private static final String[] IMAGE_FORMATS = {"jpg", "jpeg", "png"};

    @Override
    public com.sosadwaden.validator.ValidationResult isValid(CreateFacultyDto createFacultyDto) {

        com.sosadwaden.validator.ValidationResult validationResult = new ValidationResult();

        if (createFacultyDto.getName() == null || !createFacultyDto.getName().matches("^[a-zA-Z]{2,128}$")) {
            validationResult.add(new com.sosadwaden.validator.RegistrationError("invalid.name", "The name contains invalid characters"));
        }

        if (createFacultyDto.getFacultyCapacity() == null || !createFacultyDto.getFacultyCapacity().chars().allMatch(Character::isDigit)) {
            validationResult.add(new RegistrationError("invalid.facultyCapacity", "The number of places in the faculty should be a number"));
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
