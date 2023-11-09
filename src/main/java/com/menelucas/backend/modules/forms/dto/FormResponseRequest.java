package com.menelucas.backend.modules.forms.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FormResponseRequest {
    @NotNull(message = "Form item id is mandatory")
    private Integer formItemId;
    @NotBlank(message = "Property answer is mandatory")
    private String answer;
}

