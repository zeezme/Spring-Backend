package com.menelucas.backend.modules.forms.dao;

import com.menelucas.backend.modules.forms.model.Form;
import com.menelucas.backend.modules.forms.model.FormItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FormItemRepository extends JpaRepository<FormItem, Integer> {
}

