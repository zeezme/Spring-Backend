package com.menelucas.backend.modules.user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PasswordChangeRequest {
    @NotBlank(message = "Old password is mandatory")
    private String oldPassword;
    @NotBlank(message = "New password is mandatory")
    private String newPassword;
    @NotBlank(message = "Confirm password is mandatory")
    private String confirmPassword;
}
