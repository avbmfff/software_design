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
}
