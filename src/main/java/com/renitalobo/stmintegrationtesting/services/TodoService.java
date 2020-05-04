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

    public Todo todoCompleteStatus(String todoId){
        Optional<Todo> t = todoRepository.findById(todoId);
        Todo editTodo;
        if (t.isPresent()){
            editTodo = t.get();
        }else {
            return null;
        }

        if (editTodo.isCompleted()){
            editTodo.setCompleted(false);
            return todoRepository.save(editTodo);
        }else{
            editTodo.setCompleted(true);
            return todoRepository.save(editTodo);
        }
    }

    public Todo todoPriority(String todoId){
        Optional<Todo> t = todoRepository.findById(todoId);
        Todo editTodo;
        if (t.isPresent()){
            editTodo = t.get();
        }else {
            return null;
        }

        if (editTodo.isPriority()){
            editTodo.setPriority(false);
            return todoRepository.save(editTodo);
        }else{
            editTodo.setPriority(true);
            return todoRepository.save(editTodo);
        }
    }


    public List<Todo> getAllTodosForList(String todoListId) {
        return todoRepository.findByTodoList_Uuid(todoListId);
    }

}
