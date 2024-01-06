package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class AdDto {
    private Integer id;
    private String title;
    private Integer author;
    private Integer price;
    private String imageUrl;

}


