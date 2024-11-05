package org.example.Services;

import org.example.Entities.User;
import org.example.Models.DatabaseConnector;

public class Main {
    public static void main(String[] args) {
        //todo: load data from database(users,projects)
        TaskTrackerService taskTrackerService = new TaskTrackerService();
        taskTrackerService.users = DatabaseConnector.getUserRepository().getAllUsers();
        taskTrackerService.projects = DatabaseConnector.getProjectRepository().getAllProjects();
        taskTrackerService.tasks = DatabaseConnector.getTaskRepository().getAllTasks();
        taskTrackerService.changeLogs = DatabaseConnector.getChangelogRepository().getAllChangeLogs();

        for (User user : taskTrackerService.users) {
            System.out.println("ID: " + user.getId() +
                    ", Name: " + user.getName() +
                    ", Nickname: " + user.getNickname() +
                    ", Password: " + user.getPassword());
        }
    }
}
