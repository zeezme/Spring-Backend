package com.menelucas.backend.modules.forms.controller;

import com.menelucas.backend.modules.forms.dto.FormCreationRequest;
import com.menelucas.backend.modules.forms.dto.FormItemRequest;
import com.menelucas.backend.modules.forms.dto.FormResponseRequest;
import com.menelucas.backend.modules.forms.service.FormService;
import com.menelucas.backend.modules.shared.util.AuthenticationFacade;
import com.menelucas.backend.modules.user.service.CustomUserDetailsService;
import com.menelucas.backend.modules.user.model.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/forms")
@RequiredArgsConstructor
public class FormController {
    private final FormService formService;
    private final CustomUserDetailsService customUserDetailsService;

    private final AuthenticationFacade authenticationFacade;


    @PostMapping("/create")
    public ResponseEntity<?> createForm(@Valid @RequestBody FormCreationRequest request) {

        User user = authenticationFacade.getAuthenticatedUser();

        formService.createForm(request.getTitle(), user.getId());

        return ResponseEntity.ok().body("Form created successfully.");

    }

    @PostMapping("/insert-item")
    public ResponseEntity<?> insertItem(@Valid @RequestBody FormItemRequest request) {
        User user = authenticationFacade.getAuthenticatedUser();

        formService.createAndInsertItem(Integer.valueOf(request.getFormId()), request.getQuestion(), user);

        return ResponseEntity.ok().body("Item inserted successfully.");

    }

    @DeleteMapping("/delete-item/{id}")
    public ResponseEntity<?> deleteItem(@Valid @PathVariable("id") Integer formItemId) {
        User user = authenticationFacade.getAuthenticatedUser();

        formService.deleteFormItem(formItemId, user);

        return ResponseEntity.ok().body("Item deleted successfully.");
    }

    @DeleteMapping("/delete-form/{id}")
    public ResponseEntity<?> deleteForm(@Valid @PathVariable("id") Integer formId) {
        User user = authenticationFacade.getAuthenticatedUser();

        formService.deleteForm(formId, user);

        return ResponseEntity.ok().body("Form deleted successfully.");
    }

    @PostMapping("/answer-item")
    public ResponseEntity<?> answerItem(@Valid @RequestBody FormResponseRequest request) {
        User user = authenticationFacade.getAuthenticatedUser();

        formService.answerFormItem(Integer.valueOf(request.getFormItemId()), request.getAnswer(), user);

        return ResponseEntity.ok().body("Item answered successfully.");
    }

    @GetMapping("/get-form-responses-by-user-and-form/{userId}/{formId}")
    public ResponseEntity<?> getFormResponsesByUserAndForm(@PathVariable("userId") Integer userId, @PathVariable("formId") Integer formId) {
        User user = authenticationFacade.getAuthenticatedUser();

        return ResponseEntity.ok().body(formService.getFormResponseByUserAndForm(userId, formId, user));
    }
}
