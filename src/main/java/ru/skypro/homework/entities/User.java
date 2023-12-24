package ru.skypro.homework.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.skypro.homework.enums.Role;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String email;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private String phone;
    private Role role;
    private String image;
    private String username;
    private String password;

}



