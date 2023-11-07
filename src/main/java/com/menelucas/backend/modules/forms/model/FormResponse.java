package com.menelucas.backend.modules.forms.model;

import com.menelucas.backend.modules.user.model.User;
import jakarta.persistence.*;

@Entity
@Table(name = "form_responses")
public class FormResponse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "form_item_id", nullable = false)
    private FormItem formItemId;

    @Column(nullable = false)
    private String answer;

    @Column(name = "user_id")
    private Integer userId;

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setFormItemId(FormItem formItemId) {
        this.formItemId = formItemId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}