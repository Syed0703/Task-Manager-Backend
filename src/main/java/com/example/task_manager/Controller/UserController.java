package com.example.task_manager.Controller;

import com.example.task_manager.DTO.TaskDTO;
import com.example.task_manager.DTO.UserDTO;
import com.example.task_manager.Entity.Task;
import com.example.task_manager.Entity.User;
import com.example.task_manager.Mapper.DTOMapper;
import com.example.task_manager.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    // constructor injection
    public UserController(UserService userService){
        this.userService = userService;
    }


    // Create User
    @PostMapping
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO){
        User user = new User();
        user.setUserName(userDTO.getUsername());
        User savedUser = userService.saveUser(user);
        return ResponseEntity.status(201).body(DTOMapper.toUserDTO(savedUser));
    }


    // Get User by Username
    @GetMapping("/username/{username}")
    public ResponseEntity<UserDTO> getUser(@PathVariable String username){
        return userService.findByUsername(username)
                .map(DTOMapper::toUserDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Get User By Id
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id){
        return userService.findUserById(id)
                .map(user -> ResponseEntity.ok(DTOMapper.toUserDTO(user)))
                .orElse(ResponseEntity.notFound().build());
    }


    // Update User
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @Valid @RequestBody UserDTO updatedUserDTO){
        return userService.updateUser(id, updatedUserDTO)
                .map(DTOMapper::toUserDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    // Delete User
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        Optional<User> user = userService.findUserById(id);
        if(user.isPresent()){
            userService.deleteUser(id);
            return ResponseEntity.noContent().build();
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }


    // Get All Tasks For a USer
    @GetMapping("/{id}/tasks")
    public ResponseEntity<List<TaskDTO>> getUserTasks(@PathVariable Long id) {
        return userService.findUserById(id)
                .map(user -> {
                    List<TaskDTO> taskDTOS = user.getTasks()
                            .stream()
                            .map(DTOMapper::toTaskDTO)
                            .collect(Collectors.toList());
                    return ResponseEntity.ok(taskDTOS);
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
