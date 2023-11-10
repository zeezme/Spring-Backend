# Spring-Backend

API em desenvolvimento para uma aplicação de gestão de times esportivos, utilizando Spring Boot, Spring Security, Hibernate e PostgreSQL. O projeto inclui um sistema de autenticação que utiliza filtros de validação de token JWT.

## Índice

- [Autenticação](#autenticação)
- [Gestão de Usuários](#gestão-de-usuários)
- [Gestão de Formulários](#gestão-de-formulários)
- [Respostas dos Formulários](#respostas-dos-formulários)
- [Consulta de Formulários](#consulta-de-formulários)

## Autenticação

<details>
<summary>Teste da API</summary>

- **GET `/api`**
  - **Ação**: Teste
  - **Request**: Nenhum
  - **Retorno**: `API OK`

</details>

<details>
<summary>Registro de Usuário</summary>

- **POST `/api/auth/register`**
  - **Ação**: Registra um usuário
  - **Request**:
    ```json
    {
      "email": "string",
      "firstname": "string",
      "lastname": "string",
      "password": "string"
    }
    ```
  - **Retorno**: Padrão

</details>

<details>
<summary>Autenticação de Usuário</summary>

- **POST `/api/auth/authenticate`**
  - **Ação**: Autentica o usuário
  - **Request**:
    ```json
    {
      "email": "string",
      "password": "string"
    }
    ```
  - **Retorno**:
    ```json
    {
      "access_token": "string",
      "refresh_token": "string"
    }
    ```

</details>

## Gestão de Usuários

<details>
<summary>Alteração de Senha</summary>

- **POST `/api/users/change-password`**
  - **Ação**: Altera a senha
  - **Request**:
    ```json
    {
      "oldPassword": "string",
      "newPassword": "string"
    }
    ```
  - **Retorno**: Padrão

</details>

## Gestão de Formulários

<details>
<summary>Criação de Formulário</summary>

- **POST `/api/forms/create`**
  - **Ação**: Cria um formulário
  - **Request**:
    ```json
    {
      "title": "string"
    }
    ```
  - **Retorno**: Padrão

</details>

<details>
<summary>Adição de Item ao Formulário</summary>

- **POST `/api/forms/insert-item`**
  - **Ação**: Adiciona item ao formulário
  - **Request**:
    ```json
    {
      "formId": "string",
      "question": "string"
    }
    ```
  - **Retorno**: Padrão

</details>

<details>
<summary>Deleção de Item do Formulário</summary>

- **DELETE `/api/forms/delete-item/{item_id}`**
  - **Ação**: Deleta item do formulário
  - **Request**: Nenhum
  - **Retorno**: Padrão

</details>

<details>
<summary>Deleção de Formulário</summary>

- **DELETE `/api/forms/delete-form/{form_id}`**
  - **Ação**: Deleta o formulário
  - **Request**: Nenhum
  - **Retorno**: Padrão

</details>

## Respostas dos Formulários

<details>
<summary>Responder Pergunta do Formulário</summary>

- **POST `/api/form/answer-item`**
  - **Ação**: Responder pergunta do formulário
  - **Request**:
    ```json
    {
      "formItemId": "string",
      "answer": "string"
    }
    ```
  - **Retorno**: Padrão

</details>

<details>
<summary>Listar Respostas por Usuário e Formulário</summary>

- **GET `/api/forms/get-form-responses-by-user-and-form/{user_id}/{form_id}`**
  - **Ação**: Lista as perguntas respondidas por usuário e formulário
  - **Request**: Nenhum
  - **Retorno**:
    ```json
    [
      {
        "formId": integer,
        "title": "string",
        "question": "string",
        "answer": "string"
      }
    ]
    ```

</details>

## Consulta de Formulários

<details>
<summary>Listar Formulários</summary>

- **GET `/get-forms`**
  - **Ação**: Traz uma lista de todos os formulários acessíveis
  - **Request**: Nenhum
  - **Retorno**:
    ```json
    [
      {
        "id": integer,
        "title": "string",
        "userId": integer,
        "formItems": [],
        "role": "ROLE"
      }
    ]
    ```

</details>

<details>
<summary>Detalhes do Formulário Específico</summary>

- **GET `/get-forms/{form_id}`**
  - **Ação**: Traz um formulário específico acessível
  - **Request**: Nenhum
  - **Retorno**:
    ```json
    {
      "id": integer,
      "title": "string",
      "userId": integer,
      "formItems": [],
      "role": "ROLE"
    }
    ```

</details>

<details>
<summary>Listar Itens de um Formulário</summary>

- **GET `/get-form-items/{form_id}`**
  - **Ação**: Traz os itens de um formulário
  - **Request**: Nenhum
  - **Retorno**:
    ```json
    {
      "id": integer,
      "form": {
        "id": integer,
        "title": "string",
        "userId": integer,
        "formItems": [],
        "role": "ROLE"
      },
      "question": "string"
    }
    ```

</details>

<details>
<summary>Listar Usuários que Responderam um Formulário</summary>

- **GET `/get-who-answered/{form_id}`**
  - **Ação**: Traz uma lista de usuários que responderam o formulário
  - **Request**: Nenhum
  - **Retorno**:
    ```json
    [
      {
        "id": integer,
        "firstname": "string",
        "email": "string"
      }
    ]
    ```

</details>

**Nota:** Um retorno "PADRÃO" pode ser uma mensagem de sucesso, erro ou status HTTP 403.
