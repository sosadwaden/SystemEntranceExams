package com.sosadwaden.mapper;

import com.sosadwaden.dto.CreateUserDto;
import com.sosadwaden.entity.Role;
import com.sosadwaden.entity.Status;
import com.sosadwaden.entity.User;
import com.sosadwaden.serviceUtil.LocalDateFormatter;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateUserMapper implements Mapper<CreateUserDto, User> {

    private static final CreateUserMapper INSTANCE = new CreateUserMapper();

    @Override
    public User mapFrom(CreateUserDto createUserDto) {
        return User.builder()
                .name(createUserDto.getName())
                .surname(createUserDto.getSurname())
                .birthday(LocalDateFormatter.format(createUserDto.getBirthday()))
                .status(Status.valueOf(createUserDto.getStatus()))
                .role(Role.valueOf(createUserDto.getRole()))
                .email(createUserDto.getEmail())
                .password(createUserDto.getPassword())
                .build();
    }

    public static CreateUserMapper getInstance() {
        return INSTANCE;
    }
}
