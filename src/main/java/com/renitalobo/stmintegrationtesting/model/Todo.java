package com.renitalobo.stmintegrationtesting.model;

import javax.persistence.*;

@Entity
public class Todo {

    @Id
    @Column
    private String uuid;

    @ManyToOne
    private TodoList todoList;

//    @Column
//    private String todoListId;

    @Column
    private String taskName;

    @Column
    private boolean isCompleted;

    @Column
    private boolean isPriority;

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

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public boolean isPriority() {
        return isPriority;
    }

    public void setPriority(boolean priority) {
        isPriority = priority;
    }

//    public String getTodoListId() {
//        return todoListId;
//    }
//
//    public void setTodoListId(String todoListId) {
//        this.todoListId = todoListId;
//    }

    public TodoList getTodoList() {
        return todoList;
    }

    public void setTodoList(TodoList todoList) {
        this.todoList = todoList;
    }
}
