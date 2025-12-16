package com.tododemo.controller;

import com.tododemo.model.Todo;
import com.tododemo.service.TodoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/")
public class TodoController {

    private final TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    /**
     * Display all todos
     */
    @GetMapping
    public String index(Model model) {
        var todos = todoService.getAll();
        model.addAttribute("todos", todos);
        model.addAttribute("totalCount", todos.size());
        model.addAttribute("completedCount", todos.stream().filter(Todo::isCompleted).count());
        model.addAttribute("pendingCount", todos.stream().filter(t -> !t.isCompleted()).count());
        return "index";
    }

    /**
     * Show create form
     */
    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("todo", new Todo());
        return "create";
    }

    /**
     * Handle create form submission
     */
    @PostMapping("/create")
    public String createPost(@Valid @ModelAttribute("todo") Todo todo, 
                            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "create";
        }
        todoService.add(todo);
        return "redirect:/";
    }

    /**
     * Show edit form
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable int id, Model model) {
        Optional<Todo> todo = todoService.getById(id);
        if (todo.isEmpty()) {
            return "redirect:/";
        }
        model.addAttribute("todo", todo.get());
        return "edit";
    }

    /**
     * Handle edit form submission
     */
    @PostMapping("/edit/{id}")
    public String editPost(@PathVariable int id, 
                          @Valid @ModelAttribute("todo") Todo todo,
                          BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "edit";
        }
        if (id != todo.getId()) {
            return "redirect:/";
        }
        todoService.update(todo);
        return "redirect:/";
    }

    /**
     * Handle delete action
     */
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        todoService.delete(id);
        return "redirect:/";
    }

    /**
     * Handle toggle completion action
     */
    @PostMapping("/toggle/{id}")
    public String toggle(@PathVariable int id) {
        todoService.toggleComplete(id);
        return "redirect:/";
    }
}
