package org.example.Models;

import org.example.Repositories.ChangelogRepository;
import org.example.Repositories.ProjectRepository;
import org.example.Repositories.TaskRepository;
import org.example.Repositories.UserRepository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
    private static final String URL = "jdbc:postgresql://dpg-csl81e3qf0us73c42em0-a.oregon-postgres.render.com:5432/bdppo";
    private static final String USER = "bdppo_user";
    private static final String PASSWORD = "RJLYbpECa0Rzcv076vQX7wxJmtzvVuDX";

    private UserRepository userRepository;
    private ProjectRepository projectRepository;
    private TaskRepository taskRepository;
    private ChangelogRepository changelogRepository;

    public DatabaseConnector(){
        userRepository = new UserRepository();
        projectRepository = new ProjectRepository();
        taskRepository = new TaskRepository();
        changelogRepository = new ChangelogRepository();
    }
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public UserRepository getUserRepository() {
        return this.userRepository;
    }

    public ProjectRepository getProjectRepository() {
        return this.projectRepository;
    }

    public TaskRepository getTaskRepository() {
        return this.taskRepository;
    }

    public ChangelogRepository getChangelogRepository() {
        return this.changelogRepository;
    }
}
