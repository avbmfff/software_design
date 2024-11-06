package org.example.Services;

import org.example.Entities.User;
import org.example.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Получение всех пользователей
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Получение пользователя по ID
    public Optional<User> getUserById(int id) {
        return userRepository.findById(id);
    }

    // Получение пользователя по никнейму
    public Optional<User> getUserByNickname(String nickname) {
        return userRepository.findByNickname(nickname);
    }

    // Добавление нового пользователя
    public User addUser(User user) {
        return userRepository.save(user);
    }

    // Обновление данных пользователя
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    // Удаление пользователя
    public void deleteUserById(int id) {
        userRepository.deleteById(id);
    }
}

