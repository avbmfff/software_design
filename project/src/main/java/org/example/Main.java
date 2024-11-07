package org.example;

import org.example.Models.TaskTrackerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {

    @Autowired
    private TaskTrackerService taskTrackerService; // инжектируем TaskTrackerService

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
