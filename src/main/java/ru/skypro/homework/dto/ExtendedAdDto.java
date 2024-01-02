package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class ExtendedAdDto {
    private Integer id;
    private String authorFirstName;
    private String authorLastName;
    private String description;
    private String email;
    private String imageAddress;
    private String phone;
    private Integer price;
    private String title;
}
