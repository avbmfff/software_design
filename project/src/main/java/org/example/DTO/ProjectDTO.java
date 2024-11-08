package org.example.DTO;

import java.util.Date;

public class ProjectDTO {
    public int authorId;
    public String title;
    private String description;
    private Date startDate;
    private Date endDate;
    private String taskStatus;

    // Геттеры и сеттеры
    public int getAuthorId() {
        return authorId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public String getTaskStatus() {
        return taskStatus;
    }
}