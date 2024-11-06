package org.example.Models;

import org.example.Entities.User;
import org.example.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TaskTrackerService {

    @Autowired
    private UserRepository userRepository; // репозиторий для работы с пользователями

    // Метод для регистрации пользователя
    public void registerUser(String name, String nickname, String password) {
        // Проверяем, существует ли уже пользователь с таким ником
        Optional<User> existingUser = userRepository.findByNickname(nickname);
        if (existingUser.isPresent()) {
            System.out.println("User with this nickname already exists.");
            return;
        }

        // Создаем нового пользователя
        User newUser = new User(name, nickname, password);

        // Сохраняем пользователя в базе данных
        userRepository.save(newUser);

        System.out.println("User registered successfully: " + newUser.getNickname());
    }
}
