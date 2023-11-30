package com.sosadwaden.service;

import com.sosadwaden.dao.FacultyDao;
import com.sosadwaden.dao.UserDao;
import com.sosadwaden.entity.Status;
import com.sosadwaden.entity.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Random;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TeacherService {

    private final UserDao userDao = UserDao.getInstance();
    private final FacultyDao facultyDao = FacultyDao.getInstance();
    private static final TeacherService INSTANCE = new TeacherService();

    public void generateExamResults(Long facultyId) {
        Random random = new Random();
        List<User> users = userDao.findByFaculty(facultyId);
        for (User user: users) {
            user.setScore(random.nextInt(101));
            userDao.update(user);
        }
        setExamStatus(facultyId);
    }

    private void setExamStatus(Long facultyId) {

        List<User> users = userDao.findByFacultyAndSortByScore(facultyId);
        int count = 0;
        int maximumOfPeopleInTheFaculty = facultyDao.findById(facultyId).get().getFacultyCapacity();

        for (User user: users) {
            if (count < maximumOfPeopleInTheFaculty) {
                user.setStatus(Status.ENROLLED);
                userDao.update(user);
            } else {
                user.setStatus(Status.NOT_ENROLLED);
                userDao.update(user);
            }
            count++;
        }
    }

    public static TeacherService getInstance() {
        return INSTANCE;
    }
}
