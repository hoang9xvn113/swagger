package com.example.swagger.api;

import com.example.swagger.models.Todo;
import com.example.swagger.models.TodoRequest;
import com.example.swagger.models.Todos;
import com.example.swagger.service.TodoService;
import com.example.swagger.validator.TodoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/speedhome/backend/v1")
public class TodoController implements TodosApi{

    private final TodoService todoService;

    private final TodoValidator todoValidator;

    @Autowired
    public TodoController(TodoService todoService, TodoValidator todoValidator) {
        this.todoService = todoService;
        this.todoValidator = todoValidator;
    }

    @Override
    public ResponseEntity<Todo> addTodo(TodoRequest body) {

        todoValidator.validateAddTodo(body);

        Todo todo = todoService.addTodo(body);

        return new ResponseEntity<>(todo, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleteTodo(String todoId) {

        todoService.deleteTodo(todoId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<Todos> getAllTodos() {

        Todos todos = todoService.getAllTodos();

        System.out.println(todos);

        return new ResponseEntity<>(todos, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Todo> getTodoById(String todoId) {

        Todo todo = todoService.getTodoById(todoId);

        return new ResponseEntity<>(todo, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Todo> updateTodo(TodoRequest body, String todoId) {

        todoValidator.validateUpdateTodo(todoId, body);

        Todo todo = todoService.updateTodo(todoId, body);

        return new ResponseEntity<>(todo, HttpStatus.OK);
    }
}
