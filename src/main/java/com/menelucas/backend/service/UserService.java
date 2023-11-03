package com.menelucas.backend.service;

import com.menelucas.backend.model.User;
import com.menelucas.backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        List<User> users = userRepository.findAllByEmail(user.getEmail());

        if (!users.isEmpty()) {
            throw new RuntimeException("Email already exists");
        }



        return userRepository.save(user);
    }

}