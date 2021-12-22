package com.example.swagger.validator;

import com.example.swagger.exception.BadRequestException;
import com.example.swagger.exception.EntityNotFoundException;
import com.example.swagger.models.TodoRequest;
import com.example.swagger.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TodoValidator {

    private static final String TODO_DOES_NOT_EXIST = "Todo does not exist";

    private static final String TITLE_REQUEST = "Title is required";


    private final TodoRepository todoRepository;

    @Autowired
    public TodoValidator(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    private void validateTodoExist(String id) {
        if (!todoRepository.existsById(id))
            throw new EntityNotFoundException(TODO_DOES_NOT_EXIST);
    }

    public void validateAddTodo(TodoRequest request) {
        checkRequiredField(request.getTitle(), TITLE_REQUEST);
    }

    public void validateUpdateTodo(String todoId, TodoRequest request) {
        validateTodoExist(todoId);
        checkRequiredField(request.getTitle(), TITLE_REQUEST);
    }

    private void checkRequiredField(String value, String errorMsg) {
        if (value == null || value.trim().isEmpty()) {
            throw new BadRequestException(errorMsg);
        }
    }
}
