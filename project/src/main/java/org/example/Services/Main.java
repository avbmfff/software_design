package org.example.Services;

import org.example.Entities.User;
import org.example.Models.DatabaseConnector;

public class Main {
    public static void main(String[] args) {
        //todo: load data from database(users,projects)
        TaskTrackerService taskTrackerService = new TaskTrackerService();
        DatabaseConnector databaseConnector = new DatabaseConnector();
        taskTrackerService.users = databaseConnector.getUserRepository().getAllUsers();
        //taskTrackerService.projects = databaseConnector.getProjectRepository().getAllProjects();
        //taskTrackerService.tasks = databaseConnector.getTaskRepository().getAllTasks();
        //taskTrackerService.changeLogs = databaseConnector.getChangelogRepository().getAllChangeLogs();

        for (User user : taskTrackerService.users) {
            System.out.println("ID: " + user.getId() +
                    ", Name: " + user.getName() +
                    ", Nickname: " + user.getNickname() +
                    ", Password: " + user.getPassword());
        }
    }
}
