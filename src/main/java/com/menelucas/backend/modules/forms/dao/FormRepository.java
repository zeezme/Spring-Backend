package com.menelucas.backend.modules.forms.dao;

import com.menelucas.backend.modules.forms.model.Form;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface FormRepository extends JpaRepository<Form, Integer> {
}

