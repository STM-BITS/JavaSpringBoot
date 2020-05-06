package com.renitalobo.stmintegrationtesting.controller;

import com.renitalobo.stmintegrationtesting.model.Todo;
import com.renitalobo.stmintegrationtesting.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @PostMapping(value = "/create")
    public Todo createTodo(@RequestBody Todo todo) {
        if (todo != null) {
            return todoService.addTodo(todo);
        } else
            return null;
    }

    @DeleteMapping(value = "/delete/{todoId}")
    public String deleteTodo(@PathVariable String todoId) {
        if (todoId != null) {
            return todoService.deleteTodo(todoId);
        } else {
            return null;
        }
    }

    @GetMapping(value = "/getAllTodos")
    public List<Todo> getAllTodosForList() {
        return todoService.getAllTodosForList();
    }

}
