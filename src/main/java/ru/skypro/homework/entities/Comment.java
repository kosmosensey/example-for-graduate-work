package ru.skypro.homework.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pk;
    private Integer author;
    @Column(name = "author_image")
    private String authorImage;
    @Column(name = "author_first_name")
    private String authorFirstName;
    @Column(name = "created_at")
    private Integer createdAt;
    private String text;
}
