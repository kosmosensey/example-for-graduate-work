package ru.skypro.homework.mapper.ads;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.ads.CreateOrUpdateAdDTO;
import ru.skypro.homework.entities.Ad;


@Mapper
public interface CreateOrUpdateAdMapper {
    CreateOrUpdateAdMapper INSTANCE = Mappers.getMapper(CreateOrUpdateAdMapper.class);

    CreateOrUpdateAdDTO entityAdToCreateOrUpdateAdDTO(Ad entity);

    Ad createOrUpdateAdDTOToEntityAd(CreateOrUpdateAdDTO dto);
}

