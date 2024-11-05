package org.example.Repositories;

import org.example.Entities.Task;
import org.example.Models.DatabaseConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class TaskRepository {

    // Метод для получения всех задач
    public List<Task> getAllTasks() {
        List<Task> tasks = new ArrayList<>();
        String query = "SELECT id, title, status, author_id, workers_ids, dedicated_time, priority, description FROM tasks";

        try (Connection conn = DatabaseConnector.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Task task = mapResultSetToTask(rs);
                tasks.add(task);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tasks;
    }

    // Метод для получения задачи по ID
    public Task getTaskById(int id) {
        String query = "SELECT id, title, status, author_id, workers_ids, dedicated_time, priority, description FROM tasks WHERE id = ?";
        Task task = null;

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                task = mapResultSetToTask(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return task;
    }

    // Метод для добавления новой задачи
    public boolean addTask(Task task) {
        String query = "INSERT INTO tasks (title, status, author_id, workers_ids, dedicated_time, priority, description) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            setTaskParams(pstmt, task);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Метод для обновления данных задачи по ID
    public boolean updateTask(Task task) {
        String query = "UPDATE tasks SET title = ?, status = ?, author_id = ?, workers_ids = ?, dedicated_time = ?, " +
                "priority = ?, description = ? WHERE id = ?";

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            setTaskParams(pstmt, task);
            pstmt.setInt(8, task.getId()); // Устанавливаем ID задачи
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Метод для удаления задачи по ID
    public boolean deleteTaskById(int id) {
        String query = "DELETE FROM tasks WHERE id = ?";

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

    // Вспомогательный метод для маппинга ResultSet на Task
    private Task mapResultSetToTask(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String title = rs.getString("title");
        String status = rs.getString("status");
        int authorId = rs.getInt("author_id");
        List<Integer> workersIds = convertStringToList(rs.getString("workers_ids"));
        Date dedicatedTime = rs.getDate("dedicated_time");
        String priority = rs.getString("priority");
        String description = rs.getString("description");
        List<Integer> changelogIds = convertStringToList(rs.getString("changelog_ids"));

        return new Task(id, title, status, authorId, workersIds, dedicatedTime, priority, description, changelogIds);
    }

    // Вспомогательный метод для установки параметров PreparedStatement
    private void setTaskParams(PreparedStatement pstmt, Task task) throws SQLException {
        pstmt.setString(1, task.getTitle());
        pstmt.setString(2, task.getStatus());
        pstmt.setInt(3, task.getAuthorId());
        pstmt.setString(4, convertListToString(task.getWorkersIds()));
        pstmt.setDate(5, new java.sql.Date(task.getDedicatedTime().getTime()));
        pstmt.setString(6, task.getPriority());
        pstmt.setString(7, task.getDescription());
    }

    // Вспомогательные методы для конвертации списков в строки и обратно
    private List<Integer> convertStringToList(String str) {
        if (str == null || str.isEmpty()) return new ArrayList<>();
        return Arrays.stream(str.split(",")).map(Integer::parseInt).toList();
    }

    private String convertListToString(List<?> list) {
        if (list == null || list.isEmpty()) return "";
        return String.join(",", list.stream().map(Object::toString).toList());
    }
}
