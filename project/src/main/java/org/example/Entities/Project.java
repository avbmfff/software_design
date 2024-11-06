package org.example.Entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; // идентификатор проекта
    private String title; // название проекта
    private String description; // описание проекта
    private int authorId; // идентификатор создателя проекта
    private Date startDate; // дата начала(создания) проекта
    private Date endDate; // дата окончания проекта
    private Integer[] workersIds; // массив идентификаторов работников в проекте
    private Integer[] tasksIds; // массив идентификаторов задач в проекте
    private String[] taskStatusesIds; // пул возможных статусов для задач в данном проекте
    private String defaultStatus; // стандартный(дефолтный) статус для задач в проекте
    private Integer[] changelogsIds; // массив логов изменений в проекте

    // конструктор для выгрузки данных из БД
    public Project(int id, String title, String description, int authorId, Date startDate, Date endDate,
                   Integer[] workersIds, Integer[] tasksIds, String[] taskStatusesIds, String defaultStatus,
                   Integer[] changelogsIds) {
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

    // создание проекта
    public Project(int id, String title, int authorId) {
        this.id = id;
        this.title = title;
        this.description = "";
        this.authorId = authorId;
        this.startDate = new Date();
        this.endDate = null;
        this.workersIds = new Integer[0]; // инициализация пустым массивом
        this.tasksIds = new Integer[0]; // инициализация пустым массивом
        this.taskStatusesIds = new String[0]; // инициализация пустым массивом
        this.changelogsIds = new Integer[0]; // инициализация пустым массивом
        this.defaultStatus = "В процессе"; // дефолтный статус
    }

    // пустой конструктор для JPA
    public Project() {}

    // добавление пользователя в проект
    public void addUserToProject(int userId) {
        // преобразуем workersIds в список, добавляем новый работник, а затем возвращаем массив
        Integer[] newArray = new Integer[workersIds.length + 1];
        System.arraycopy(workersIds, 0, newArray, 0, workersIds.length);
        newArray[workersIds.length] = userId;
        workersIds = newArray;
    }

    // добавление нового статуса в пул
    public void createNewStatus(String status) {
        if (!contains(taskStatusesIds, status)) {
            String[] newArray = new String[taskStatusesIds.length + 1];
            System.arraycopy(taskStatusesIds, 0, newArray, 0, taskStatusesIds.length);
            newArray[taskStatusesIds.length] = status;
            taskStatusesIds = newArray;
        }
    }

    // удаление статуса из пула
    public void deleteProjectStatus(String status) {
        String[] newArray = new String[taskStatusesIds.length - 1];
        int index = 0;
        for (String currentStatus : taskStatusesIds) {
            if (!currentStatus.equals(status)) {
                newArray[index++] = currentStatus;
            }
        }
        if (newArray.length == 0) {
            newArray = new String[]{this.defaultStatus}; // если все статусы удалены, добавляем дефолтный
        }
        taskStatusesIds = newArray;
    }

    // утилитный метод для проверки наличия статуса
    private boolean contains(String[] array, String value) {
        for (String s : array) {
            if (s.equals(value)) {
                return true;
            }
        }
        return false;
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

    public Integer[] getWorkersIds() {
        return this.workersIds;
    }

    public Integer[] getTasksIds() {
        return this.tasksIds;
    }

    public String[] getTaskStatuses() {
        return this.taskStatusesIds;
    }

    public Integer[] getChangelogsIds() {
        return this.changelogsIds;
    }

    public String getDefaultStatus() {
        return this.defaultStatus;
    }
}
