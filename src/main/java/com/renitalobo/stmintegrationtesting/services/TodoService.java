package com.renitalobo.stmintegrationtesting.services;

import com.renitalobo.stmintegrationtesting.model.Todo;
import com.renitalobo.stmintegrationtesting.model.TodoList;
import com.renitalobo.stmintegrationtesting.repository.TodoListRepository;
import com.renitalobo.stmintegrationtesting.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TodoService {

    @Autowired
    private TodoListRepository todoListRepository;

    @Autowired
    private TodoRepository todoRepository;

    public Todo addTodo(Todo todo, String todoListId){
            Optional<TodoList> todoList = todoListRepository.findById(todoListId);
            TodoList currentTodoList;
            if (todoList.isPresent()){
                currentTodoList =todoList.get();
            }else{
                return null;
            }
            String uuid = UUID.randomUUID().toString().toUpperCase().replace("-", "");
            todo.setUuid(uuid);
            todo.setCompleted(false);
            todo.setTodoList(currentTodoList);
            return todoRepository.save(todo);
    }

    public String deleteTodo(String todoId){
        if(todoId != null) {
            Optional<Todo> optionalTodo = todoRepository.findById(todoId);
            Todo todo;
            if(optionalTodo.isPresent()){
                todo = optionalTodo.get();
            }else {
                return null;
            }
            todoRepository.delete(todo);
            return "Deleted";
        }else{
            return "Internal Error occurred";
        }
    }

    public Todo todoCompleteStatus(Todo todo){
        if (todo.isCompleted()){
            todo.setCompleted(false);
            return todoRepository.save(todo);
        }else{
            todo.setCompleted(true);
            return todoRepository.save(todo);
        }
    }

    public Todo todoPriority(Todo todo){
        if (todo.isPriority()){
            todo.setPriority(false);
            return todoRepository.save(todo);
        }else{
            todo.setPriority(true);
            return todoRepository.save(todo);
        }
    }


    public List<Todo> getAllTodosForList(String todoListId) {
        return todoRepository.findByTodoList_Uuid(todoListId);
    }

}
