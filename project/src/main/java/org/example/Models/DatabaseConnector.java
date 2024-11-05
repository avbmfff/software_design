package org.example.Models;

import org.example.Repositories.ChangelogRepository;
import org.example.Repositories.ProjectRepository;
import org.example.Repositories.TaskRepository;
import org.example.Repositories.UserRepository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
    private static final String URL = "jdbc:postgresql://localhost:5432/mydatabase";
    private static final String USER = "your_username";
    private static final String PASSWORD = "your_password";

    private static UserRepository userRepository;
    private static ProjectRepository projectRepository;
    private static TaskRepository taskRepository;
    private static ChangelogRepository changelogRepository;

    public DatabaseConnector(){
        userRepository = new UserRepository();
        projectRepository = new ProjectRepository();
        taskRepository = new TaskRepository();
        changelogRepository = new ChangelogRepository();
    }
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static UserRepository getUserRepository() {
        return userRepository;
    }

    public static ProjectRepository getProjectRepository() {
        return projectRepository;
    }

    public static TaskRepository getTaskRepository() {
        return taskRepository;
    }

    public static ChangelogRepository getChangelogRepository() {
        return changelogRepository;
    }
}
