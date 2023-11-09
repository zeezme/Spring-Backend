package com.menelucas.backend.modules.forms.dao;

import com.menelucas.backend.modules.auth.Role;
import com.menelucas.backend.modules.forms.model.Form;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface FormRepository extends JpaRepository<Form, Integer> {
    List<Form> findAllByRole(Role role);

    Form findByIdAndRole(Integer id, Role role);
}

