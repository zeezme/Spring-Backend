package com.menelucas.backend.modules.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public User createUser(User user) {
        List<User> users = userRepository.findAllByEmail(user.getEmail());

        if (!users.isEmpty()) {
            throw new RuntimeException("Email already exists");
        }

        return userRepository.save(user);
    }

    public User changePassword(User user, Integer userId, String oldPassword, String newPassword) {
        String oldPasswordFromDB = userRepository.findById(userId.longValue()).get().getPassword();

        if (!passwordEncoder.matches(oldPassword, oldPasswordFromDB)) {

            throw new RuntimeException("Invalid old password");
        }

        user.setPassword(passwordEncoder.encode(newPassword));

        return userRepository.save(user);
    }

}