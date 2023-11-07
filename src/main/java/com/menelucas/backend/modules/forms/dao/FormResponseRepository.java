package com.menelucas.backend.modules.forms.dao;

import com.menelucas.backend.modules.forms.dto.FormResponseByUserAndForm;
import com.menelucas.backend.modules.forms.model.Form;
import com.menelucas.backend.modules.forms.model.FormResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface FormResponseRepository extends JpaRepository<FormResponse, Integer> {
    @Query("SELECT new com.menelucas.backend.modules.forms.dto.FormResponseByUserAndForm(f.id, f.title, fi.question, fr.answer) " +
            "FROM FormResponse fr " +
            "INNER JOIN fr.formItemId fi " +
            "INNER JOIN fi.form f " +
            "WHERE fr.userId = :userId AND f.id = :formId")

    List<FormResponseByUserAndForm> findFormResponsesByUserIdAndFormId(@Param("userId") Integer userId, @Param("formId") Integer formId);

}

