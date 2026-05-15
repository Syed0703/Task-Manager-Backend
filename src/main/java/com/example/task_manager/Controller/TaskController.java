package com.example.task_manager.Controller;


import com.example.task_manager.DTO.TaskDTO;
import com.example.task_manager.Entity.Task;
import com.example.task_manager.Entity.User;
import com.example.task_manager.Mapper.DTOMapper;
import com.example.task_manager.Service.TaskService;
import com.example.task_manager.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public ResponseEntity<TaskDTO> createTask(@PathVariable Long userId, @Valid @RequestBody TaskDTO taskDTO){
        Optional<User> user = userService.findUserById(userId);
        if(user.isPresent()){
            Task task = new Task();
            task.setDescription(taskDTO.getDescription());
            task.setCompleted(taskDTO.isCompleted());
            task.setUser(user.get());

            Task savedTask = taskService.saveTask(task);
            return ResponseEntity.status(201).body(DTOMapper.toTaskDTO(savedTask));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //Get All Tasks
    @GetMapping
    public ResponseEntity<List<TaskDTO>> getAllTasks(){
        List<TaskDTO> tasks = taskService.getAllTasks()
                .stream()
                .map(DTOMapper::toTaskDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(tasks);
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
    public ResponseEntity<TaskDTO> updateTask(@PathVariable Long id, @Valid @RequestBody TaskDTO updatedTaskDTO){
        return taskService.updateTask(id, updatedTaskDTO)
                .map(DTOMapper::toTaskDTO)
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
    public ResponseEntity<List<TaskDTO>> getTaskByStatus(@PathVariable boolean status){
        List<TaskDTO> tasks = taskService.findByCompleted(status)
                .stream()
                .map(DTOMapper::toTaskDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(tasks);
    }
}
