package com.example.task_manager.DTO;

public class TaskDTO {
    private Long id;
    private String description;
    private boolean completed;
    private Long userId;  // Reference To Owner


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
}
