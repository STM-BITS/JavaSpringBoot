package com.renitalobo.stmintegrationtesting.services;

import com.renitalobo.stmintegrationtesting.model.Todo;
import com.renitalobo.stmintegrationtesting.model.TodoList;
import com.renitalobo.stmintegrationtesting.repository.TodoListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TodoListService {

    @Autowired
    private TodoListRepository todoListRepository;

    public TodoList createTodoList(TodoList todoList){
        if(todoList != null){
            String uuid = UUID.randomUUID().toString().toUpperCase().replace("-", "");
            todoList.setUuid(uuid);
            return todoListRepository.save(todoList);
        }else{
            return null;
        }
    }



    public List<TodoList> viewAllTodoList(){
        List<TodoList> todoLists = todoListRepository.findAll();
        return todoLists;
    }

    public void deleteTodoList(String todoListId){
        todoListRepository.deleteById(todoListId);
    }


}
