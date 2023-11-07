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
public class FormResponseRequest {
    @NotBlank(message = "Property formItemId is mandatory")
    private String formItemId;
    @NotBlank(message = "Property answer is mandatory")
    private String answer;
}

