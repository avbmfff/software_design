package org.example.Entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Task {
    private int id; //идентификатор задачи
    private String title; //заголовок(название задачи)
    private String status; //статус задачи
    private int authorId; //идентификатор создателя задачи
    private List<Integer> workersIds; //идентификаторы работников над этой задачей
    private Date dedicatedTime; //сроки выполнения задачи
    private String priority; //приоритет задачи
    private String description; //описание задачи

    //создание задачи
    public Task(int id,String title,String status,int authorId,Date dedicatedTime,String priority,String description) {
        this.id = id;
        this.title = title;
        this.status = status;
        this.authorId = authorId;
        this.workersIds = new ArrayList<>();
        this.dedicatedTime = dedicatedTime;
        this.priority = priority;
        this.description = description;
    }

    //обновление задачи
    public void updateTask(String title,String status,Date dedicatedTime, String priority, String description){
        this.title = title;
        this.status = status;
        this.dedicatedTime = dedicatedTime;
        this.priority = priority;
        this.description = description;
    }

    //добавление работника к задаче
    public void addWorkerToTask(int workerId){
        this.workersIds.add(workerId);
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getStatus() {
        return status;
    }

    public int getAuthorId() {
        return authorId;
    }

    public List<Integer> getWorkersIds() {
        return workersIds;
    }

    public Date getDedicatedTime() {
        return dedicatedTime;
    }

    public String getPriority() {
        return priority;
    }

    public String getDescription() {
        return description;
    }

    public List<ChangeLog> getChangelogsIds() {
        return changelogsIds;
    }

    private List<ChangeLog> changelogsIds; //список логов изменения задачи
}
