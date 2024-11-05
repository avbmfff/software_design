package org.example.Repositories;

import org.example.Entities.User;
import org.example.Models.DatabaseConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    //получение всех пользователей
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        String query = "SELECT id,name,nickname,password FROM users";

        try (Connection conn = DatabaseConnector.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String nickname = rs.getString("nickname");
                String password = rs.getString("password");

                User user = new User(id, name, nickname, password);
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    //получение пользователя по id
    public User getUserById(int id) {
        String query = "SELECT id, name, nickname, password FROM users WHERE id = ?";
        User user = null;

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String name = rs.getString("name");
                String nickname = rs.getString("nickname");
                String password = rs.getString("password");

                user = new User(id, name, nickname, password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    //добавление нового пользователя
    public boolean addUser(User user) {
        String query = "INSERT INTO users (name, nickname, password) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getNickname());
            pstmt.setString(3, user.getPassword());

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //обновление данных пользователя
    public boolean updateUser(User user) {
        String query = "UPDATE users SET name = ?, nickname = ?, password = ? WHERE id = ?";

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getNickname());
            pstmt.setString(3, user.getPassword());
            pstmt.setInt(4, user.getId());

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //удаление пользователя
    public boolean deleteUserById(int id) {
        String query = "DELETE FROM users WHERE id = ?";

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, id);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
