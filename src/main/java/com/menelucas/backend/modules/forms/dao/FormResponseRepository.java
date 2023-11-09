package com.menelucas.backend.modules.forms.dao;

import com.menelucas.backend.modules.forms.dto.FormResponseByUserAndForm;
import com.menelucas.backend.modules.forms.model.Form;
import com.menelucas.backend.modules.forms.model.FormResponse;
import com.menelucas.backend.modules.user.dto.MinimalUserResponse;
import com.menelucas.backend.modules.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;


@Repository
public interface FormResponseRepository extends JpaRepository<FormResponse, Integer> {

    boolean existsByFormItem_IdAndUser_Id(Integer formItemId, Integer userId);
    @Query("SELECT new com.menelucas.backend.modules.forms.dto.FormResponseByUserAndForm(f.id, f.title, fi.question, fr.answer) " +
            "FROM FormResponse fr " +
            "INNER JOIN fr.formItem fi " +
            "INNER JOIN fi.form f " +
            "WHERE fr.user = :user AND f.id = :formId")

    List<FormResponseByUserAndForm> findFormResponsesByUserIdAndFormId(@Param("user") User user, @Param("formId") Integer formId);

    @Query("SELECT DISTINCT u FROM FormResponse fr JOIN fr.user u WHERE fr.formItem.form = :form")
    Set<User> findDistinctUsersByFormItemForm(@Param("form") Form form);



}

