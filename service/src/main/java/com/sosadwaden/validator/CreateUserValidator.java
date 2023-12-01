package com.sosadwaden.validator;

import com.sosadwaden.dto.CreateUserDto;
import com.sosadwaden.entity.Role;
import com.sosadwaden.serviceUtil.LocalDateFormatter;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.stream.Stream;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateUserValidator implements Validator<CreateUserDto> {

    private static final CreateUserValidator INSTANCE = new CreateUserValidator();
    private static final String[] EMAIL_DOMAIN = {"gmail.com", "yahoo.com", "hotmail.com", "aol.com", "hotmail.co.uk", "hotmail.fr", "msn.com", "yahoo.fr"};

    @Override
    public com.sosadwaden.validator.ValidationResult isValid(CreateUserDto createUserDto) {

        com.sosadwaden.validator.ValidationResult validationResult = new ValidationResult();

        if (createUserDto.getName() == null || !createUserDto.getName().matches("^[a-zA-Zа-яА-Я]{2,128}$")) {
            validationResult.add(new com.sosadwaden.validator.RegistrationError("invalid.name", "Некорректное имя"));
        }

        if (createUserDto.getSurname() == null || !createUserDto.getSurname().matches("^[a-zA-Zа-яА-Я]{2,128}$")) {
            validationResult.add(new com.sosadwaden.validator.RegistrationError("invalid.surname", "Некорректная фамилия"));
        }

        if (!LocalDateFormatter.isValid(createUserDto.getBirthday())) {
            validationResult.add(new com.sosadwaden.validator.RegistrationError("invalid.birthday", "Неверный формат даты рождения"));
        }

        if (Role.find(createUserDto.getRole()).isEmpty()) {
            validationResult.add(new com.sosadwaden.validator.RegistrationError("invalid.role","Role is invalid"));
        }

        if (!Stream.of(EMAIL_DOMAIN).anyMatch(createUserDto.getEmail()::endsWith)) {
            validationResult.add(new com.sosadwaden.validator.RegistrationError("invalid.email", "Неверная почта"));
        }

        if (PasswordValidator.isValid(createUserDto.getPassword())) {
            validationResult.add(new RegistrationError("invalid.password", "Invalid Password"));
        }

        return validationResult;
    }

    public static CreateUserValidator getInstance() {
        return INSTANCE;
    }
}
