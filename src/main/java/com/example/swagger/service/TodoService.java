package com.example.swagger.service;

import com.example.swagger.entity.TodoEntity;
import com.example.swagger.mapper.TodoMapper;
import com.example.swagger.models.Todo;
import com.example.swagger.models.TodoRequest;
import com.example.swagger.models.Todos;
import com.example.swagger.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TodoService {

    private final TodoMapper todoMapper;

    private final TodoRepository todoRepository;

    @Autowired
    public TodoService(TodoMapper todoMapper, TodoRepository todoRepository) {
        this.todoMapper = todoMapper;
        this.todoRepository = todoRepository;
    }

    public Todos getAllTodos() {

        Todos todos = todoMapper.mapTodosFromTodoEntity(todoRepository.findAll());

        return todos;
    }

    public Todo getTodoById(String todoId) {

        Todo todo = todoMapper.mapTodoFromTodoEntity(todoRepository.getOne(todoId));

        return todo;
    }

    public Todo addTodo(TodoRequest request) {

        TodoEntity todo = todoMapper.mapTodoEntityFromTodoRequest(request);
        
        TodoEntity saved = todoRepository.save(todo);
        
        return todoMapper.mapTodoFromTodoEntity(saved);
    }

    public Todo updateTodo(String todoId, TodoRequest request) {

        TodoEntity todo = todoMapper.mapTodoEntityFromTodoRequest(todoId, request);

        TodoEntity saved = todoRepository.save(todo);

        return todoMapper.mapTodoFromTodoEntity(saved);
    }
    
    public void deleteTodo(String todoId) {
        
        TodoEntity todo = todoRepository.getOne(todoId);

        todoRepository.delete(todo);
    }
}
