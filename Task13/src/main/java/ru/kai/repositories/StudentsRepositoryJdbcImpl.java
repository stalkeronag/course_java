package ru.kai.repositories;

import ru.kai.models.Student;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudentsRepositoryJdbcImpl implements StudentsRepository {

    //language=SQL
    private static final String SQL_SELECT_ALL = "select * from student;";

    private final DataSource dataSource;

    public StudentsRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void save(Student student) {
        String sqlQuery = "insert into student(email, password, first_name, last_name, age, is_worker) " +
                "values(?, ?, ?, ?, ?, ?)";
        String email = student.getEmail();
        String password = student.getPassword();
        String firstName = student.getFirstName();
        String lastName = student.getLastName();
        Integer age = student.getAge();
        boolean isWorking = student.isWorker();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlQuery);
        ) {
            statement.setString(1, email);
            statement.setString(2, password);
            statement.setString(3, firstName);
            statement.setString(4, lastName);
            statement.setInt(5, age);
            statement.setBoolean(6, isWorking);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void update(Student student) {
        String sqlQuery = "update student set email = (?), " +
                "password = (?) ,first_name = (?), last_name = (?)," +
                "age = (?), is_worker = (?)" +
                "where id = (?)";
        long id = student.getId();
        String email = student.getEmail();
        String password = student.getPassword();
        String firstName = student.getFirstName();
        String lastName = student.getLastName();
        Integer age = student.getAge();
        boolean isWorking = student.isWorker();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlQuery);
        ) {
            statement.setString(1, email);
            statement.setString(2, password);
            statement.setString(3, firstName);
            statement.setString(4, lastName);
            statement.setInt(5, age);
            statement.setBoolean(6, isWorking);
            statement.setLong(7, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void remove(Student student) {
        long id = student.getId();
        String sqlQuery = "delete from student where id =" + id;
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
        ) {
            statement.executeUpdate(sqlQuery);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public Optional<Student> findById(Long id) {
        //language SQL
        String queryString = "select * from student where id = " + id;
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(queryString);) {
            if (resultSet.next() == true) {
                Student student = Student.builder()
                        .id(resultSet.getLong("id"))
                        .email(resultSet.getString("email"))
                        .password(resultSet.getString("password"))
                        .firstName(resultSet.getString("first_name"))
                        .lastName(resultSet.getString("last_name"))
                        .age(resultSet.getInt("age"))
                        .isWorker(resultSet.getBoolean("is_worker"))
                        .build();
                return Optional.of(student);
            }


        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return Optional.empty();
    }
}
