package ru.lexink.entity;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Person {
    private int id;
    @NotEmpty(message = "Name field can't be empty")
    @Size(min = 2, max = 10, message = "Name is too short <2 or too long >10")
    private String name;
    @Min(value = 0, message = "Only positive value is possible!")
    private int age;
    @NotEmpty(message = "E-mail field can't be empty")
    @Email(message = "E-mail must be valid format: xxxxx@xxx.xx")
    private String email;
}
