package com.menelucas.backend.modules.forms.service;

import com.menelucas.backend.exception.AlreadyExistsException;
import com.menelucas.backend.modules.auth.Role;
import com.menelucas.backend.modules.forms.dao.FormItemRepository;
import com.menelucas.backend.modules.forms.dao.FormRepository;
import com.menelucas.backend.modules.forms.dao.FormResponseRepository;
import com.menelucas.backend.modules.forms.dto.FormResponseByUserAndForm;
import com.menelucas.backend.modules.forms.model.Form;
import com.menelucas.backend.modules.forms.model.FormItem;
import com.menelucas.backend.modules.forms.model.FormResponse;
import com.menelucas.backend.modules.user.dao.UserRepository;
import com.menelucas.backend.modules.user.dto.MinimalUserResponse;
import com.menelucas.backend.modules.user.model.User;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class FormService {
    private final FormRepository formRepository;
    private final FormItemRepository formItemRepository;

    private final UserRepository userRepository;
    private final FormResponseRepository formResponseRepository;

    public Form createForm(String formTitle, Integer userId, String role) {
        Form form = new Form();
        Role roleEnum = Role.valueOf(role.toUpperCase());

        form.setUserId(userId);
        form.setTitle(formTitle);
        form.setRole(roleEnum);

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

        if (formResponseRepository.existsByFormItem_IdAndUser_Id(formItemId, user.getId())) {
            throw new AlreadyExistsException("You already answered this form item");
        }

        FormResponse formResponse = new FormResponse();

        formResponse.setAnswer(answer);
        formResponse.setFormItem(formItem);
        formResponse.setUser(user);

        formResponseRepository.save(formResponse);
    }

    public List<FormResponseByUserAndForm> getFormResponseByUserAndForm(Integer userId, Integer formId, User loggedUser) {
        User userFromRequest = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found with id " + userId));
        if (!formRepository.existsById(formId)) {
            throw new EntityNotFoundException("Form not found with id " + formId);
        }

        if (!userRepository.existsById(userFromRequest.getId())) {
            throw new EntityNotFoundException("User not found with id " + userId);
        }

        if (loggedUser.getId() != userId && !loggedUser.getRole().equals(Role.ADMIN)) {
            throw new EntityNotFoundException("You can only view your own answers or you need to be an admin");
        }
        return formResponseRepository.findFormResponsesByUserIdAndFormId(userFromRequest, formId);
    }

    public List<Form> getAllForms(User user) {
        if (user.getRole().equals(Role.ADMIN)) {
            List<Form> allForms = formRepository.findAll();
            if (allForms.isEmpty()) {
                throw new EntityNotFoundException("No forms found");
            }
            return allForms;
        }

        List<Form> formsByRole = formRepository.findAllByRole(user.getRole());
        if (formsByRole.isEmpty()) {
            throw new EntityNotFoundException("No forms found with your role");
        }

        return formsByRole;
    }

    public Form getFormById(Integer formId, User user) {
        if (user.getRole().equals(Role.ADMIN)) {
            Form form = formRepository.findById(formId).orElseThrow(() -> new EntityNotFoundException("Form not found with id " + formId));

            return form;
        }

        Form form = formRepository.findByIdAndRole(formId, user.getRole());

        if (form == null) {
            throw new EntityNotFoundException("Form not found with id or you have no permission to view. " + formId);
        }

        return form;
    }

    public List<FormItem> getFormItems(Integer formId, User user) {
        if (user.getRole().equals(Role.ADMIN)) {
            Form form = formRepository.findById(formId).orElseThrow(() -> new EntityNotFoundException("Form not found with id " + formId));

            return formItemRepository.findAllByForm(form);
        }

        Form form = formRepository.findByIdAndRole(formId, user.getRole());

        return formItemRepository.findAllByFormAndForm_Role(form, user.getRole());
    }

    public Set<MinimalUserResponse> getWhoAnswered(Integer formId) {
        Form form = formRepository.findById(formId)
                .orElseThrow(() -> new EntityNotFoundException("Form not found with id " + formId));

        Set<User> users = formResponseRepository.findDistinctUsersByFormItemForm(form);

        return users.stream()
                .map(user -> MinimalUserResponse.builder()
                        .id(user.getId())
                        .firstname(user.getFirstname())
                        .email(user.getEmail())
                        .build())
                .collect(Collectors.toSet());
    }

    public void deleteFormResponse(Integer formResponseId, User user) {
        FormResponse formResponse = formResponseRepository.findById(formResponseId)
                .orElseThrow(() -> new EntityNotFoundException("Form response not found with id " + formResponseId));

        if (formResponse.getUser().getId() != user.getId()) {
            throw new EntityNotFoundException("You can only delete your own form responses");
        }

        formResponseRepository.deleteById(formResponse.getId());
    }

    public void updateFormResponse(Integer formResponseId, String answer, User user) {
        FormResponse formResponse = formResponseRepository.findById(formResponseId)
                .orElseThrow(() -> new EntityNotFoundException("Form response not found with id " + formResponseId));

        if (formResponse.getUser().getId() != user.getId()) {
            throw new EntityNotFoundException("You can only update your own form responses");
        }

        formResponse.setAnswer(answer);

        formResponseRepository.save(formResponse);
    }


    
}
