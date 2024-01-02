package ru.skypro.homework.entities;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Data
@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "ads")
public class Ad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "description")
    private String description;
    @Column(name = "image")
    private String imageAddress;
    @Column(name = "price")
    private Integer price;
    @Column(name = "title")
    private String title;

    @ManyToOne
    @JoinColumn(name = "author")
    private User author;

    @OneToOne
    @JoinColumn(name = "image_id")
    private Image image;
}
