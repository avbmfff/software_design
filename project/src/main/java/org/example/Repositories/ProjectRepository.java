package org.example.Repositories;

import org.example.Entities.Project;
import org.example.Models.DatabaseConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class ProjectRepository {

    // Метод для получения всех проектов
    public List<Project> getAllProjects() {
        List<Project> projects = new ArrayList<>();
        String query = "SELECT id, title, description, author_id, start_date, end_date, workers_ids, tasks_ids, " +
                "task_statuses_ids, default_status, changelogs_ids FROM projects";

        try (Connection conn = DatabaseConnector.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Project project = mapResultSetToProject(rs);
                projects.add(project);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return projects;
    }

    // Метод для получения проекта по ID
    public Project getProjectById(int id) {
        String query = "SELECT id, title, description, author_id, start_date, end_date, workers_ids, tasks_ids, " +
                "task_statuses_ids, default_status, changelogs_ids FROM projects WHERE id = ?";
        Project project = null;

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                project = mapResultSetToProject(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return project;
    }

    // Метод для добавления нового проекта
    public boolean addProject(Project project) {
        String query = "INSERT INTO projects (title, description, author_id, start_date, end_date, workers_ids, tasks_ids, " +
                "task_statuses_ids, default_status, changelogs_ids) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            setProjectParams(pstmt, project);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Метод для обновления данных проекта по ID
    public boolean updateProject(Project project) {
        String query = "UPDATE projects SET title = ?, description = ?, author_id = ?, start_date = ?, end_date = ?, " +
                "workers_ids = ?, tasks_ids = ?, task_statuses_ids = ?, default_status = ?, changelogs_ids = ? " +
                "WHERE id = ?";

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            setProjectParams(pstmt, project);
            pstmt.setInt(11, project.getId()); // Устанавливаем ID проекта
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Метод для удаления проекта по ID
    public boolean deleteProjectById(int id) {
        String query = "DELETE FROM projects WHERE id = ?";

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

    // Вспомогательный метод для маппинга ResultSet на Project
    private Project mapResultSetToProject(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String title = rs.getString("title");
        String description = rs.getString("description");
        int authorId = rs.getInt("author_id");
        Date startDate = rs.getDate("start_date");
        Date endDate = rs.getDate("end_date");

        List<Integer> workersIds = convertStringToList(rs.getString("workers_ids"));
        List<Integer> tasksIds = convertStringToList(rs.getString("tasks_ids"));
        List<String> taskStatusesIds = convertStringToStringList(rs.getString("task_statuses_ids"));
        String defaultStatus = rs.getString("default_status");
        List<Integer> changelogsIds = convertStringToList(rs.getString("changelogs_ids"));

        return new Project(id, title, description, authorId, startDate, endDate, workersIds, tasksIds, taskStatusesIds, defaultStatus, changelogsIds);
    }

    // Вспомогательный метод для установки параметров PreparedStatement
    private void setProjectParams(PreparedStatement pstmt, Project project) throws SQLException {
        pstmt.setString(1, project.getTitle());
        pstmt.setString(2, project.getDescription());
        pstmt.setInt(3, project.getAuthorId());
        pstmt.setDate(4, new java.sql.Date(project.getStartDate().getTime()));
        pstmt.setDate(5, new java.sql.Date(project.getEndDate().getTime()));
        pstmt.setString(6, convertListToString(project.getWorkersIds()));
        pstmt.setString(7, convertListToString(project.getTasksIds()));
        pstmt.setString(8, convertListToString(project.getTaskStatuses()));
        pstmt.setString(9, project.getDefaultStatus());
        pstmt.setString(10, convertListToString(project.getChangelogsIds()));
    }

    // Вспомогательные методы для конвертации списков в строки и обратно
    private List<Integer> convertStringToList(String str) {
        if (str == null || str.isEmpty()) return new ArrayList<>();
        return Arrays.stream(str.split(",")).map(Integer::parseInt).toList();
    }

    private List<String> convertStringToStringList(String str) {
        if (str == null || str.isEmpty()) return new ArrayList<>();
        return Arrays.asList(str.split(","));
    }

    private String convertListToString(List<?> list) {
        if (list == null || list.isEmpty()) return "";
        return String.join(",", list.stream().map(Object::toString).toList());
    }
}
