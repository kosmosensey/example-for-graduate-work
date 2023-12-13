package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.dto.Login;

import javax.swing.event.HyperlinkEvent;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RequestMapping("/ads")
@RestController
@RequiredArgsConstructor
public class AdsController {

    @DeleteMapping("/{id}")
    public ResponseEntity<ExtendedAd> removeAd(@PathVariable Long id) {
            return ResponseEntity.ok().build();
//            реализовать
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ExtendedAd> updateAd(@PathVariable Long id, @RequestBody ExtendedAd extendedAd) {
        return ResponseEntity.ok(extendedAd);
//            реализовать
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @GetMapping("/me")
    public ResponseEntity<List<ExtendedAd>> getAdsMe() {
        List<ExtendedAd> emptyListOfAds = new ArrayList<>();
        return ResponseEntity.ok(emptyListOfAds);
//            реализовать
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }


}
