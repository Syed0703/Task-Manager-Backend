package com.example.task_manager.Controller;

import com.example.task_manager.DTO.UserDTO;
import com.example.task_manager.Entity.Task;
import com.example.task_manager.Entity.User;
import com.example.task_manager.Mapper.DTOMapper;
import com.example.task_manager.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


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
    public ResponseEntity<User> createUser(@RequestBody User user){
        User savedUser = userService.saveUser(user);
        return ResponseEntity.status(201).body(savedUser);
    }


    // Get User by Username
    @GetMapping("/username/{username}")
    public ResponseEntity<User> getUser(@PathVariable String username){
        return userService.findByUsername(username)
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
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User updatedUser){
        return userService.updateUser(id, updatedUser)
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
    public ResponseEntity<List<Task>> getUserTasks(@PathVariable Long id) {
        return userService.findUserById(id)
                .map(user -> ResponseEntity.ok(user.getTasks()))
                .orElse(ResponseEntity.notFound().build());
    }
}
