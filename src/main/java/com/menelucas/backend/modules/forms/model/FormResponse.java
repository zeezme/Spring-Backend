package com.menelucas.backend.modules.forms.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.menelucas.backend.modules.user.model.User;
import jakarta.persistence.*;

@Entity
@Table(name = "form_responses")
public class FormResponse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "form_item", nullable = false)
    private FormItem formItem;

    @Column(nullable = false)
    private String answer;

    @ManyToOne
    @JoinColumn(name = "\"user\"", nullable = false)
    @JsonBackReference
    private User user;

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setFormItem(FormItem formItem) {
        this.formItem = formItem;
    }

    public void setUser(User user) {
        this.user = user;
    }
}