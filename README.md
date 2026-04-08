# 📦 ControleStock

Sistema completo de **Controle de Estoque e Usuários**, composto por uma API backend em Java (Spring Boot) e um aplicativo Android.

---

## 🚀 Tecnologias Utilizadas

### 🔙 Backend
![Java](https://img.shields.io/badge/Java-21-orange)
![Spring Boot](https://img.shields.io/badge/SpringBoot-3.x-green)
![Spring Security](https://img.shields.io/badge/SpringSecurity-JWT-green)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-Database-blue)
![Docker](https://img.shields.io/badge/Docker-Container-blue)
![Swagger](https://img.shields.io/badge/Swagger-OpenAPI-green)
![Flyway](https://img.shields.io/badge/Flyway-Migrations-red)
![Lombok](https://img.shields.io/badge/Lombok-Productivity-orange)

### 📱 Android
![Android](https://img.shields.io/badge/Android-Java-green)
![Retrofit](https://img.shields.io/badge/Retrofit-HTTP-blue)
![Navigation](https://img.shields.io/badge/Navigation-Component-purple)
![Material UI](https://img.shields.io/badge/Material-Design-blue)

---

## 📖 Sobre o Projeto

O **ControleStock** é um sistema desenvolvido para gerenciamento de usuários e controle de estoque.

O projeto é dividido em duas partes:

- 🔙 API Backend em Java com Spring Boot
- 📱 Aplicativo Android consumindo a API

---

## 🧱 Arquitetura

- API RESTful com autenticação JWT
- Banco de dados PostgreSQL rodando em Docker
- Aplicativo Android consumindo API via Retrofit
- Navegação mobile com Drawer (menu lateral)

---

# 🔙 Backend (Spring Boot)

## ⚙️ Funcionalidades

- Autenticação com JWT
- Controle de acesso por perfil (ADMIN / USER)
- Cadastro de usuários
- Listagem de usuários
- Consulta do usuário autenticado
- Validações com Bean Validation
- Documentação com Swagger
- Migração de banco com Flyway

---

## 🐳 Banco de Dados com Docker

O projeto utiliza PostgreSQL rodando em container Docker.

### ▶️ Executar com Docker

```bash
docker run --name postgres-controlstock \
  -e POSTGRES_DB=controlstock \
  -e POSTGRES_USER=postgres \
  -e POSTGRES_PASSWORD=123456 \
  -p 5432:5432 \
  -d postgres
  
 ```

````
version: '3.8'

services:
  postgres:
    image: postgres:latest
    container_name: postgres_controlstock
    restart: always
    environment:
      POSTGRES_DB: controlstock
      POSTGRES_USER: postgres
      POSTGRES_PASSWORD: 123456
    ports:
      - "5432:5432"
    volumes:
      - postgres_data:/var/lib/postgresql/data

volumes:
  postgres_data:

````
## Estrutura do projeto

```text
src/main/java/com/paulo/controlstock
├── api
├── controllers
│   ├── authentication
│   ├── products
│   └── user
├── dtos
├── exceptions
├── infra
│   ├── documentation
│   └── security
├── models
├── repositorys
├── services
│   ├── authentication
│   ├── product
│   └── user
└── ControlstockApplication.java
```
## 🔐 Autenticação

A API utiliza JWT para autenticação.

Fluxo:
- Login com usuário e senha
- Recebe token JWT
- Envia token no header:
````
Authorization: Bearer TOKEN
````
Swagger

````
Acesse a documentação da API:

http://localhost:8080/swagger-ui/index.html

````
---

## 📱 Aplicativo Android

## Funcionalidades
- Tela de Splash
- Tela de Login
- Consumo da API via Retrofit
- Menu lateral (Drawer)
- Navegação por Fragments
- Aplicativo Android desenvolvido em Java para consumir a API ControleStock e gerenciar autenticação e usuários em dispositivos móveis.
---
##  📊 Telas do App
- 🔐 Login
- 🏠 Home
- 👤 Meu Usuário
- ➕ Criar Usuário
- 📋 Listar Usuários
---
## 🧭 Navegação
```
O app utiliza Navigation Component + DrawerLayout.
```
Fragments:
- Home
- Meu Usuário
- Criar Usuário
- Listar Usuários
---

## 🔌 Integração com API
- Retrofit para requisições HTTP
- Gson para conversão JSON
- Token JWT armazenado e enviado nas requisições
---

## ⚙️ Configuração
- **Application ID:** `com.prsoftware.appcontrolstock`
- **Namespace:** `com.prsoftware.appcontrolstock`
- Linguagem: Java
- **minSdk:** 21
- **targetSdk:** 35
- **compileSdk:** 36
- **versionName:** `1.0`
---
## Arquitetura geral

O app está organizado em módulos e pacotes como:

```text
app/src/main/java/com/prsoftware/appcontrolstock
├── api
├── dto
├── service
│   └── login
├── ui
│   ├── create
│   ├── homeUser
│   ├── list
│   └── user
├── HomeActivity.java
├── MainActivity.java
├── SplashActivity.java
└── UsuarioActivity.java
```
---

---

# 🔥 Resultado

Esse README:
- tem **badges profissionais**
- explica backend + app
- mostra arquitetura
- inclui Docker (🔥 diferencial forte)
- tem espaço para prints
- fica perfeito para recrutador

---

## Imagens
# 📱 Aplicativo Android

## 🔐 Login
![Login](img/Tela%20Home%20APP.png)

## 📊 Home
![Home](img/Tela%20Home%20APP.png)

## 📊 Home Usuário
![Home User](img/Home%20User%20App.png)

## 👤 Meu Usuário
![User](img/Meu%20Usuario%20APP.png)

## ➕ Criar Usuário
![Create](img/Criar%20Usuario%20APP.png)

## 📝 Listar Usuários
![Listar](img/Listar%20Usuarios%20APP.png)
---

## 👨‍💻 Autor
````
Paulo Trindade
💼 Desenvolvedor Backend & Mobile
🚀 Focado em Java, Spring Boot e Android
````
---
