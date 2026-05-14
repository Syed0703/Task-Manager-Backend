package com.example.task_manager.Controller;


import com.example.task_manager.DTO.TaskDTO;
import com.example.task_manager.Entity.Task;
import com.example.task_manager.Entity.User;
import com.example.task_manager.Mapper.DTOMapper;
import com.example.task_manager.Service.TaskService;
import com.example.task_manager.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;
    private final UserService userService;

    public TaskController(TaskService taskService, UserService userService){
        this.taskService = taskService;
        this.userService = userService;
    }

    // Create Task For a User
    @PostMapping("/user/{userId}")
    public ResponseEntity<Task> createTask(@PathVariable Long userId, @RequestBody Task task){
        return userService.findUserById(userId)
                .map(user -> {
                    task.setUser(user);
                    Task savedTask = taskService.saveTask(task);
                    return ResponseEntity.status(201).body(savedTask);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    //Get All Tasks
    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks(){
        return ResponseEntity.ok(taskService.getAllTasks());
    }


    // Get Task By Id
    @GetMapping("/{id}")
    public ResponseEntity<TaskDTO> getTaskById(@PathVariable Long id){
        return taskService.getTaskById(id)
                .map(task -> ResponseEntity.ok(DTOMapper.toTaskDTO(task)))
                .orElse(ResponseEntity.notFound().build());
    }


    // Update Task
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task updatedTask){
        return taskService.updateTask(id, updatedTask)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    // Delete Task
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        Optional<Task> task = taskService.getTaskById(id);

        if (task.isPresent()) {
            taskService.deleteTask(id);
            return ResponseEntity.noContent().build(); // 204
        } else {
            return ResponseEntity.notFound().build();  // 404
        }
    }

    // Get Task By Status
    @GetMapping("/completed/{status}")
    public ResponseEntity<List<Task>> getTaskByStatus(@PathVariable boolean status){
        List<Task> tasks = taskService.findByCompleted(status);
        return ResponseEntity.ok(tasks);
    }
}
