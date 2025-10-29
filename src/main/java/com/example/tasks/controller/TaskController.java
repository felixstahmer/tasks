package com.example.tasks.controller;

import com.example.tasks.model.Task;
import com.example.tasks.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/tasks")
    public List<Task> getAllTasks() { return taskService.findAll(); }

    @GetMapping("/tasks/{id}")
    public Task getTaskById(@PathVariable long id) { return taskService.findById(id); }

    @DeleteMapping("/tasks/{id}")
    public void deleteTaskById(@PathVariable long id) { taskService.deleteTask(id); }

    @PostMapping("/tasks")
    public Task createTask(@Valid @RequestBody Task task) { return taskService.createTask(task); }

    @PutMapping("/tasks/{id}")
    public Task updateTask(@PathVariable long id, @Valid @RequestBody Task task) { return taskService.updateTask(id, task); }
}
