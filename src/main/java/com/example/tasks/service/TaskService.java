package com.example.tasks.service;

import com.example.tasks.model.Task;
import com.example.tasks.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    public Task findById(Long id){
        return taskRepository.findById(id).orElseThrow();
    }

    public Task createTask(Task task){
        return taskRepository.save(task);
    }

    public Task updateTask(Long id, Task task){
        if (!taskRepository.existsById(id)) {
            throw new RuntimeException("Task not found with ID: " + id + ". Cannot perform update.");
        }

        return taskRepository.save(task);
    }

    public void deleteTask(Long id){
        if (!taskRepository.existsById(id)) {
            throw new RuntimeException("Task not found with ID: " + id + ". Cannot perform delete.");
        }

        taskRepository.deleteById(id);
    }
}
