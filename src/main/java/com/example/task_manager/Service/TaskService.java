package com.example.task_manager.Service;


import com.example.task_manager.Entity.Task;
import com.example.task_manager.Repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    // constructor injection
    public TaskService(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }


    // Save Task
    public Task saveTask(Task task){
        return taskRepository.save(task);
    }

    // Find All Tasks
    public List<Task> getAllTasks(){
        return taskRepository.findAll();
    }

    // Find Task By Id
    public Optional<Task> getTaskById(Long id){
        return taskRepository.findById(id);
    }

    // Update Task
    public Optional<Task> updateTask(Long id, Task updatedTask){
        return taskRepository.findById(id).map(existingTask -> {
            existingTask.setDescription(updatedTask.getDescription());
            existingTask.setCompleted(updatedTask.isCompleted());
            return taskRepository.save(existingTask);
        });
    }

    // Delete Task
    public void deleteTask(Long id){
        taskRepository.deleteById(id);
    }

    // Find Task By Status
    public List<Task> findByCompleted(boolean completed){
        return taskRepository.findByCompleted(completed);
    }
}
