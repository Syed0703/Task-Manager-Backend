package com.example.task_manager.DTO;

import jakarta.validation.constraints.NotBlank;

public class TaskDTO {
    private Long id;

    @NotBlank(message = "Description cannot be empty")
    private String description;
    private boolean completed;
    private Long userId;  // Reference To Owner

    // Default Constructor (needed for JSON deserialization)
    public TaskDTO() {}

    // Constructor
    public TaskDTO(Long id, String description, boolean completed, Long userId){
        this.id = id;
        this.description = description;
        this.completed = completed;
        this.userId = userId;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public Long getUserId() {
        return userId;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
