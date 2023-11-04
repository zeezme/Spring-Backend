package com.menelucas.backend.modules.user;

import com.menelucas.backend.modules.user.dto.PasswordChangeRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final CustomUserDetailsService customUserDetailsService;


    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(@Valid @RequestBody PasswordChangeRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            String username = authentication.getName();

            User user = customUserDetailsService.getUserObjectByUsername(username);

            Integer userId = customUserDetailsService.loadUserIDByUsername(username);

            userService.changePassword(user,userId, request.getOldPassword(), request.getNewPassword());

            return ResponseEntity.ok().body("Password changed successfully.");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
