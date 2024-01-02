package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.entities.Image;

import java.io.IOException;

public interface ImageService {
    Image getById(Integer id);

    Image saveToDataBase(MultipartFile multipartFile) throws IOException;

    void deleteImage(Image image);

    Image findById(Integer id);
}
