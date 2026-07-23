# 🔐 JWT Authentication Service

A Spring Boot REST API that implements JWT-based authentication — issuing signed tokens on login and verifying them on request, demonstrating the complete authentication lifecycle.

## ✨ Features

- 🔑 Secure login using Spring Security's HTTP Basic Authentication
- 🎫 Issues signed JWT tokens (HS256) upon successful login
- ✅ Dedicated /validate endpoint to verify token authenticity
- 🛡️ Signature-based verification to detect tampered or fake tokens
- ⏱️ Token expiry handling (20-minute validity)

## 🛠️ Tech Stack

- Java
- Spring Boot 3
- Spring Security
- JJWT (io.jsonwebtoken)
- Maven

## 📍 Endpoints

### GET /authenticate
Authenticates a user via Basic Auth and returns a signed JWT.

Request:
curl -s -u user:pwd http://localhost:8080/authenticate

Response:
{"token": "eyJhbGciOiJIUzI1NiJ9...."}

### GET /validate
Verifies a JWT's signature and returns the authenticated username.

Request:
curl "http://localhost:8080/validate?token=YOUR_TOKEN_HERE"

Response:
Token is valid. Welcome, user

## 📸 Screenshots

See the Screenshots folder for:
- Successful authentication and token generation
- Token validation response
- Eclipse console logs confirming server startup

## 🚀 How It Works

1. User sends credentials to /authenticate
2. Spring Security verifies credentials against the configured user store
3. A JWT is generated, signed with a secret key (HS256), and returned
4. The token can later be sent to /validate
5. The server recalculates the signature and confirms the token's authenticity

## 👩‍💻 Developer

Kameswari Sathvika Bhallamudi
- GitHub: https://github.com/sathvika0824
- LinkedIn: https://linkedin.com/in/sathvika-aiml
