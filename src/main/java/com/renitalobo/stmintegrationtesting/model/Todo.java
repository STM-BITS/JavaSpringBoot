package com.renitalobo.stmintegrationtesting.model;

import javax.persistence.*;

@Entity
public class Todo {

    @Id
    @Column
    private String uuid;

    @Column
    private String taskName;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

}
