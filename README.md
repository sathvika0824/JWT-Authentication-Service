# 🔐 JWT Authentication Service

![Java](https://img.shields.io/badge/Java-17-orange?logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3-brightgreen?logo=springboot)
![MySQL](https://img.shields.io/badge/MySQL-Database-blue?logo=mysql)
![JWT](https://img.shields.io/badge/JWT-HS256-yellow?logo=jsonwebtokens)
![Security](https://img.shields.io/badge/Security-BCrypt%20%2B%20RBAC-red?logo=springsecurity)

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

```
curl -u username:password http://localhost:8080/authenticate
```

**Response:**
```json
{"token": "eyJhbGciOiJIUzI1NiJ9...."}
```

[![Login Success](https://github.com/sathvika0824/JWT-Authentication-Service/raw/main/Screenshots/login-success-token.png)](Screenshots/login-success-token.png)

### GET /validate

Verifies a JWT's signature and returns the authenticated username.

```
curl "http://localhost:8080/validate?token=YOUR_TOKEN_HERE"
```

### GET /admin

Restricted endpoint — only accessible to users with the ADMIN role.

```
curl -u admin:adminpassword http://localhost:8080/admin
```

**USER role → blocked:**

[![Access Denied](https://github.com/sathvika0824/JWT-Authentication-Service/raw/main/Screenshots/admin-access-denied-403.png)](Screenshots/admin-access-denied-403.png)

**ADMIN role → allowed:**

[![Access Granted](https://github.com/sathvika0824/JWT-Authentication-Service/raw/main/Screenshots/admin-access-granted.png)](Screenshots/admin-access-granted.png)

## 🗄️ Database Design

A `User` entity (id, username, hashed password, role) is persisted via Spring Data JPA. Spring Security's `UserDetailsService` loads users directly from the database at login time.

[![Database Records](https://github.com/sathvika0824/JWT-Authentication-Service/raw/main/Screenshots/database-hashed-passwords.png)](Screenshots/database-hashed-passwords.png)

## 🔒 Security Highlights

- Passwords are never stored or compared in plain text — BCrypt hashing applied before persistence.
- Database credentials kept out of source control, injected via environment variables.
- `SecurityFilterChain` restricts endpoint access by role, returning 403 Forbidden for unauthorized roles.

## 🚀 How It Works

1. User sends credentials to `/authenticate`
2. Spring Security loads the user from the database and verifies the password using BCrypt
3. A JWT is generated, signed with a secret key (HS256), and returned
4. The token can later be sent to `/validate` to confirm identity
5. Role-restricted endpoints (like `/admin`) check the authenticated user's role before granting access

## 👩‍💻 Developer

**Kameswari Sathvika Bhallamudi**

- GitHub: github.com/sathvika0824
- LinkedIn: linkedin.com/in/sathvika-aiml
