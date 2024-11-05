package org.example.Services;

import org.example.Entities.ChangeLog;
import org.example.Entities.Project;
import org.example.Entities.Task;
import org.example.Entities.User;

import java.util.ArrayList;
import java.util.List;

public class TaskTrackerService {
    List<User> users;
    List<Project> projects;
    List<Task> tasks;
    List<ChangeLog> changeLogs;
    LoggingService loggingService;

    public TaskTrackerService() {
        this.users = new ArrayList<>();
        this.projects = new ArrayList<>();
        this.loggingService = new LoggingService();
    }
}
