# Spring-Backend
  API em desenvolvimento para uma aplicação de gestão de times esportivos, o projeto está sendo desenvolvido utilizando Spring Boot, Spring Security, Hibernate e PostgreSQL, até o momento a aplicação conta com um sistema de autenticação que envolve filtros de validação de token JWT para autenticação e autorização de rota.

## Endpoints disponíveis

| Método  | Rota                                                                | Ação                                                         | Request                                                                               | Retorno                                                                               |
|---------|----------------------------------------------------------------------|--------------------------------------------------------------|---------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------|
| GET     | `/api`                                                              | Teste                                                        | NONE                                                                                  | `API OK`                                                                             |
| POST    | `/api/auth/register`                                                | Registra um usuário                                          | `{"email": "string", "firstname": "string", "lastname": "string", "password": "string"}` | PADRÃO                                                                               |
| POST    | `/api/auth/authenticate`                                            | Autentica o Usuário                                          | `{"email": "string", "password": "string"}`                                           | `{"access_token": "string", "refresh_token": "string"}`                             |
| POST    | `/api/users/change-password`                                        | Altera a senha                                               | `{"oldPassword": "string", "newPassword": "string"}`                                  | PADRÃO                                                                               |
| POST    | `/api/forms/create`                                                 | Cria um formulário                                           | `{"title": "string"}`                                                                 | PADRÃO                                                                               |
| POST    | `/api/forms/insert-item`                                            | Adiciona item ao formulário                                  | `{"formId": "string", "question": "string"}`                                          | PADRÃO                                                                               |
| DELETE  | `/api/forms/delete-item/{item_id}`                                  | Deleta item do Formulário                                    | NONE                                                                                  | PADRÃO                                                                               |
| DELETE  | `/api/forms/delete-form/{form_id}`                                  | Deleta o formulário                                          | NONE                                                                                  | PADRÃO                                                                               |
| GET     | `/api/forms/get-form-responses-by-user-and-form/{user_id}/{form_id}` | Lista as perguntas respondidas por usuário e por formulário  | NONE                                                                                  | `[{ "formId": integer, "title": "string", "question": "string", "answer": "string" }]` |
| POST     | `/api/form/answer-item` | Responder pergunta do formulário | `{ "formItemId":"string", "answer": "string"}` | PADRÃO  |
| GET     | `/get-forms` | Traz uma lista de todos os formulários que você tem acesso| NONE | `[{"id": integer, "title": "string", "userId": integer, "formItems": array, "role": "ROLE"}]`  |
| GET     | `/get-forms/{form_id}` | Traz um formulário específico que você tem acesso | NONE | `{"id": integer, "title": "string", "userId": integer, "formItems": array, "role": "ROLE"}`  |
| GET     | `/get-form-items/{form_id}"` | Traz os items de um formulário | NONE | `{ "id": integer, "form": { "id": integer, "title": "string", "userId": integer, "formItems": [array], "role": "ROLE" }, "question": "string"` },  |
| GET     | `/get-who-answered/{form_id}` | Traz uma lista de usuários que responderam o formulário | NONE | `[{ "id": integer, "firstname": "string", "email": "string" }]`  |



**Nota:** Um retorno "PADRÃO" pode ser uma mensagem de sucesso, erro ou 403


