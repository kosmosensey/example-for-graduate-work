package ru.skypro.homework.entities;

import lombok.Data;
import lombok.Getter;
import ru.skypro.homework.enums.Role;

import javax.persistence.*;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private String phone;
    private String role;
    private String image;


}



