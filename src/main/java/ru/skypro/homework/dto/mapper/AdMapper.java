package ru.skypro.homework.dto.mapper;

import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.entities.Ad;

@Component
public class AdMapper {
    public Ad adDtoToAd(AdDto adDto) {
        Ad ad = new Ad();
        ad.setId(adDto.getPk());
        ad.setTitle(adDto.getTitle());
        ad.setImage(adDto.getImage());
        ad.setPrice(adDto.getPrice());
        return ad;
    }

    public  AdDto adToAdDto(Ad ad) {
        AdDto adDto = new AdDto();
        adDto.setPk(ad.getId());
        adDto.setAuthor(ad.getAuthor().getId());
        adDto.setTitle(ad.getTitle());
        adDto.setImage(ad.getImage());
        adDto.setPrice(ad.getPrice());
        return adDto;
    }
}

