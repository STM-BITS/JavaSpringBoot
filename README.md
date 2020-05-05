# JavaSpringBoot
A Spring boot application for todo list

The application creates a todolist which contains various todos.

The todos can be set as priority and can be marked as completed. 

## Steps to execute:

### 1. Create Todo 
```
POST http://localhost:8080/create
Content-Type: application/json

{
  "taskName": "Go for Walk"
}
```

###### Response Structure
Request 1

```
POST http://localhost:8080/create
Content-Type: application/json

{
  "taskName": "Go for Walk"
}
```
##### Output Response Structure
```
{
  "uuid": "C9D8B2CCB48D4D2B9858EBE777C6257D",
  "taskName": "Go for Walk"
}
```

Request 2
```
POST http://localhost:8080/create
Content-Type: application/json

{
  "taskName": "Complete Assignment"
}
```
##### Output Response Structure
```
{
  "uuid": "BA008DE11A5843B3B2A3EBC2857CA907",
  "taskName": "Complete Assignment"
}

```
Request 3
```
POST http://localhost:8080/create
Content-Type: application/json

{
  "taskName": "Watch Series"
}
```
##### Output Response Structure

```
{
  "uuid": "54E3083AC5EB49AFB27DABCBF0CC0731",
  "taskName": "Watch Series"
}
```
---
### 2. Get List of All Todos 
``` GET http://localhost:8080/getAllTodos```

##### Response Structure
Request: 
``` GET http://localhost:8080/getAllTodos```

##### Output Response Structure
```
[
  {
    "uuid": "C9D8B2CCB48D4D2B9858EBE777C6257D",
    "taskName": "Go for Walk"
  },
  {
    "uuid": "BA008DE11A5843B3B2A3EBC2857CA907",
    "taskName": "Complete Assignment"
  },
  {
    "uuid": "54E3083AC5EB49AFB27DABCBF0CC0731",
    "taskName": "Watch Series"
  }
]
```
---
### 3. Delete Todo
``` DELETE http://localhost:8080/delete/{todoId}```

Request 1: 
``` DELETE http://localhost:8080/delete/54E3083AC5EB49AFB27DABCBF0CC0731```

##### Output Response Structure
```
Deleted
```

## Steps to view the H2 Console
- Run the application locally
- Go to <a href="http://localhost:8080/h2-console/" target="_blank">H2 Console</a>
- Enter the username as root
- Enter the password as stmShriReniSpa