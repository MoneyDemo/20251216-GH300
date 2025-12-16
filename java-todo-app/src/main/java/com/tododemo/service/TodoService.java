package com.tododemo.service;

import com.tododemo.model.Todo;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

@Service
public class TodoService {
    private final List<Todo> todos = new ArrayList<>();
    private int nextId = 1;
    private final ReentrantLock lock = new ReentrantLock();

    /**
     * Get all todos sorted by creation date (newest first)
     */
    public List<Todo> getAll() {
        return todos.stream()
                .sorted(Comparator.comparing(Todo::getCreatedAt).reversed())
                .collect(Collectors.toList());
    }

    /**
     * Get a todo by its ID
     */
    public Optional<Todo> getById(int id) {
        return todos.stream()
                .filter(t -> t.getId() == id)
                .findFirst();
    }

    /**
     * Add a new todo
     */
    public void add(Todo todo) {
        lock.lock();
        try {
            todo.setId(nextId++);
            todo.setCreatedAt(LocalDateTime.now());
            todos.add(todo);
        } finally {
            lock.unlock();
        }
    }

    /**
     * Update an existing todo
     */
    public void update(Todo todo) {
        lock.lock();
        try {
            Optional<Todo> existing = getById(todo.getId());
            if (existing.isPresent()) {
                Todo existingTodo = existing.get();
                existingTodo.setTitle(todo.getTitle());
                existingTodo.setDescription(todo.getDescription());
                existingTodo.setCompleted(todo.isCompleted());
            }
        } finally {
            lock.unlock();
        }
    }

    /**
     * Delete a todo by its ID
     */
    public void delete(int id) {
        lock.lock();
        try {
            todos.removeIf(t -> t.getId() == id);
        } finally {
            lock.unlock();
        }
    }

    /**
     * Toggle the completion status of a todo
     */
    public void toggleComplete(int id) {
        lock.lock();
        try {
            Optional<Todo> todo = getById(id);
            todo.ifPresent(t -> t.setCompleted(!t.isCompleted()));
        } finally {
            lock.unlock();
        }
    }
}
