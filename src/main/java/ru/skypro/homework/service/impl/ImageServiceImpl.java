package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.entities.Image;
import ru.skypro.homework.exception.NotFoundException;
import ru.skypro.homework.repository.ImageRepository;
import ru.skypro.homework.service.ImageService;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {
    private final ImageRepository imageRepository;

    @Override
    public Image getById(Integer id) {
        return imageRepository.findById(id).orElseThrow(NotFoundException::new);
    }
    @Override
    public Image saveInDataBase(MultipartFile file) throws IOException {
        Image image = new Image();
        image.setData(file.getBytes());
        image.setFileSize(file.getSize());
        image.setMediaType(file.getContentType());
        imageRepository.save(image);
        return image;
    }
}
