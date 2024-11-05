package Services;

import entities.Project;
import entities.User;

import java.util.ArrayList;
import java.util.List;

public class TaskTrackerService {
    List<User> users;
    List<Project> projects;
    LoggingService loggingService;

    public static void main(String[] args) {
        //todo: load data from database(users,projects)
        TaskTrackerService taskTrackerService = new TaskTrackerService();
        taskTrackerService.loggingService = new LoggingService();
        taskTrackerService.users = new ArrayList<>();
        taskTrackerService.projects = new ArrayList<>();
    }

}
