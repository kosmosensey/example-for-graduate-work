package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.entities.Ad;

import java.util.List;

@Repository
public interface AdRepository extends JpaRepository<Ad, Integer> {

    Ad findAdByPk(int id);

}
