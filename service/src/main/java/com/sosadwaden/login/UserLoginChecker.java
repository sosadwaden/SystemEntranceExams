package com.sosadwaden.login;

import com.sosadwaden.dao.UserDao;
import com.sosadwaden.entity.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserLoginChecker {

    private final UserDao userDao = UserDao.getInstance();
    private static final UserLoginChecker INSTANCE = new UserLoginChecker();

    public boolean isEmailExist(String email) {
        Optional<User> maybeUser = userDao.findByEmail(email);
        return maybeUser.isPresent();
    }

    public static UserLoginChecker getInstance() {
        return INSTANCE;
    }
}
