package com.sosadwaden.dao;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import com.sosadwaden.databaseUtil.ConnectionManager;
import com.sosadwaden.entity.Faculty;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FacultyDao implements Dao<Long, Faculty> {

    private static final FacultyDao INSTANCE = new FacultyDao();

    public static FacultyDao getInstance() {
        return INSTANCE;
    }

    private static final String FIND_ALL_SQL = """
            SELECT id,
               name,
               faculty_capacity,
               description,
               image
            FROM faculty
            """;

    private static final String FIND_BY_ID = FIND_ALL_SQL + """
            WHERE id = ?
            """;

    private static final String SAVE_SQL = """
            INSERT INTO faculty (name, faculty_capacity, description, image) 
            VALUES (?, ?, ?, ?) 
            """;

    private static final String UPDATE_SQL = """
            UPDATE faculty
            SET name = ?,
                faculty_capacity = ?,
                description = ?,
                image = ?
            WHERE id = ?
            """;

    private static final String DELETE_SQL = """
            DELETE FROM faculty
            WHERE id = ?
            """;

    @Override
    public List<Faculty> findAll() {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_ALL_SQL)) {

            var resultSet = preparedStatement.executeQuery();
            List<Faculty> result = new ArrayList<>();
            while (resultSet.next()) {
                result.add(buildFaculty(resultSet));
            }

            return result;

        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public Optional<Faculty> findById(Long id) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_BY_ID)) {

            preparedStatement.setLong(1, id);
            var resultSet = preparedStatement.executeQuery();
            Faculty faculty = null;
            if (resultSet.next()) {
                faculty = buildFaculty(resultSet);
            }

            return Optional.ofNullable(faculty);

        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public Faculty save(Faculty faculty) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(SAVE_SQL, RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, faculty.getName());
            preparedStatement.setInt(2, faculty.getFacultyCapacity());
            preparedStatement.setString(3, faculty.getDescription());
            preparedStatement.setString(4, faculty.getImage());

            preparedStatement.executeUpdate();
            var generatedKeys = preparedStatement.getGeneratedKeys();

            generatedKeys.next();
            faculty.setId(generatedKeys.getLong("id"));
            return faculty;

        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public void update(Faculty faculty) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(UPDATE_SQL)) {

            preparedStatement.setString(1, faculty.getName());
            preparedStatement.setInt(2, faculty.getFacultyCapacity());
            preparedStatement.setString(3, faculty.getDescription());
            preparedStatement.setString(4, faculty.getImage());

            preparedStatement.setLong(5, faculty.getId());

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
            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    private Faculty buildFaculty(ResultSet resultSet) throws SQLException {
        return Faculty.builder()
                .id(resultSet.getLong("id"))
                .name(resultSet.getString("name"))
                .facultyCapacity(resultSet.getInt("faculty_capacity"))
                .description(resultSet.getString("description"))
                .image(resultSet.getString("image"))
                .build();
    }
}
