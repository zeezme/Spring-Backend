package com.menelucas.backend.modules.forms.dto;

import com.menelucas.backend.modules.auth.Role;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FormCreationRequest {
    @NotBlank(message = "Property title is mandatory")
    private String title;
    @NotBlank(message = "Property role is mandatory")
    private String role;
}

