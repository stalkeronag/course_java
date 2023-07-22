package ru.kai.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Student {
    private Long id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private Integer age;

    private boolean isWorker;
}
