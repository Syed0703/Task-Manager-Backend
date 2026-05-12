package com.example.task_manager.Repository;

import com.example.task_manager.Entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    // Custom query method to find tasks by completion status
    List<Task> findByCompleted(boolean completed);
}
