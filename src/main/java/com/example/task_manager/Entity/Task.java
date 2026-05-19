package com.example.task_manager.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private boolean completed;

    // Many tasks belong to one user
    @ManyToOne(fetch = FetchType.LAZY)  // lazy loading prevents unnecessary data fetch
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;

    // Default constructor (required by JPA)
    public Task(){}

    // Constructor without id (since id is auto-generated)
    public Task(String description, boolean completed) {
        this.description = description;
        this.completed = completed;
    }

    // Full constructor (optional, useful for testing)
    public Task(Long id, String description, boolean completed, User user) {
        this.id = id;
        this.description = description;
        this.completed = completed;
        this.user = user;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
