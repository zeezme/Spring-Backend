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
    private FormItem formItem;

    @Column(nullable = false)
    private String answer;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}