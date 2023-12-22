package ru.skypro.homework.entities;

import liquibase.pro.packaged.I;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@NoArgsConstructor
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pk;
    private Long author;
    @Column(name = "author_image")
    private String authorImage;
    @Column(name = "author_first_name")
    private String authorFirstName;
    @Column(name = "created_at")
    private Timestamp createdAt;
    private String text;
}
