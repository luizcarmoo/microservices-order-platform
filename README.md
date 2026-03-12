# Microservices Order Platform

[![Java](https://img.shields.io/badge/Java-17-red)]()
[![Spring Boot](https://img.shields.io/badge/SpringBoot-3.x-brightgreen)]()
[![Spring Cloud](https://img.shields.io/badge/SpringCloud-2023-blue)]()
[![JWT](https://img.shields.io/badge/Auth-JWT-orange)]()
[![Maven](https://img.shields.io/badge/Build-Maven-blue)]()
[![Architecture](https://img.shields.io/badge/Architecture-Microservices-purple)]()

Plataforma backend baseada em **arquitetura de microserviços**, construída com **Java + Spring**, utilizando **API Gateway**, **Service Discovery** e **autenticação JWT**.

Este projeto foi desenvolvido para demonstrar **boas práticas utilizadas em arquiteturas modernas de backend**, incluindo:

- autenticação **stateless**
- gateway centralizado
- descoberta dinâmica de serviços
- separação clara de responsabilidades
- escalabilidade horizontal

---

# Arquitetura do Sistema

Arquitetura baseada em **Spring Cloud Microservices**.

            ┌──────────────┐
            │    Client    │
            └──────┬───────┘
                   │
                   ▼
            ┌──────────────┐
            │  API Gateway │
            └──────┬───────┘
                   │
                   ▼
            ┌──────────────┐
            │  Eureka      │
            │  Discovery   │
            └──────┬───────┘
                   │
      ┌────────────┴────────────┐
      ▼                         ▼
    ┌──────────────┐ ┌──────────────┐
    │ Auth Service │ │ Future       │
    │ Services     │ | Orders       │
    │ JWT + Users  │ │ Notifications│
    └──────────────┘ └──────────────┘

    
---

# Componentes da Arquitetura

## API Gateway

Responsável por:

- centralizar o acesso à API
- roteamento de requisições
- validação de JWT
- comunicação com Service Discovery

Porta padrão: `http://localhost:8082`
                       

Exemplo de rota: `/auth/** → AUTH-SERVICE`

Com Service Discovery: `lb://AUTH-SERVICE`

---

# Service Discovery

O projeto utiliza **Service Discovery** com **Eureka Server**.

Responsabilidades:

- registrar serviços
- permitir descoberta dinâmica
- facilitar escalabilidade

Dashboard: `http://localhost:8761`

Serviços registrados: `AUTH-SERVICE` & `API-GATEWAY`


---

# Auth Service

Microserviço responsável por autenticação e gerenciamento de usuários.

Funcionalidades:

- registro de usuários
- autenticação
- geração de JWT
- validação de credenciais
- persistência de usuários

Porta padrão: `http://localhost:8081`

---

# Estrutura do Auth Service

src/main/java/com/luizcarmo/auth

      config
      └ SecurityConfig
      
      controller
      └ AuthController
      
      dto
      ├ request
      │ ├ LoginRequest
      │ └ RegisterRequest
      │
      └ response
        └ AuthResponse
      
      entity
      └ User
      
      exception
      ├ AuthException
      └ GlobalExceptionHandler
      
      repository
      └ UserRepository

       security
      └ JwtService
      
      service
      └ AuthService
      
---

# Endpoints da API

### Registro de usuário

POST:

      /auth/register


Request:

JSON:

      {
        "email": "user@email.com",
        "password": "123456"
      }


Resposta:

JSON:

      {
        "token": "jwt-token"
      }


Login:

JSON:

      {
        "email": "user@email.com",
        "password": "123456"
      }


Resposta:

JSON:

      {
        "token": "jwt-token"
      }


---

# Segurança

### O sistema utiliza:

BCrypt

Hash seguro para armazenamento de senhas.

JWT (JSON Web Token)

Autenticação stateless.

Fluxo de autenticação:

      User Login
         │
         ▼
      Auth Service
         │
         ▼
      JWT Token
         │
         ▼
      Authorization: Bearer TOKEN

---

# Banco de Dados

### Durante desenvolvimento é utilizado:

H2 Database (em memória)

Console disponível em: 

      http://localhost:8081/h2-console

Configuração:

      JDBC URL: jdbc:h2:mem:testdb
      User: sa
      Password: (vazio)

---

# Tecnologias Utilizadas

### Backend

- Java 17
- Spring Boot
- Spring Security
- Spring Data JPA
- Spring Cloud Gateway
- Spring Cloud Netflix Eureka

### Segurança

- JWT
- BCrypt

### Ferramentas

- Maven
- Lombok
- H2 Database

---

# Como executar o projeto
## Pré-requisitos

- Java 17
- Maven

#### 1 - Executar o Service Discovery

      cd eurekaserver
      mvn spring-boot:run

Servidor disponível em:

      http://localhost:8761

#### 2 - Executar o Auth Service

      cd authservice
      mvn spring-boot:run

Disponível em:

      http://localhost:8081

#### 3 - Executar o API Gateway

      cd apigateway
      mvn spring-boot:run

Disponível em:

      http://localhost:8082

---

# Testando via Gateway

Login:

POST:

      http://localhost:8082/auth/login

Após autenticar: 

`Authorization: Bearer TOKEN`

---

# Roadmap do Projeto

## Próximas evoluções da arquitetura:

- Config Server
- Microservice de Orders
- Microservice de Notifications
- Comunicação assíncrona com Kafka
- Observabilidade com Prometheus + Grafana
- Docker
- CI/CD pipeline
- Deploy em Cloud
- Objetivo do Projeto

## Este projeto foi desenvolvido com o objetivo de demonstrar conhecimento em:

- Arquitetura de microserviços
- Design de APIs REST
- Autenticação segura
- Spring Cloud
- Arquitetura escalável de backend

---

# Autor

### Luiz Nicolau Pereira do Carmo

#### Estudante de Engenharia de Software focado em:

Arquitetura de software, desenvolvimento backend com Java, sistemas distribuídos, cloud e DevOps.

## Contato

[![LinkedIn](https://img.shields.io/badge/LinkedIn-Luiz%20Carmo-0A66C2?logo=linkedin&logoColor=white)](https://www.linkedin.com/in/luizcarmo/)

[![Gmail](https://img.shields.io/badge/Email-luizcarmo.dev@gmail.com-D14836?logo=gmail&logoColor=white)](mailto:luizcarmo.dev@gmail.com)
