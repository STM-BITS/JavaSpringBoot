package com.renitalobo.stmintegrationtesting.controller;

import com.renitalobo.stmintegrationtesting.model.Todo;
import com.renitalobo.stmintegrationtesting.model.TodoList;
import com.renitalobo.stmintegrationtesting.services.TodoListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TodoListController {

    @Autowired
    private TodoListService todoListService;

    @PostMapping(value = "/create/todolist")
    public TodoList createList(@RequestBody TodoList todoList){
        if (todoList != null){
            return todoListService.createTodoList(todoList);
        }else{
            return null;
        }
    }

    @DeleteMapping(value = "/{todoListId}/delete")
    public void deleteList(@PathVariable String todoListId){
        if(todoListId != null){
            todoListService.deleteTodoList(todoListId);
        } else {
            return;
        }
    }

    @GetMapping(value = "/viewAllTodoList")
    public List<TodoList> getAllTodoList() {
        return todoListService.viewAllTodoList();
    }

    @PostMapping(value = "/{todoListId}/updateTodoListName")
    public String updateTodoListName(@RequestBody TodoList todoList, @PathVariable String todoListId) {
        if (todoListId != null) {
            return todoListService.updateTodoListName(todoList, todoListId);
        } else {
            return null;
        }
    }


}
