package ru.skypro.homework.mapper.ads;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.ads.AdDto;
import ru.skypro.homework.dto.ads.AdsDTO;
import ru.skypro.homework.entities.Ad;

import java.util.List;

@Mapper
public interface AdsMapper {
    AdsMapper INSTANCE = Mappers.getMapper(AdsMapper.class);

    AdsDTO adsToAdsDto(List<Ad> ads);

    List<AdDto> mapAds(List<Ad> ads);
}
