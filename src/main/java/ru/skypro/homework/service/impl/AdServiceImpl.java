package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.dto.mapper.AdMapper;
import ru.skypro.homework.dto.mapper.ExtendedAdMapper;
import ru.skypro.homework.dto.mapper.UserDtoMapper;
import ru.skypro.homework.entities.Ad;
import ru.skypro.homework.entities.User;
import ru.skypro.homework.repository.AdRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdServiceImpl {
    private final AdRepository adRepository;
    private final AdMapper adMapper;
    private final ExtendedAdMapper extendedAdMapper;
    private final UserServiceImpl userService;
    private final UserDtoMapper userDtoMapper;

    public List<AdDto> getAllAds() {
        return adRepository.findAll().stream()
                .map(adMapper::adToAdDto)
                .toList();
    }

    public AdDto createAd(AdDto adDto) {
        return adMapper.adToAdDto(adRepository.save(adMapper.adDtoToAd(adDto)));
    }
    public AdDto createAd(CreateOrUpdateAdDto adDto, String email, String imagePath) {
        // добавить обработку картинки
        User user = userDtoMapper.mapToUser(userService.findByEmail(email));
        Ad ad = new Ad();
        ad.setTitle(adDto.getTitle());
        ad.setPrice(adDto.getPrice());
        ad.setDescription(adDto.getDescription());
        ad.setImage(imagePath);
        ad.setAuthor(user);
        return adMapper.adToAdDto(adRepository.save(ad));
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
