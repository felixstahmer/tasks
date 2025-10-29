package com.example.tasks.service;

import com.example.tasks.exception.InvalidInputException;
import com.example.tasks.exception.ResourceNotFoundException;
import com.example.tasks.model.Task;
import com.example.tasks.model.TaskStatus;
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
        return taskRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found"));
    }

    public Task createTask(Task task){
        return taskRepository.save(task);
    }

    public Task updateTask(Long id, Task task){
        Task taskToUpdate = taskRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task to update not found"));

        if(taskToUpdate.getStatus() == TaskStatus.DONE && (task.getStatus() == TaskStatus.OPEN || task.getStatus() == TaskStatus.IN_PROGRESS)){
            throw new InvalidInputException("Cannot revert back from status DONE.");
        }

        return taskRepository.save(task);
    }

    public void deleteTask(Long id){
        if (!taskRepository.existsById(id)) {
            throw new ResourceNotFoundException("Task not found with ID: " + id + ". Cannot perform delete.");
        }

        taskRepository.deleteById(id);
    }
}
