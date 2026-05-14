package com.example.task_manager.DTO;

public class UserDTO {
    private Long id;
    private String username;
    // No Password Field To Hide Sensitive Data.

    // Constructor
    public UserDTO(Long id, String username){
        this.id = id;
        this.username = username;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }
}
