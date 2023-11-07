package com.menelucas.backend.modules.forms.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
public class FormResponseByUserAndForm {

    private Integer formId;
    private String title;
    private String question;
    private String answer;

    public FormResponseByUserAndForm(Integer formId, String title, String question, String answer) {
        this.formId = formId;
        this.title = title;
        this.question = question;
        this.answer = answer;
    }
}
