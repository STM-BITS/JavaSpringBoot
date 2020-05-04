# JavaSpringBoot
A Spring boot application for todo list

The application creates a todolist which contains various todos

The todos can be set as priorty and can be marked as completed. The todo can also be deleted from them list

## Steps to execute:

### 1. Create Todo List
```
POST http://localhost:8080/create/todolist
Content-Type: application/json

{
  "todoListName": "Stationary"
}
```

---
### 2. Add Todo
```
POST http://localhost:8080/{todoListId}/create
Content-Type: application/json

{
    "taskName": "Buy Marker",
    "priority": false
}
```

---
### 3. Delete Todo
`DELETE http://localhost:8080/{todoListId}/delete/{todoId}`

---
### 4. Change the Priority of todo
```
POST http://localhost:8080/{todoListId}/todoPriority/{todoId}
Content-Type: application/json

{
    "priority": true
}
```

---
### 5. Change the Completion Status of todo

```
POST http://localhost:8080/{todoListId}/todoStatus/{todoId}
Content-Type: application/json

{
   "completed": true
}
```


---
### 6. View List of Todo for a particular TodoList

`GET http://localhost:8080/{todoListId}/getAllTodos/`



---
### 7. Delete TodoList
`DELETE http://localhost:8080/delete/{todoListId}`



---
### 8. Update TodoList Name

---
### 9. Update Todo Name
