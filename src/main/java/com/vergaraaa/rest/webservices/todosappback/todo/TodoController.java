package com.vergaraaa.rest.webservices.todosappback.todo;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

// @RestController
public class TodoController {

    private TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("/users/{username}/todos")
    public List<Todo> getUserTodos(@PathVariable String username) {
        return todoService.findByUsername(username);
    }

    @GetMapping("/users/{username}/todos/{id}")
    public Todo getUserTodo(@PathVariable String username, @PathVariable int id) {
        return todoService.findById(id);
    }

    @PostMapping("/users/{username}/todos")
    public Todo createUserTodo(@PathVariable String username, @RequestBody Todo todo) {
        Todo createdTodo = todoService.addTodo(username, todo.getDescription(), todo.getTargetDate(), false);

        return createdTodo;
    }

    @PutMapping("/users/{username}/todos/{id}")
    public Todo updateUserTodo(@PathVariable String username, @PathVariable int id,
            @RequestBody Todo todo) {
        todoService.updateTodo(todo);

        return todo;
    }

    @DeleteMapping("/users/{username}/todos/{id}")
    public ResponseEntity<Void> deleteUserTodo(@PathVariable String username, @PathVariable int id) {
        todoService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
