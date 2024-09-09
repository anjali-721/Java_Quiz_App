package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Find a user by email
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // Save a user
    public void save(User user) {
        userRepository.save(user);
    }
}
