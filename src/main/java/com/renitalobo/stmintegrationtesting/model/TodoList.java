package com.renitalobo.stmintegrationtesting.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
public class TodoList {

    @Id
    private String uuid;

    @Column
    private String todoListName;

    @Column
    private boolean removeList;

    @OneToMany(mappedBy = "todoList", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Todo> todos = new ArrayList<>();

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getTodoListName() {
        return todoListName;
    }

    public void setTodoListName(String todoListName) {
        this.todoListName = todoListName;
    }

    public boolean isRemoveList() {
        return removeList;
    }

    public void setRemoveList(boolean removeList) {
        this.removeList = removeList;
    }

    public void addTodo(Todo todo){
        todos.add(todo);
        todo.setTodoList(this);
    }

    public void removeTodo(Todo todo){
        todo.setTodoList(null);
        this.todos.remove(todo);
    }

}
