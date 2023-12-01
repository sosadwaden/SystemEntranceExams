package com.sosadwaden.service;


import com.sosadwaden.dao.FacultyDao;
import com.sosadwaden.dao.UserDao;
import com.sosadwaden.dto.CreateUserDto;
import com.sosadwaden.dto.ReadUserDto;
import com.sosadwaden.entity.Faculty;
import com.sosadwaden.entity.Status;
import com.sosadwaden.entity.User;
import com.sosadwaden.exception.LoginException;
import com.sosadwaden.exception.ValidationException;
import com.sosadwaden.login.LoginError;
import com.sosadwaden.mapper.CreateUserMapper;
import com.sosadwaden.mapper.ReadUserMapper;
import com.sosadwaden.validator.CreateUserValidator;
import com.sosadwaden.login.UserLoginChecker;
import com.sosadwaden.validator.ValidationResult;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserService {

    private static final UserService INSTANCE = new UserService();
    private final CreateUserValidator createUserValidator = CreateUserValidator.getInstance();
    private final UserLoginChecker userLoginChecker = UserLoginChecker.getInstance();
    private final UserDao userDao = UserDao.getInstance();
    private final FacultyDao facultyDao = FacultyDao.getInstance();
    private final ReadUserMapper readUserMapper = ReadUserMapper.getInstance();
    private final CreateUserMapper createUserMapper = CreateUserMapper.getInstance();

    public static UserService getInstance() {
        return INSTANCE;
    }

    public List<ReadUserDto> findByFacultyAndSortByScore(Long id) {
        return userDao.findByFacultyAndSortByScore(id).stream()
                .map(readUserMapper::mapFrom).collect(toList());
    }

    public Long create(CreateUserDto createUserDto) {
        if (userLoginChecker.isEmailExist(createUserDto.getEmail())) {
            throw new LoginException(new LoginError("login.error", "Такая почта уже используется"));
        }

        // validation
        ValidationResult validationResult = createUserValidator.isValid(createUserDto);
        if (!validationResult.isValid()) {
            throw new ValidationException(validationResult.getErrors());
        }

        // map
        User user = createUserMapper.mapFrom(createUserDto);

        // userDao.save
        userDao.save(user);

        // return id
        return user.getId();
    }

    public void updateWhenUserSubmittedTheApplication(Long userId, Long facultyId) {
        User user = userDao.findById(userId).get();
        Faculty faculty = facultyDao.findById(facultyId).get();
        user.setStatus(Status.TAKE_EXAMS);
        user.setFaculty(faculty);
        userDao.update(user);
    }

    public Optional<ReadUserDto> login(String email, String password) {
        return userDao.findByEmailAndPassword(email , password)
                .map(readUserMapper::mapFrom);
    }
}
