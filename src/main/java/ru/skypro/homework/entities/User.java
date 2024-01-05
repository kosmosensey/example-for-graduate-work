package ru.skypro.homework.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "email")
    private String email;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "phone")
    private String phone;
    @Column(name = "role")
    private String role;
    @Column(name = "image")
    private String imageAddress;
    @Column(name = "user_name")
    private String username;
    @Column(name = "password")
    private String password;

    @OneToOne
    @JoinColumn(name = "image_id")
    private Image image;
}



