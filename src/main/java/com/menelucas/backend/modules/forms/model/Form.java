package com.menelucas.backend.modules.forms.model;

import com.menelucas.backend.modules.auth.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "forms")
public class Form {
    @Id
    @GeneratedValue
    private Integer id;

    private String title;

    @Column(name = "user_id")
    private Integer userId;

    @OneToMany(mappedBy = "form", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<FormItem> formItems;

    @Enumerated(EnumType.STRING)
    private Role role;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<FormItem> getFormItems() {
        return formItems;
    }

    public void setFormItems(Set<FormItem> formItems) {
        this.formItems = formItems;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void removeFormItem(FormItem formItem) {
        this.formItems.remove(formItem);
        formItem.setForm(null);
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Role getRole() {
        return role;
    }
}
