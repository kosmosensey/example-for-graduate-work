package ru.skypro.homework.dto;

import lombok.Data;
import ru.skypro.homework.enums.Role;

import javax.validation.constraints.*;

@Data
public class RegisterDto {
    @Size(min = 4, max = 32)
    private String username;
    @Size(min = 8, max = 16)
    private String password;
    @Size(min = 8, max = 16)
    private String firstName;
    @Size(min = 8, max = 16)
    private String lastName;
    @Pattern(regexp = "'\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}'")
    private String phone;
    private Role role;
}
