package ru.skypro.homework.dto.mapper;

import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.entities.Ad;

@Component
public class AdMapper {
    public Ad adDtoToAd(AdDto adDto) {
        Ad ad = new Ad();
        ad.setId(adDto.getId());
        ad.setTitle(adDto.getTitle());
        ad.setImageAddress(adDto.getImageAddress());
        ad.setPrice(adDto.getPrice());
        return ad;
    }

    public  AdDto adToAdDto(Ad ad) {
        AdDto adDto = new AdDto();
        adDto.setId(ad.getId());
        adDto.setAuthor(ad.getAuthor().getId());
        adDto.setTitle(ad.getTitle());
        adDto.setImageAddress(ad.getImageAddress());
        adDto.setPrice(ad.getPrice());
        return adDto;
    }
}

