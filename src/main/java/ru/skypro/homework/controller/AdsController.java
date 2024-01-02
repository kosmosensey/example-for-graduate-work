package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.ExtendedAdDto;
import ru.skypro.homework.service.impl.AdServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@RestController
@CrossOrigin(value = "http://localhost:3000")
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/ads")
public class AdsController {
    private final AdServiceImpl adService;


    @GetMapping
    public ResponseEntity<AdsDto> getAllAds() {
        //  логика
        if (!adService.getAllAds().isEmpty()) {
            log.info("all ads");
            return new ResponseEntity<>(new AdsDto(adService.getAllAds().size(), adService.getAllAds()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AdDto> addAd(@RequestPart("properties") CreateOrUpdateAdDto createAds,
                                       @RequestPart("image") MultipartFile image,
                                       HttpServletRequest request) {
        String userNameEmail = request.getRemoteUser();
        log.info("Adding ad with title: {}", createAds.getTitle());
        // логика
        // добавить обработку картинки
        if (image != null && createAds != null) { // условие проверки наличия авторизации
            AdDto adDto = adService.createAd(createAds, userNameEmail, image.getName());
            return new ResponseEntity<>(adDto, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExtendedAdDto> getAdById(@PathVariable("id") Integer id) {
        // получения объявления по id
        if (adService.findExtendedAd(id) != null) {
            return new ResponseEntity<>(adService.findExtendedAd(id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeAd(@PathVariable Integer id, Authentication authentication) {
        try {
            adService.delete(id, authentication);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (HttpClientErrorException.NotFound e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
//                    реализовать
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ExtendedAdDto> updateAd(@PathVariable Integer id, @RequestBody ExtendedAdDto extendedAd) {
        if (adService.findExtendedAd(id) != null) {
            return new ResponseEntity<>(adService.updateExtendedAd(extendedAd), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        //            реализовать
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @GetMapping("/me")
    public ResponseEntity<List<AdDto>> getAdsMe() {
        if (!adService.getAllAds().isEmpty()) {
            return new ResponseEntity<>(adService.getAllAds(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
//            реализовать
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @PatchMapping(value = "/{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<List<String>> updateImage(@PathVariable Integer id, @RequestParam MultipartFile image) throws IOException {
        ExtendedAdDto ad = null;
        if (ad == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(image.getBytes());
            InputStreamResource inputStreamResource = new InputStreamResource(inputStream);

            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body((List<String>) inputStreamResource);

        }
    }


}
