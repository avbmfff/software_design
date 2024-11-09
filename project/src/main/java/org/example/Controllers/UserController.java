package org.example.Controllers;

import org.example.Entities.Project;
import org.example.Entities.User;
import org.example.Services.ProjectService;
import org.example.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Получение всех пользователей
    @GetMapping("/getAll")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // Получение пользователя по ID
    @GetMapping("/getById/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") int id) {
        Optional<User> user = userService.getUserById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Получение пользователя по никнейму
    @GetMapping("/getByNickname/{nickname}")
    public ResponseEntity<User> getUserByNickname(@PathVariable("nickname") String nickname) {
        Optional<User> user = userService.getUserByNickname(nickname);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Добавление нового пользователя
    @PostMapping("/addUser")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        User createdUser = userService.addUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    // Обновление данных пользователя
    @PutMapping("/updateUser/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") int id, @RequestBody User user) {
        if (user.getId() != id) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        User updatedUser = userService.updateUser(user);
        return ResponseEntity.ok(updatedUser);
    }

    // Удаление пользователя
    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable("id") int id) {
        userService.deleteUserById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/register")
    public String registerUser(@RequestBody User user) {
        if (userService.getUserByNickname(user.getNickname()).isPresent()) {
            return "{\"message\": \"User with this nickname already exists\"}";
        }
        userService.addUser(user);
        return "{\"message\": \"User registered successfully!\"}";
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) {
        Optional<User> existingUser = userService.getUserByNickname(user.getNickname());

        // Проверяем, существует ли пользователь с таким никнеймом и совпадает ли пароль
        if (existingUser.isPresent() && existingUser.get().getPassword().equals(user.getPassword())) {

            // Возвращаем информацию о пользователе, чтобы сохранить ее на фронтенде
            User loggedInUser = existingUser.get();
            return ResponseEntity.ok(new LoginResponse("Login successful!", loggedInUser));
        }

        // Если логин не успешен, возвращаем ошибку
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new LoginResponse("Invalid credentials", null));
    }

    // Класс для ответа при логине
    public static class LoginResponse {
        private String message;
        private User user;

        public LoginResponse(String message, User user) {
            this.message = message;
            this.user = user;
        }

        // Геттеры и сеттеры
        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }
    }

}
