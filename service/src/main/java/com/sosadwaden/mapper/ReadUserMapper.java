package com.sosadwaden.mapper;

import com.sosadwaden.dto.ReadUserDto;
import com.sosadwaden.entity.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReadUserMapper implements Mapper<User, ReadUserDto> {

    private static final ReadUserMapper INSTANCE = new ReadUserMapper();

    @Override
    public ReadUserDto mapFrom(User user) {
        return ReadUserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .birthday(user.getBirthday())
                .status(user.getStatus())
                .role(user.getRole())
                .email(user.getEmail())
                .score(user.getScore())
                .faculty(user.getFaculty())
                .build();
    }

    public static ReadUserMapper getInstance() {
        return INSTANCE;
    }
}
