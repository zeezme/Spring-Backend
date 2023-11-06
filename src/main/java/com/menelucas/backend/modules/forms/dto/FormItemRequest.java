package com.menelucas.backend.modules.forms.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FormItemRequest {
    @NotBlank(message = "Property formId is mandatory")
    private String formId;
    @NotBlank(message = "Property question is mandatory")
    private String question;
}

