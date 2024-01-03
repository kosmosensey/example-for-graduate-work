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
    @Column(name = "id")
    private Integer id;
    @Column(name = "created_at")
    private Integer createdAt;
    @Column(name = "text")
    private String text;

    @ManyToOne
    @JoinColumn(name = "author")
    private User author;

    @ManyToOne
    @JoinColumn(name = "ad_id")
    private Ad ad;
}
