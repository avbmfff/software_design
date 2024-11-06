package org.example.Entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "changelogs")
public class ChangeLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; //идентификатор записи
    private int workerId; //работник,совершивший залогированное изменение
    private Date changeTime; //время публикации изменения(создания лога)
    private int workTime; //потраченное на внесение изменений время
    private String changedAttributes; //измененные атрибуты

    //конструктор для выгрузки данных из БД
    public ChangeLog(int id, int workerId, Date changeTime, int workTime, String changedAttributes) {
        this.id = id;
        this.workerId = workerId;
        this.changeTime = changeTime;
        this.workTime = workTime;
        this.changedAttributes = changedAttributes;
    }

    public ChangeLog(int id, int workerId, int workTime, String changedAttributes){
        this.id = id;
        this.workerId = workerId;
        this.changeTime = new Date();
        this.workTime = workTime;
        this.changedAttributes = changedAttributes;
    }

    public ChangeLog() {}

    public String getChangedAttributes() {
        return changedAttributes;
    }

    public int getWorkTime() {
        return workTime;
    }

    public Date getChangeTime() {
        return changeTime;
    }

    public int getWorkerId() {
        return workerId;
    }

    public int getId() {
        return id;
    }

}
