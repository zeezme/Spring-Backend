package com.menelucas.backend.modules.forms.dao;

import com.menelucas.backend.modules.auth.Role;
import com.menelucas.backend.modules.forms.model.Form;
import com.menelucas.backend.modules.forms.model.FormItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface FormItemRepository extends JpaRepository<FormItem, Integer> {
    List<FormItem> findAllByFormAndForm_Role(Form form, Role role);

    List<FormItem> findAllByForm(Form form);
}

