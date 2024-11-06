package org.example.Repositories;

import org.example.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    // Метод для поиска пользователя по никнейму
    Optional<User> findByNickname(String nickname);
}
