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

curl -u username:password http://localhost:8080/authenticate

Response:

{"token": "eyJhbGciOiJIUzI1NiJ9...."}

### GET /validate

Verifies a JWT's signature and returns the authenticated username.

Request:

curl "http://localhost:8080/validate?token=YOUR_TOKEN_HERE"

### GET /admin

Restricted endpoint — only accessible to users with the ADMIN role. Returns 403 Forbidden for regular users.

Request:

curl -u admin:adminpassword http://localhost:8080/admin

## 🗄️ Database Design

A `User` entity (id, username, hashed password, role) is persisted via Spring Data JPA. Spring Security's `UserDetailsService` is implemented to load users directly from the database at login time, replacing an earlier in-memory, hardcoded version.

## 🔒 Security Highlights

- Passwords are never stored or compared in plain text — BCrypt hashing is applied before persistence and used automatically during authentication.
- Database credentials are kept out of source control, injected via environment variables rather than hardcoded in application.properties.
- SecurityFilterChain restricts endpoint access by role, returning 403 Forbidden for unauthorized roles.

## 📸 Screenshots

See the Screenshots folder for:
- Successful authentication and token generation
- Token validation response
- Role-based access control: USER blocked (403) vs ADMIN allowed (200)
- Database table showing persisted, hashed user records

## 🚀 How It Works

1. User sends credentials to /authenticate
2. Spring Security loads the user from the database and verifies the password using BCrypt
3. A JWT is generated, signed with a secret key (HS256), and returned
4. The token can later be sent to /validate to confirm identity
5. Role-restricted endpoints (like /admin) check the authenticated user's role before granting access

## 👩‍💻 Developer

Kameswari Sathvika Bhallamudi
- GitHub: https://github.com/sathvika0824
- LinkedIn: https://linkedin.com/in/sathvika-aiml
