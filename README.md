# JavaSpringBoot
A Spring boot application for todo list

The application creates a todolist which contains various todos.

The todos can be set as priority and can be marked as completed. 

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
`DELETE http://localhost:8080/{todoListId}/delete/`



---
### 8. Update TodoList Name
```
POST http://localhost:8080/{todoListId}/updateTodoListName
Content-Type: application/json

{
  "todoListName":"Stationary Item"
}
```

---
### 9. Update Todo Name

```
POST http://localhost:8080/{todoListId}/updateTodoName/{todoId}
Content-Type: application/json

{
  "taskName":"Buy Glue"
}
```

## Steps to view the H2 Console
- Run the application locally
- Go to <a href="http://localhost:8080/h2-console/" target="_blank">H2 Console</a>
- Enter the username as root
- Enter the password as stmShriReniSpa