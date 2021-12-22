package com.example.swagger.mapper;

import com.example.swagger.entity.TodoEntity;
import com.example.swagger.models.Todo;
import com.example.swagger.models.TodoRequest;
import com.example.swagger.models.Todos;
import com.example.swagger.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class TodoMapper {

    private final TodoRepository todoRepository;

    @Autowired
    public TodoMapper(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public TodoEntity mapTodoEntityFromTodoRequest(TodoRequest from) {

        TodoEntity to = new TodoEntity();

        to.setId(UUID.randomUUID().toString());
        to.setTitle(from.getTitle());
        to.setSelected(from.isSelected());

        return to;
    }

    public TodoEntity mapTodoEntityFromTodoRequest(String todoId, TodoRequest from) {

        TodoEntity to = todoRepository.getOne(todoId);

        to.setTitle(from.getTitle());
        to.setSelected(from.isSelected());

        return to;
    }

    public Todo mapTodoFromTodoEntity(TodoEntity from) {

        Todo to = new Todo();

        to.setId(from.getId());
        to.setTitle(from.getTitle());
        to.setSelected(from.isSelected());

        return to;
    }

    public Todos mapTodosFromTodoEntity(List<TodoEntity> from) {

        return from.stream()
                .map(this::mapTodoFromTodoEntity)
                .collect(Collectors.toCollection(Todos::new));
    }


}
