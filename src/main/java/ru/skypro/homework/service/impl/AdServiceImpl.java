package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.dto.mapper.AdMapper;
import ru.skypro.homework.dto.mapper.ExtendedAdMapper;
import ru.skypro.homework.dto.mapper.UserDtoMapper;
import ru.skypro.homework.entities.Ad;
import ru.skypro.homework.entities.Image;
import ru.skypro.homework.entities.User;
import ru.skypro.homework.exception.NotFoundException;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.service.ImageService;
import ru.skypro.homework.service.UserService;


import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdServiceImpl {
    private final AdRepository adRepository;
    private final AdMapper adMapper;
    private final ExtendedAdMapper extendedAdMapper;
    private final UserDtoMapper userDtoMapper;
    private final ImageService imageService;
    private final UserService userService;

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
        ad.setImageAddress(imagePath);
        ad.setAuthor(user);
        return adMapper.adToAdDto(adRepository.save(ad));
    }

    public AdDto updateAd(AdDto adDto) {
        return adMapper.adToAdDto(adRepository.save(adMapper.adDtoToAd(adDto)));
    }

    public ExtendedAdDto updateExtendedAd(ExtendedAdDto adDto) {
        return extendedAdMapper.toDto(adRepository.save(extendedAdMapper.toEntity(adDto)));
    }

    public AdDto findAd(Integer id) {
        return adMapper.adToAdDto(adRepository.findById(id).get());
    }

    public ExtendedAdDto findExtendedAd(Integer id) {
        return extendedAdMapper.toDto(adRepository.findById(id).get());
    }

    public void delete(Integer id, Authentication authentication) {
        Ad adForDell = adRepository.findById(id).orElseThrow(NotFoundException::new);
        Image deletedImage = imageService.findById(adForDell.getImage().getId());
        String deletedAdAuthorName = adForDell.getAuthor().getEmail();
        if (ValidationService.isAdmin(authentication) || ValidationService.isOwner(authentication, deletedAdAuthorName)) {
            adRepository.delete(adForDell);
            imageService.deleteImage(deletedImage);
        } else {
            throw new AccessDeniedException("Access is denied");
        }
    }
}
