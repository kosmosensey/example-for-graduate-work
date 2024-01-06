package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.ExtendedAdDto;
import ru.skypro.homework.dto.mapper.AdMapper;
import ru.skypro.homework.dto.mapper.CreateOrUpdateAdMapper;
import ru.skypro.homework.dto.mapper.ExtendedAdMapper;
import ru.skypro.homework.entities.Ad;
import ru.skypro.homework.entities.User;
import ru.skypro.homework.exception.AccessErrorException;
import ru.skypro.homework.exception.AdNotFoundException;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.service.AdService;
import ru.skypro.homework.service.UserService;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdServiceImpl implements AdService {
    private final AdRepository adRepository;
    private final UserService userService;
    private final CreateOrUpdateAdMapper createOrUpdateAdMapper;

    public AdsDto getAllAds() {
        List<AdDto> adList = adRepository.findAll().stream()
                .map(AdMapper::adToAdDto)
                .collect(Collectors.toList());
        return new AdsDto(adList.size(), adList);
    }

    @Override
    public AdDto createAd(CreateOrUpdateAdDto adDto, MultipartFile image, Authentication authentication) throws IOException {
        User user = userService.getUser(authentication.getName());
        Ad ad = createOrUpdateAdMapper.mapToAd(adDto);
        ad.setUser(user);
        ad.setTitle(adDto.getTitle());
        ad.setPrice(adDto.getPrice());
        ad.setDescription(adDto.getDescription());
        ad.setData(image.getBytes());
        ad.setImageUrl("/image/" + image.getOriginalFilename());
        return AdMapper.adToAdDto(adRepository.save(ad));
    }

    @Override
    public AdsDto getMyAds(Authentication authentication) {
        Integer userId = userService.findByEmail(authentication.getName()).getId();
        List<AdDto> allMyAds = adRepository.findAll()
                .stream()
                .filter(ad -> ad.getUser().getId().equals(userId))
                .map(AdMapper::adToAdDto)
                .collect(Collectors.toList());
        return new AdsDto(allMyAds.size(), allMyAds);
    }

    @Override
    public AdDto updateAds(Integer id, CreateOrUpdateAdDto createOrUpdateAdDto) {
        Ad ad = adRepository.findAdById(id);
        if (ad == null) {
            return null;
        }
        ad.setTitle(createOrUpdateAdDto.getTitle());
        ad.setPrice(createOrUpdateAdDto.getPrice());
        ad.setDescription(createOrUpdateAdDto.getDescription());
        adRepository.save(ad);
        return AdMapper.adToAdDto(ad);
    }

    @Override
    public void deleteAd(Integer id, Authentication authentication) {
        Ad deletedAd = adRepository.findAdById(id);
        if (CheckRoleService.isAdminOrOwnerAd(authentication, deletedAd.getUser().getEmail())) {
            adRepository.deleteById(id);
        } else {
            throw new AccessErrorException();
        }
    }

    @Override
    public ExtendedAdDto findExtendedAd(Integer id) {
        Ad ad = adRepository.findAdById(id);
        if (ad != null) {
            return ExtendedAdMapper.toDto(ad);
        }
        return null;
    }

    @Override
    public byte[] updateImageAd(Integer id, MultipartFile image) throws IOException {
        Ad ad = adRepository.findById(id).orElseThrow(AdNotFoundException::new);
        ad.setData(ad.getData());
        ad.setImageUrl("/images/" + ad.getId());
        adRepository.save(ad);
        return ad.getData();
    }

    @Override
    public byte[] getImage(Integer imageId) throws IOException {
        return adRepository.findById(imageId).get().getData();
    }
}
