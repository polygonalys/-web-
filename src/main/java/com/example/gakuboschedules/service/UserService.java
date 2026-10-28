package com.example.gakuboschedules.service;

import org.springframework.stereotype.Service;

import com.example.gakuboschedules.entity.User;
import com.example.gakuboschedules.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User checkUsername(String username) {
        return userRepository.findByUsername(username);
    }

    // public void createUser(User user) {
    //     userRepository.insert(user);
    // }
}
