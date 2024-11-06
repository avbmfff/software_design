package org.example.Entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; // идентификатор задачи
    private String title; // заголовок(название задачи)
    private String status; // статус задачи
    private int authorId; // идентификатор создателя задачи
    private Integer[] workersIds; // идентификаторы работников над этой задачей (массив вместо списка)
    private Date dedicatedTime; // сроки выполнения задачи
    private String priority; // приоритет задачи
    private String description; // описание задачи
    private Integer[] changelogsIds; // список логов изменения задачи (массив вместо списка)

    // конструктор для выгрузки данных из БД
    public Task(int id, String title, String status, int authorId, Integer[] workersIds, Date dedicatedTime, String priority, String description, Integer[] changelogsIds) {
        this.id = id;
        this.title = title;
        this.status = status;
        this.authorId = authorId;
        this.workersIds = workersIds;
        this.dedicatedTime = dedicatedTime;
        this.priority = priority;
        this.description = description;
        this.changelogsIds = changelogsIds;
    }

    // создание задачи
    public Task(int id, String title, String status, int authorId, Date dedicatedTime, String priority, String description) {
        this.id = id;
        this.title = title;
        this.status = status;
        this.authorId = authorId;
        this.workersIds = new Integer[0]; // инициализация пустым массивом
        this.dedicatedTime = dedicatedTime;
        this.priority = priority;
        this.description = description;
    }

    public Task() {
    }

    // обновление задачи
    public void updateTask(String title, String status, Date dedicatedTime, String priority, String description) {
        this.title = title;
        this.status = status;
        this.dedicatedTime = dedicatedTime;
        this.priority = priority;
        this.description = description;
    }

    // добавление работника к задаче
    public void addWorkerToTask(int workerId) {
        // преобразуем workersIds в список, добавляем новый работник, а затем возвращаем массив
        Integer[] newArray = new Integer[workersIds.length + 1];
        System.arraycopy(workersIds, 0, newArray, 0, workersIds.length);
        newArray[workersIds.length] = workerId;
        workersIds = newArray;
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

    public Integer[] getWorkersIds() {
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

    public Integer[] getChangelogsIds() {
        return changelogsIds;
    }
}
