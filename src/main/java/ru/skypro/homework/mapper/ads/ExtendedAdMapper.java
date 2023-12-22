package ru.skypro.homework.mapper.ads;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.ads.ExtendedAdDto;
import ru.skypro.homework.entities.Ad;

@Mapper
public interface ExtendedAdMapper {

    ExtendedAdMapper INSTANCE = Mappers.getMapper(ExtendedAdMapper.class);

    ExtendedAdDto extendedAdDtoToEntityAd(ExtendedAdDto dto);

    Ad entityAdToExtendedAdDto(Ad ad);
}
