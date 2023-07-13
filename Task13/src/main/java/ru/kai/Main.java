package ru.kai;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.postgresql.util.PSQLException;
import ru.kai.models.Student;
import ru.kai.repositories.StudentsRepository;
import ru.kai.repositories.StudentsRepositoryJdbcImpl;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        Properties properties = new Properties();

        try {
            properties.load(Main.class.getResourceAsStream("/application.properties"));
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(properties.getProperty("db.url"));
        config.setPassword(properties.getProperty("db.password"));
        config.setUsername(properties.getProperty("db.username"));

        DataSource dataSource = new HikariDataSource(config);

        StudentsRepository studentsRepository = new StudentsRepositoryJdbcImpl(dataSource);
        Optional<Student> stud = studentsRepository.findById((long) 43);
        if (stud.isPresent()) {
            System.out.println(stud.get());
        } else {
            System.out.println("student not found");
        }
        studentsRepository.remove(new Student((long) 13,
                "gimp@gmail.com",
                "ban3121",
                "oleg",
                "egapov",
                72,
                true));

        studentsRepository.update(new Student((long) 43,
                "vanomas@gmail.com",
                "vankal300",
                "van",
                "darkholmich",
                12,
                false));
        studentsRepository.save(new Student((long) 16,
                "gane@gmail.com",
                "capusta121",
                "olanerag",
                "ege",
                72,
                true));
    }
}
