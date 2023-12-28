package ru.skypro.homework.dto.mapper;

import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.CreateOrUpdateAdDto;
import ru.skypro.homework.entities.Ad;
@Component
public class CreateOrUpdateAdMapper {

    public static CreateOrUpdateAdDto mapToCreateOrUpdateAdDto(Ad entity) {
        CreateOrUpdateAdDto dto = new CreateOrUpdateAdDto();
        dto.setTitle(entity.getTitle());
        dto.setPrice(entity.getPrice());
        dto.setDescription(entity.getDescription());
        return dto;
    }

    public static Ad mapToAd(CreateOrUpdateAdDto dto) {
        Ad entity = new Ad();
        entity.setTitle(dto.getTitle());
        entity.setPrice(dto.getPrice());
        entity.setDescription(dto.getDescription());
        return entity;
    }
}
