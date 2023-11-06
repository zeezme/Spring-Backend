# Spring-Backend
  API em desenvolvimento para uma aplicação de gestão de times esportivos, o projeto está sendo desenvolvido utilizando Spring Boot, Spring Security, Hibernate e PostgreSQL, até o momento a aplicação conta com um sistema de autenticação que envolve filtros de validação de token JWT para autenticação e autorização de rota.

## Endpoints disponíveis

| Método | Rota                          | Ação                        | Request                                                   | Retorno                         |
|--------|-------------------------------|-----------------------------|-----------------------------------------------------------|---------------------------------|
| GET    | `/api`                        | Teste                       | NONE                                                      | `API OK`                        |
| POST   | `/api/auth/register`          | Registra um usuário         | `{ "email": "string", "firstname": "string", "lastname": "string", "password": “string" }` | PADRÃO |                                 |
| POST   | `/api/auth/authenticate`      | Autentica o Usuário         | `{ "email": "string", "password": “string" }`             | `{ "access_token": "string", "refresh_token": “string" }` |
| POST   | `/api/users/change-password`  | Altera a senha              | `{ “oldPassword” : "string", “newPassword" :  "string" }` | PADRÃO                          |
| POST   | `/api/forms/create`           | Cria um formulário          | `{ "title": “string" }`                                   | PADRÃO                          |
| POST   | `/api/forms/insert-item`      | Adiciona item ao formulário | `{ “formId":"string", "question": "string" }`             | PADRÃO                          |
| DELETE | `/api/forms/delete-item/{item_id}` | Deleta item do Formulário | NONE                                                      | PADRÃO                          |
| DELETE | `/api/forms/delete-form/{form_id}` | Deleta o formulário       | NONE                                                      | PADRÃO                          |

**Nota:** Um retorno "PADRÃO" pode ser uma mensagem de sucesso, erro ou 403


