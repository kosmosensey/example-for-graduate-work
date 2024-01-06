package ru.skypro.homework.dto.mapper;

import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.entities.Ad;

@Component
public class AdMapper {
    public static Ad adDtoToAd(AdDto adDto) {
        Ad ad = new Ad();
        ad.setId(adDto.getId());
        ad.setTitle(adDto.getTitle());
        ad.setImageUrl(adDto.getImageUrl());
        ad.setPrice(adDto.getPrice());
        return ad;
    }

    public static AdDto adToAdDto(Ad entity) {
        AdDto adDto = new AdDto();
        adDto.setId(entity.getId());
        adDto.setAuthor(entity.getUser().getId());
        adDto.setTitle(entity.getTitle());
        adDto.setPrice(entity.getPrice());
        adDto.setImageUrl("/images/" + entity.getId());
        return adDto;
    }
}

