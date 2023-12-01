package com.sosadwaden.dao;


import com.sosadwaden.databaseUtil.ConnectionManager;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import com.sosadwaden.entity.Role;
import com.sosadwaden.entity.Status;
import com.sosadwaden.entity.User;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserDao implements Dao<Long, User> {

    private static final UserDao INSTANCE = new UserDao();
    private final FacultyDao facultyDao = FacultyDao.getInstance();

    public static UserDao getInstance() {
        return INSTANCE;
    }

    private static final String FIND_ALL_SQL = """
            SELECT id,
               name,
               surname,
               birthday,
               status,
               role,
               email,
               password,
               score,
               faculty_id
            FROM users
            """;

    private static final String FIND_BY_ID = FIND_ALL_SQL + """
            WHERE id = ?
            """;

    private static final String FIND_BY_FACULTY = FIND_ALL_SQL + """
            WHERE faculty_id = ?
            """;

    private static final String FIND_BY_FACULTY_AND_SORT_BY_SCORE = FIND_BY_FACULTY + """
            ORDER BY score DESC
            """;

    private static final String FIND_BY_EMAIL = FIND_ALL_SQL + """
            WHERE email = ?
            """;

    private static final String FIND_BY_EMAIL_AND_PASSWORD = FIND_ALL_SQL + """
            WHERE email = ? AND password = ? 
            """;

    private static final String SAVE_SQL = """
            INSERT INTO 
            users (name, surname, birthday, status, role, email, password, score) 
            VALUES (?, ?, ?, ?, ?, ?, ?, ?) 
            """;

    private static final String UPDATE_SQL = """
            UPDATE users
            SET name = ?,
                surname = ?,
                birthday = ?,
                status = ?,
                role = ?,
                email = ?,
                password = ?,
                score = ?,
                faculty_id = ?
            WHERE id = ?
            """;

    private static final String DELETE_SQL = """
            DELETE FROM users
            WHERE id = ?
            """;

    @Override
    public List<User> findAll() {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_ALL_SQL)) {

            List<User> result = new ArrayList<>();
            var resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result.add(buildUser(resultSet));
            }

            return result;

        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public Optional<User> findById(Long id) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_BY_ID)) {

            preparedStatement.setLong(1, id);

            var resultSet = preparedStatement.executeQuery();
            User applicant = null;
            if (resultSet.next()) {
                applicant = buildUser(resultSet);
            }

            return Optional.ofNullable(applicant);
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    public List<User> findByFaculty(Long facultyId) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_BY_FACULTY)) {

            preparedStatement.setLong(1, facultyId);

            var resultSet = preparedStatement.executeQuery();
            List<User> users = new ArrayList<>();
            while (resultSet.next()) {
                users.add(buildUser(resultSet));
            }

            return users;
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }


    public List<User> findByFacultyAndSortByScore(Long faculty_id) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_BY_FACULTY_AND_SORT_BY_SCORE)) {

            List<User> result = new ArrayList<>();
            preparedStatement.setLong(1, faculty_id);
            var resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result.add(buildUser(resultSet));
            }

            return result;

        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    public Optional<User> findByEmail(String email) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_BY_EMAIL)) {

            preparedStatement.setString(1, email);

            var resultSet = preparedStatement.executeQuery();
            User user = null;
            if (resultSet.next()) {
                user = buildUser(resultSet);
            }

            return Optional.ofNullable(user);

        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    public Optional<User> findByEmailAndPassword(String email, String password) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_BY_EMAIL_AND_PASSWORD)) {

            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            var resultSet = preparedStatement.executeQuery();
            User user = null;
            if (resultSet.next()) {
                user = buildUser(resultSet);
            }

            return Optional.ofNullable(user);

        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public User save(User user) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(SAVE_SQL, RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setDate(3, Date.valueOf(user.getBirthday()));
            preparedStatement.setString(4, Status.DEFAULT.name());
            preparedStatement.setString(5, user.getRole().name());
            preparedStatement.setString(6, user.getEmail());
            preparedStatement.setString(7, user.getPassword());

            if (user.getScore() != null) {
                preparedStatement.setInt(8, user.getScore());
            } else {
                preparedStatement.setNull(8, Types.INTEGER);
            }

            preparedStatement.executeUpdate();
            var generatedKeys = preparedStatement.getGeneratedKeys();

            generatedKeys.next();
            user.setId(generatedKeys.getLong("id"));
            return user;

        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }

    }

    @Override
    public void update(User user) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(UPDATE_SQL)) {

            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setDate(3, Date.valueOf(user.getBirthday()));
            preparedStatement.setString(4, user.getStatus().name());
            preparedStatement.setString(5, user.getRole().name());
            preparedStatement.setString(6, user.getEmail());
            preparedStatement.setString(7, user.getPassword());
            preparedStatement.setInt(8, user.getScore());
            preparedStatement.setLong(9, user.getFaculty().getId());

            preparedStatement.setLong(10, user.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public boolean delete(Long id) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(DELETE_SQL)) {

            preparedStatement.setLong(1, id);
            return preparedStatement.executeUpdate() != 0;

        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    private User buildUser(ResultSet resultSet) throws SQLException {
        return User.builder()
                .id(resultSet.getLong("id"))
                .name(resultSet.getString("name"))
                .surname(resultSet.getString("surname"))
                .birthday(resultSet.getDate("birthday").toLocalDate())
                .status(Status.find(resultSet.getString("status")).orElse(null))
                .role(Role.find(resultSet.getString("role")).orElse(null))
                .email(resultSet.getString("email"))
                .password(resultSet.getString("password"))
                .score(resultSet.getInt("score"))
                .faculty(facultyDao.findById(resultSet.getLong("faculty_id")).orElse(null))
                .build();
    }

}
