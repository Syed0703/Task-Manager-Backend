package com.example.task_manager.Mapper;

import com.example.task_manager.DTO.TaskDTO;
import com.example.task_manager.DTO.UserDTO;
import com.example.task_manager.Entity.Task;
import com.example.task_manager.Entity.User;

public class DTOMapper {

    public static UserDTO toUserDTO(User user) {
        return new UserDTO(user.getId(), user.getUserName());
    }

    public static TaskDTO toTaskDTO(Task task) {
        return new TaskDTO(task.getId(), task.getDescription(), task.isCompleted(), task.getUser().getId());
    }
}
