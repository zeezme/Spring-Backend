package com.menelucas.backend.modules.user.controller;

import com.menelucas.backend.modules.shared.util.AuthenticationFacade;
import com.menelucas.backend.modules.user.model.User;
import com.menelucas.backend.modules.user.service.UserService;
import com.menelucas.backend.modules.user.dto.PasswordChangeRequest;
import com.menelucas.backend.modules.user.service.CustomUserDetailsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private final AuthenticationFacade authenticationFacade;



    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(@Valid @RequestBody PasswordChangeRequest request) {
            User loggedUser = authenticationFacade.getAuthenticatedUser();

            userService.changePassword(loggedUser, request.getOldPassword(), request.getNewPassword());

            return ResponseEntity.ok().body("Password changed successfully.");

    }
}
