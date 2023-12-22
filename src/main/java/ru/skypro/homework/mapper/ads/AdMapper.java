package ru.skypro.homework.mapper.ads;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.ads.AdDto;
import ru.skypro.homework.entities.Ad;

@Mapper
public interface AdMapper {
    AdMapper INSTANCE = Mappers.getMapper(AdMapper.class);

    AdDto adToAdDto(Ad ad);

    Ad adDtoToAd(AdDto adDto);
}

