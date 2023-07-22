package ru.kai.repositories;

import ru.kai.models.Student;

import java.util.List;
import java.util.Optional;

public interface StudentsRepository {

    void save(Student student);

    void update(Student student);

    void remove(Student student);

    Optional<Student> findById(Long id);
}
