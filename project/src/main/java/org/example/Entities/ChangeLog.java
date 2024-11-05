package org.example.Entities;

import java.util.Date;

public class ChangeLog {
    private int id; //идентификатор записи
    private int workerId; //работник,совершивший залогированное изменение
    private Date changeTime; //время публикации изменения(создания лога)
    private int workTime; //потраченное на внесение изменений время
    private String changedAttributes; //измененные атрибуты

    public ChangeLog(int id, int workerId, int workTime,String changedAttributes){
        this.id = id;
        this.workerId = workerId;
        this.changeTime = new Date();
        this.workTime = workTime;
        this.changedAttributes = changedAttributes;
    }

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
