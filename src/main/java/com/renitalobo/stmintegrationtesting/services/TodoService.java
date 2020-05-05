package com.renitalobo.stmintegrationtesting.services;

import com.renitalobo.stmintegrationtesting.model.Todo;
import com.renitalobo.stmintegrationtesting.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    public Todo addTodo(Todo todo) {
        String uuid = UUID.randomUUID().toString().toUpperCase().replace("-", "");
        todo.setUuid(uuid);
        return todoRepository.save(todo);
    }

    public String deleteTodo(String todoId) {

        Optional<Todo> optionalTodo = todoRepository.findById(todoId);
        Todo todo;
        if (optionalTodo.isPresent()) {
            todo = optionalTodo.get();
            todoRepository.delete(todo);
            return "Deleted";
        } else {
            return "Internal Error occurred";
        }
    }

    public List<Todo> getAllTodosForList() {
        return todoRepository.findAll();
    }

}
