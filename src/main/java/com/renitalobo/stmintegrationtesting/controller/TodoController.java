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
    public Todo todoCompleteStatus(@RequestBody Todo todo){
        if(todo != null){
            return todoService.todoCompleteStatus(todo);
        }else{
            return null;
        }
    }

    @PostMapping(value = "/{todoListId}/todoPriority/{todoId}")
    public Todo todoPriority(@RequestBody Todo todo){
        if(todo != null){
            return todoService.todoPriority(todo);
        }else{
            return null;
        }
    }

    @GetMapping(value = "/{todoListId}/getTodos/")
    public List<Todo> getAllTodosForList(@PathVariable String todoListId){
        if (todoListId!= null){
            return todoService.getAllTodosForList(todoListId);
        }else{
            return null;
        }
    }


}
