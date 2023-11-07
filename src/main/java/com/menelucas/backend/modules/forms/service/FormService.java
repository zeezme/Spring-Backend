package com.menelucas.backend.modules.forms.service;
import com.menelucas.backend.modules.auth.Role;
import com.menelucas.backend.modules.forms.dao.FormItemRepository;
import com.menelucas.backend.modules.forms.dao.FormRepository;
import com.menelucas.backend.modules.forms.dao.FormResponseRepository;
import com.menelucas.backend.modules.forms.dto.FormResponseByUserAndForm;
import com.menelucas.backend.modules.forms.model.Form;
import com.menelucas.backend.modules.forms.model.FormItem;
import com.menelucas.backend.modules.forms.model.FormResponse;
import com.menelucas.backend.modules.user.dao.UserRepository;
import com.menelucas.backend.modules.user.model.User;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class FormService {
    private final FormRepository formRepository;
    private final FormItemRepository formItemRepository;

    private final UserRepository userRepository;
    private final FormResponseRepository formResponseRepository;

    public Form createForm( String formTitle, Integer userId) {
        Form form = new Form();

        if (formTitle != null && userId != null) {
            form.setUserId(userId);
            form.setTitle(formTitle);
        }
        return formRepository.save(form);
    }

    public FormItem createAndInsertItem(Integer formId, String question, User user) {
        FormItem formItem = new FormItem();

        Form form = formRepository.findById(formId)
                .orElseThrow(() -> new EntityNotFoundException("Form not found with id " + formId));

        if (user.getId() != form.getUserId()) {
            throw new EntityNotFoundException("You can only add items to your own forms");
        }

        formItem.setQuestion(question);
        formItem.setForm(form);
        return formItemRepository.save(formItem);
    }
    public void deleteFormItem(Integer formItemId, User user) {
        FormItem formItem = formItemRepository.findById(formItemId)
                .orElseThrow(() -> new EntityNotFoundException("Form item not found with id " + formItemId));

        System.out.println(formItem.getForm().getUserId());

        if (user.getId() != formItem.getForm().getUserId()) {
            throw new EntityNotFoundException("You can only remove items from your own forms");
        }

        formItemRepository.delete(formItem);
    }

    public void deleteForm(Integer formId, User user) {
        Form form = formRepository.findById(formId)
                .orElseThrow(() -> new EntityNotFoundException("Form not found with id " + formId));

        if (user.getId() != form.getUserId()) {
            throw new EntityNotFoundException("You can only delete your own forms");
        }

        formRepository.delete(form);

    }

    public void answerFormItem(Integer formItemId, String answer, User user) {
        FormItem formItem = formItemRepository.findById(formItemId)
                .orElseThrow(() -> new EntityNotFoundException("Form item not found with id " + formItemId));

        FormResponse formResponse = new FormResponse();
        formResponse.setAnswer(answer);
        formResponse.setFormItemId(formItem);
        formResponse.setUserId(user.getId());

        formResponseRepository.save(formResponse);
    }

    public List<FormResponseByUserAndForm>  getFormResponseByUserAndForm(Integer userId, Integer formId, User loggedUser) {
        if (!formRepository.existsById(formId)) {
            throw new EntityNotFoundException("Form not found with id " + formId);
        }

        if (!userRepository.existsById(userId.longValue())) {
            throw new EntityNotFoundException("User not found with id " + userId);
        }

        if (loggedUser.getId() != userId && !loggedUser.getRole().equals(Role.ADMIN))  {
            throw new EntityNotFoundException("You can only view your own answers or you need to be an admin");
        }
        return formResponseRepository.findFormResponsesByUserIdAndFormId(userId, formId);
    }


}
