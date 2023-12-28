package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.ExtendedAdDto;
import ru.skypro.homework.dto.mapper.AdMapper;
import ru.skypro.homework.dto.mapper.CreateOrUpdateAdMapper;
import ru.skypro.homework.dto.mapper.ExtendedAdMapper;
import ru.skypro.homework.entities.Ad;
import ru.skypro.homework.repository.AdRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdServiceImpl {
    private final AdRepository adRepository;
    private final AdMapper adMapper;
    private final ExtendedAdMapper extendedAdMapper;

    public List<AdDto> getAllAds() {
        return adRepository.findAll().stream()
                .map(adMapper::adToAdDto)
                .toList();
    }

    public AdDto createAd(AdDto adDto) {
        return adMapper.adToAdDto(adRepository.save(adMapper.adDtoToAd(adDto)));
    }
    public AdDto createAd(CreateOrUpdateAdDto adDto) {
        return adMapper.adToAdDto(adRepository.save(CreateOrUpdateAdMapper.mapToAd(adDto)));
    }

    public AdDto updateAd(AdDto adDto) {
        return adMapper.adToAdDto(adRepository.save(adMapper.adDtoToAd(adDto)));
    }
    public ExtendedAdDto updateExtendedAd(ExtendedAdDto adDto) {
        return extendedAdMapper.toDto(adRepository.save(extendedAdMapper.toEntity(adDto)));
    }


    public AdDto findAd(Long id) {
        return adMapper.adToAdDto(adRepository.findById(id).get());
    }
    public ExtendedAdDto findExtendedAd(Long id) {
        return extendedAdMapper.toDto(adRepository.findById(id).get());
    }

    public void delete(Long id) {
        adRepository.deleteById(id);
    }

}
