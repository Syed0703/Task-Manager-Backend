package com.example.task_manager.Controller;

import com.example.task_manager.Entity.User;
import com.example.task_manager.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/{username}")
    public ResponseEntity<User> getUser(@PathVariable String username){
        return userService.findByUsername(username)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
