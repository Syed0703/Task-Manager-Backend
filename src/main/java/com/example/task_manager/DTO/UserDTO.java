package com.example.task_manager.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserDTO {
    private Long id;

    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 20, message = "Username must be 3–20 characters")
    private String username;
    // No Password Field To Hide Sensitive Data.

    // Default Constructor (needed for JSON deserialization)
    public UserDTO() {}

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


    //Setters
    public void setId(Long id){
        this.id = id;
    }

    public void setUsername(String username){
        this.username = username;
    }
}
