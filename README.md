# Auth Service

Microserviço de autenticação desenvolvido com **Java** e **Spring Boot**, responsável pelo registro de usuários, autenticação e geração de **JSON Web Tokens (JWT)** para acesso seguro aos demais serviços da arquitetura.

Este serviço faz parte de uma arquitetura de **microserviços**, utilizando **Service Discovery** e autenticação stateless.

---

## Arquitetura

O Auth Service é responsável por:

* Registro de novos usuários
* Autenticação de usuários
* Geração de token JWT
* Validação de credenciais
* Persistência de usuários
* Integração com Service Discovery

Arquitetura simplificada:

```
Client
   │
   ▼
API Gateway
   │
   ▼
Auth Service
   │
   ▼
Database
```

---

## Tecnologias utilizadas (atualmente)

* Java 17
* Spring Boot
* Spring Security
* Spring Data JPA
* JWT (JSON Web Token)
* H2 Database
* Lombok
* Maven
* Service Discovery (Eureka)

---

## Estrutura do Projeto

```
src/main/java/com/luizcarmo/auth

config
 └ SecurityConfig

controller
 └ AuthController

dto
 ├ request
 │   ├ LoginRequest
 │   └ RegisterRequest
 │
 └ response
     └ AuthResponse

entity
 └ User

repository
 └ UserRepository

service
 └ AuthService

security
 └ JwtService

exception
 └ GlobalExceptionHandler
```

---

## Funcionalidades

### Registro de usuário

Cria um novo usuário no sistema.

```
POST /auth/register
```

Body:

```json
{
  "email": "user@email.com",
  "password": "123456"
}
```

Resposta:

```json
{
  "token": "jwt-token"
}
```

---

### Login

Autentica um usuário existente e gera um token JWT.

```
POST /auth/login
```

Body:

```json
{
  "email": "user@email.com",
  "password": "123456"
}
```

Resposta:

```json
{
  "token": "jwt-token"
}
```

---

## Segurança

O sistema utiliza:

* **BCrypt** para hash de senhas
* **JWT** para autenticação stateless
* **Spring Security** para controle de acesso

Fluxo de autenticação:

```
User Login
   │
   ▼
Auth Service
   │
   ▼
JWT Token gerado
   │
   ▼
Token enviado no header Authorization
```

---

## Validação de dados

As requisições são validadas utilizando **Jakarta Validation**.

Exemplo:

* `@Email`
* `@NotBlank`

Se os dados forem inválidos, a API retorna:

```
400 Bad Request
```

---

## Banco de Dados

Durante o desenvolvimento é utilizado **H2 Database em memória**.

Console disponível em:

```
http://localhost:8081/h2-console
```

Configurações padrão:

```
JDBC URL: jdbc:h2:mem:testdb
User: sa
Password: (vazio)
```

---

## Service Discovery

O serviço se registra automaticamente no **Eureka Server**, permitindo que outros serviços descubram o Auth Service dinamicamente.

Exemplo de configuração:

```
spring.application.name=auth-service
eureka.client.service-url.defaultZone=http://localhost:8761/eureka
```

---

## Como executar o projeto

### Pré-requisitos

* Java 17
* Maven

### Executar

```
mvn spring-boot:run
```

Ou executar a classe principal da aplicação.

O serviço iniciará na porta:

```
http://localhost:8081
```

---

## Próximos passos da arquitetura

* API Gateway
* Config Server
* Outros microserviços (Tasks, Notifications)
* Containerização com Docker
* Observabilidade (Prometheus + Grafana)

---

## Autor

Luiz Nicolau Pereira do Carmo

Estudante de Engenharia de Software focado em **desenvolvimento backend com Java e arquitetura de microserviços**.
