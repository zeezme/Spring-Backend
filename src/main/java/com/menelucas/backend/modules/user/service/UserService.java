package com.menelucas.backend.modules.user.service;

import com.menelucas.backend.modules.user.model.User;
import com.menelucas.backend.modules.user.dao.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User changePassword(User user, String oldPassword, String newPassword) {

        String oldPasswordFromDB = userRepository.findById(user.getId().longValue()).get().getPassword();

        if (!passwordEncoder.matches(oldPassword, oldPasswordFromDB)) {
            throw new RuntimeException("Old password is incorrect");
        }

        user.setPassword(passwordEncoder.encode(newPassword));

        return userRepository.save(user);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User getUserById(Integer userId){
        return userRepository.findById(userId.longValue()).get();
    }

}