# 🔐 JWT Authentication Service

A Spring Boot REST API implementing complete, production-style JWT authentication — including database-backed user storage, BCrypt password hashing, and role-based access control.

## ✨ Features

- 🔑 Secure login using Spring Security's HTTP Basic Authentication
- 🎫 Issues signed JWT tokens (HS256) upon successful login
- ✅ Dedicated /validate endpoint to verify token authenticity
- 🛡️ Signature-based verification to detect tampered or fake tokens
- ⏱️ Token expiry handling (20-minute validity)
- 🗄️ Real user persistence via MySQL and Spring Data JPA (no hardcoded credentials)
- 🔒 Passwords hashed with BCrypt — never stored in plain text
- 👤 Role-based access control (USER / ADMIN) restricting sensitive endpoints

## 🛠️ Tech Stack

- Java 17
- Spring Boot 3
- Spring Security
- Spring Data JPA
- MySQL
- JJWT (io.jsonwebtoken)
- Maven

## 📍 Endpoints

### GET /authenticate
Authenticates a user via Basic Auth (checked against the database, password verified with BCrypt) and returns a signed JWT.

Request:
