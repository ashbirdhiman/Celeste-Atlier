package com.celesteatelier.service;

import com.celesteatelier.exceptions.ResourceNotFoundException;
import com.celesteatelier.model.User;
import com.celesteatelier.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private  UserRepo userRepo;

    public User registerUser(User user) {
        return userRepo.save(user);
    }

    public boolean authenticate(String email, String password) {
        User user = userRepo.findByEmail(email);
        return user != null && user.getPassword().equals(password);
    }

    public User getUserById(Integer userId) {
        return userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + userId));
    }

    public User updateUser(Integer userId, User updatedUser) {
        User existing = getUserById(userId);
        existing.setName(updatedUser.getName());
        existing.setEmail(updatedUser.getEmail());
        existing.setPassword(updatedUser.getPassword());
        existing.setRole(updatedUser.getRole());
        return userRepo.save(existing);
    }

    public void deleteUser(Integer userId) {
        userRepo.deleteById(userId);
    }
}
