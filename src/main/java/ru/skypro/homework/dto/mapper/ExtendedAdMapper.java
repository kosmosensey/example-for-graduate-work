package ru.skypro.homework.dto.mapper;

import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.ExtendedAdDto;
import ru.skypro.homework.entities.Ad;

@Component
public class ExtendedAdMapper {
    public Ad toEntity(ExtendedAdDto dto) {
        Ad entity = new Ad();
        entity.setId(dto.getId());
        entity.setDescription(dto.getDescription());
        entity.setImageAddress(dto.getImageAddress());
        entity.setPrice(dto.getPrice());
        entity.setTitle(dto.getTitle());

        return entity;
    }

    public ExtendedAdDto toDto(Ad entity) {
        ExtendedAdDto dto = new ExtendedAdDto();
        dto.setId(entity.getId());
        dto.setAuthorFirstName(entity.getAuthor().getFirstName());
        dto.setAuthorLastName(entity.getAuthor().getLastName());
        dto.setDescription(entity.getDescription());
        dto.setEmail(entity.getAuthor().getEmail());
        dto.setImageAddress(entity.getImageAddress());
        dto.setPhone(entity.getAuthor().getPhone());
        dto.setPrice(entity.getPrice());
        dto.setTitle(entity.getTitle());

        return dto;
    }
}
