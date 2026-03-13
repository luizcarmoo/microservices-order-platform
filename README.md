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

# Quick Start

Executar toda a arquitetura localmente.

1 - Iniciar o Service Discovery

    cd eurekaserver
    mvn spring-boot:run

2 - Iniciar o Config Server

    cd configserver
    mvn spring-boot:run

3 - Iniciar o Auth Service

    cd authservice
    mvn spring-boot:run

4 - Iniciar o API Gateway

    cd apigateway
    mvn spring-boot:run

Depois acessar:

Gateway:

    http://localhost:8082
            
Eureka Dashboard:

    http://localhost:8761
            
Auth Service Health:

    http://localhost:8081/actuator/health

---

# Componentes da Arquitetura

## API Gateway

Responsável por:

- centralizar o acesso à API
- roteamento de requisições
- validação de JWT
- comunicação com Service Discovery

Porta padrão: 

    http://localhost:8082
                       

Exemplo de rota: 


    /auth/** → AUTH-SERVICE

Com Service Discovery: 

    lb://AUTH-SERVICE

---

# Service Discovery

O projeto utiliza **Service Discovery** com **Eureka Server**.

Responsabilidades:

- registrar serviços
- permitir descoberta dinâmica
- facilitar escalabilidade

Dashboard: 

    http://localhost:8761

Serviços registrados: 

    AUTH-SERVICE
    API-GATEWAY


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

# Config Server

O projeto utiliza **Spring Cloud Config Server** para configuração centralizada dos microserviços.

### Benefícios:

- centralização das configurações
- gerenciamento de ambiente
- atualização dinâmica de propriedades

Servidor disponível em:

    http://localhost:8888

Exemplo de configuração utilizada nos serviços:

    spring.config.import=optional:configserver:http://localhost:8888

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

**BCrypt**

Hash seguro para armazenamento de senhas.

**JWT** (JSON Web Token)

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

# Monitoramento

O projeto utiliza **Spring Boot Actuator** para monitoramento dos serviços.

Endpoint de health check:

    http://localhost:8081/actuator/health

Resposta esperada:

    {
      "status": "UP"
      ...
    }

Esse endpoint é utilizado para:

- monitoramento de microserviços
- health checks
- integração com ferramentas de observabilidade

---

# Tecnologias Utilizadas

### Backend

- Java 17
- Spring Boot
- Spring Security
- Spring Data JPA
- Spring Cloud Gateway
- Spring Cloud Netflix Eureka
- Spring Cloud Config
- Spring Boot Actuator

### Segurança

- JWT
- BCrypt

### Ferramentas

- Maven
- Lombok
- H2 Database

---

## Pré-requisitos

- Java 17
- Maven

---

# Arquitetura do Sistema

#### Arquitetura baseada em Spring Cloud Microservices.

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
             ┌──────────────┐       ┌──────────────┐
             │ Auth Service │       │ Future       │
             │ JWT + Users  │       │ Orders       │
             │ + Actuator   │       │ Notifications│
             └──────┬───────┘       └──────────────┘
                    │
                    ▼
             ┌──────────────┐
             │ ConfigServer │
             │ Centralized  │
             │ Configuration│
             └──────────────┘

---

# Características da Arquitetura

Este projeto implementa diversos padrões utilizados em arquiteturas modernas de microserviços.

#### API Gateway Pattern

Centralização de entrada da API utilizando gateway para roteamento e segurança.

#### Service Discovery Pattern

Serviços são registrados dinamicamente utilizando Eureka.

#### Externalized Configuration

Configurações centralizadas através do Config Server.

#### Stateless Authentication

Autenticação baseada em JWT.

#### Observability

Monitoramento de serviços através do Spring Boot Actuator.

---

# Roadmap do Projeto

#### Próximas evoluções

- Order Service
- Notification Service
- Comunicação assíncrona com Kafka
- Observabilidade completa com Prometheus + Grafana
- Containerização com Docker
- Orquestração com Docker Compose
- Cache distribuído com Redis
- Resiliência com Circuit Breaker
- CI/CD Pipeline
- Deploy em Cloud

### Este projeto foi desenvolvido com o objetivo de demonstrar conhecimento em:

- Arquitetura de microserviços
- Design de APIs REST
- Autenticação segura
- Spring Cloud
- Arquitetura escalável de backend

---

# Arquitetura de Produção (planejada)

Este projeto evoluirá para uma arquitetura completa com:

- API Gateway
- Service Discovery
- Config Server
- Autenticação JWT
- Observabilidade
- Containers Docker
- Comunicação assíncrona
- CI/CD

---

# Autor

### Luiz Nicolau Pereira do Carmo

#### Estudante de Engenharia de Software focado em:

Arquitetura de software, desenvolvimento backend com Java, sistemas distribuídos, cloud e DevOps.

## Contato

[![LinkedIn](https://img.shields.io/badge/LinkedIn-Luiz%20Carmo-0A66C2?logo=linkedin&logoColor=white)](https://www.linkedin.com/in/luizcarmo/)

[![Gmail](https://img.shields.io/badge/Email-luizcarmo.dev@gmail.com-D14836?logo=gmail&logoColor=white)](mailto:luizcarmo.dev@gmail.com)
