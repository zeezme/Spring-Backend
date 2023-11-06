package com.menelucas.backend.modules.forms.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "form_items")
public class FormItem {
    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "form")
    private Form form;

    private String question;

    public void setForm(Form form) {
        this.form = form;
    }
    public void setQuestion(String question) {
        this.question = question;
    }
}
