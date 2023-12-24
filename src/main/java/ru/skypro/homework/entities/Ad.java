package ru.skypro.homework.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@Table(name = "ads")
public class Ad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer pk;
    @ManyToOne
    @JoinColumn(name = "author")
    private User author;
    @Column(name = "description")
    private String description;
    @Column(name = "image")
    private String image;
    @Column(name = "price")
    private Integer price;
    @Column(name = "title")
    private String title;
}
