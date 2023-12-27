package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.mapper.AdMapper;
import ru.skypro.homework.entities.Ad;
import ru.skypro.homework.repository.AdRepository;

@Service
@RequiredArgsConstructor
public class AdServiceImpl {
    private final AdRepository adRepository;
    private final AdMapper adMapper;

    public AdDto createAd(AdDto adDto) {
        return adMapper.adToAdDto(adRepository.save(adMapper.adDtoToAd(adDto)));
    }

    public AdDto updateAd(AdDto adDto) {
        return adMapper.adToAdDto(adRepository.save(adMapper.adDtoToAd(adDto)));
    }

    public AdDto findAd(Long id) {
        return adMapper.adToAdDto(adRepository.findById(id).get());
    }

    public void deleteAd(Long id) {
        adRepository.deleteById(id);
    }

}
