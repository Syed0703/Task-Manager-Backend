package com.example.task_manager.Service;


import com.example.task_manager.Entity.User;
import com.example.task_manager.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    // constructor injection
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    // Create or Save User
    public User saveUser(User user){
        return userRepository.save(user);
    }

    // Find User by Username
    public Optional<User> findByUsername(String username){
        return userRepository.findByUsername(username);
    }

    // Find User By Id
    public Optional<User> findUserById(Long id){
        return userRepository.findById(id);
    }

    // Update User
    public Optional<User> updateUser(Long id, User updatedUser){
        return userRepository.findById(id).map(existingUser -> {
            existingUser.setUserName(updatedUser.getUserName());
            existingUser.setPassword(updatedUser.getPassword());
            return userRepository.save(existingUser);
        });
    }

    // Delete User
    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }
}
