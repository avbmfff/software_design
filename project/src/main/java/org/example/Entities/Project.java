package org.example.Entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Project {
    private int id; //идентификатор проекта
    private String title; //название проекта
    private String description; //описание проекта
    private int authorId; //идентификатор создателя проекта
    private Date startDate; //дата начала(создания) проекта
    private Date endDate; //дата окончания проекта
    private List<Integer> workersIds; //список работников в проекте
    private List<Integer> tasksIds; //список задач в проекте
    private List<String> taskStatusesIds; //пул возможных статусов для задач в данном проекте
    private String defaultStatus; //стандартный(дефолтный) статус для задач в проекте
    private List<Integer> changelogsIds; //логи изменений в проекте

    //конструктор для выгрузки данных из БД
    public Project(int id, String title, String description, int authorId, Date startDate, Date endDate, List<Integer> workersIds, List<Integer> tasksIds, List<String> taskStatusesIds, String defaultStatus, List<Integer> changelogsIds) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.authorId = authorId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.workersIds = workersIds;
        this.tasksIds = tasksIds;
        this.taskStatusesIds = taskStatusesIds;
        this.defaultStatus = defaultStatus;
        this.changelogsIds = changelogsIds;
    }

    //создание проекта
    public Project(int id,String title, int authorId) {
        this.id = id;
        this.title = title;
        this.description = "";
        this.authorId = authorId;
        this.startDate = new Date();
        this.endDate = null;
        this.workersIds = new ArrayList<>();
        this.tasksIds = new ArrayList<>();
        this.taskStatusesIds = new ArrayList<>();
        this.changelogsIds = new ArrayList<>();
        this.defaultStatus = "В процессе"; //todo: add hex color description in beginning
    }

    //добавление пользователя в проект
    public void addUserToProject(int userId){
        if(!workersIds.contains(userId)){
            workersIds.add(userId);
        }
        else{
            System.out.println("Worker is already in project");
        }
    }

    //добавление нового статуса в пул
    public void createNewStatus(String status){
        //todo: add status string to statuses pool
        if(!taskStatusesIds.contains(status)){
            taskStatusesIds.add(status);
        }
    }

    //удаление статуса из пула
    public void deleteProjectStatus(String status){
        //todo: parse status string
        taskStatusesIds.remove(status);
        if(taskStatusesIds.isEmpty()){
            taskStatusesIds.add(this.defaultStatus);
        }
    }

    public int getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getDescription() {
        return this.description;
    }

    public int getAuthorId() {
        return this.authorId;
    }

    public Date getStartDate() {
        return this.startDate;
    }

    public Date getEndDate() {
        return this.endDate;
    }

    public List<Integer> getWorkersIds() {
        return this.workersIds;
    }

    public List<Integer> getTasksIds() {
        return this.tasksIds;
    }

    public List<String> getTaskStatuses() {
        return this.taskStatusesIds;
    }

    public List<Integer> getChangelogsIds() {
        return this.changelogsIds;
    }

    public String getDefaultStatus() { return this.defaultStatus; }
}
