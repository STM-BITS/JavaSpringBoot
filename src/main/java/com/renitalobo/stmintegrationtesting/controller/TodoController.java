package com.renitalobo.stmintegrationtesting.controller;

import com.renitalobo.stmintegrationtesting.model.Todo;
import com.renitalobo.stmintegrationtesting.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TodoController {

    @Autowired
    private TodoService todoService;

    @PostMapping(value = "/{todoListId}/create")
    public Todo createTodo(@RequestBody Todo todo, @PathVariable String todoListId){
        if(todo != null && todoListId != null){
            return todoService.addTodo(todo, todoListId);
        }else
            return null;
    }

    @DeleteMapping(value = "/{todoListId}/delete/{todoId}")
    public void deleteTodo(@PathVariable String todoId){
        if(todoId != null){
            todoService.deleteTodo(todoId);
        }
    }

    @PostMapping(value = "/{todoListId}/todoStatus/{todoId}")
    public Todo todoCompleteStatus(@PathVariable String todoId){
        if(todoId != null){
            return todoService.todoCompleteStatus(todoId);
        }else{
            return null;
        }
    }

    @PostMapping(value = "/{todoListId}/todoPriority/{todoId}")
    public Todo todoPriority(@PathVariable String todoId){
        if(todoId != null){
            return todoService.todoPriority(todoId);
        } else {
            return null;
        }
    }

    @GetMapping(value = "/{todoListId}/getAllTodos")
    public List<Todo> getAllTodosForList(@PathVariable String todoListId) {
        return todoService.getAllTodosForList(todoListId);
    }

    @PostMapping(value = "/{todoListId}/updateTodoName/{todoId}")
    public Todo updateTodoName(@RequestBody Todo todo, @PathVariable String todoId) {
        if (todoId != null) {
            return todoService.updateTodoName(todo, todoId);
        } else {
            return null;
        }
    }

}
