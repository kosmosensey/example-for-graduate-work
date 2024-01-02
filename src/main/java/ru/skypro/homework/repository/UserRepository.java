package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.entities.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Modifying
    @Query(value = "update users set first_name  = ? , last_name = ? , phone = ? where id = ?", nativeQuery = true)
    int updateSomeFields(String firstName, String lastName, String phone, Integer id);


    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
}
