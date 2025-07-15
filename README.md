

# 🔐 Spring Boot Role-Based Authentication with PostgreSQL

A simple Spring Boot application that demonstrates **basic authentication** and **role-based access control** using **Spring Security** and **PostgreSQL**.

---

## 📋 Features

✅ Basic Authentication using Spring Security
✅ Role-based access for USER and ADMIN
✅ PostgreSQL database integration
✅ Secure REST API endpoints
✅ Preloaded users (intern + admin)
✅ Password encryption with BCrypt
✅ Environment variable support (for Docker and local)

---

## 🧰 Tech Stack

* Java 17
* Spring Boot 3.x
* Spring Web
* Spring Security
* Spring Data JPA
* PostgreSQL
* Maven

---

## 🗃️ Database Setup

Create a PostgreSQL database named:

```sql
CREATE DATABASE intern_db;
```

Ensure your PostgreSQL is running on:

* Host: `localhost`
* Port: `5432`
* Username: `postgres`
* Password: `password`

You can change these in environment variables or fallback in `application.properties`.

---

## 🛠 Environment Configuration Notes

This project uses environment variables to configure database connection and server port. These variables are typically defined in a `.env` file or passed directly into the application.

---

## 🚀 How to Run

### 1️⃣ Run Without Docker (Local)

Make sure your `application.properties` has default fallback values like:

```properties
spring.datasource.url=${SPRING_DATASOURCE_URL:jdbc:postgresql://localhost:5432/intern_db}
spring.datasource.username=${SPRING_DATASOURCE_USERNAME:postgres}
spring.datasource.password=${SPRING_DATASOURCE_PASSWORD:password}
server.port=${PORT:8081}
```

This means if environment variables are not set, the app will use the defaults above.

**Steps:**

1. Clone the repo:

```bash
git clone https://github.com/rajin-siam/authenticationDemo.git
cd authenticationDemo
```

2. Update PostgreSQL credentials in `.env` or `application.properties` if needed.

3. Run the app:

```bash
mvn spring-boot:run
```

---

### 2️⃣ Run With Docker

Make sure your `.env` file includes this (note `host.docker.internal` for PostgreSQL host):

```env
SPRING_DATASOURCE_URL=jdbc:postgresql://host.docker.internal:5432/intern_db
SPRING_DATASOURCE_USERNAME=postgres
SPRING_DATASOURCE_PASSWORD=password
PORT=8081
```

Then, use this Docker command to run the app:

```bash
docker run -p 8081:8081 --env-file .env rajinsiam/authenticationdemo
```

> ⚠️ **Important:** The hostname `host.docker.internal` only works inside Docker containers to connect to your local machine. Don't use this setting outside Docker.

---

## 🔐 Predefined Users

| Role  | Username | Password    |
| ----- | -------- | ----------- |
| USER  | intern   | password123 |
| ADMIN | admin    | admin123    |

Passwords are **BCrypt encoded** at startup.

---

## 📡 API Endpoints

| Method | Endpoint  | Access     | Description                       |
| ------ | --------- | ---------- | --------------------------------- |
| GET    | `/public` | Public     | Accessible by anyone              |
| GET    | `/user`   | USER/ADMIN | Requires login                    |
| GET    | `/admin`  | ADMIN      | Only accessible by admin          |
| POST   | `/users`  | ADMIN      | Add new user (username, password) |

**Sample request to `/users` (admin only):**

```json
{
  "username": "newuser",
  "password": "newpass",
  "role": "USER"
}
```

---

## ✅ How to Test

Use Postman or curl.

Example for `/user` with Basic Auth:

* URL: `http://localhost:8081/user`
* Auth:

    * Username: `intern`
    * Password: `password123`

**Expected Response:**

```
This is a user endpoint
```

---

## ❓ Assumptions

* Only two roles: `USER` and `ADMIN`
* Passwords stored with `BCryptPasswordEncoder`
* Only admin can create new users
* Database must exist beforehand (no auto-creation)

---

## 📌 Project Structure

```
src/
 └── main/
     ├── java/
     │   └── com/siam/
     │       ├── model/
     │       │   └── AppUser.java
     │       ├── repository/
     │       │   └── UserRepository.java
     │       ├── security/
     │       │   ├── CustomUserDetailsService.java
     │       │   └── SecurityConfig.java
     │       ├── controller/
     │       │   └── UserController.java
     │       └── SpringSecurityApplication.java
     └── resources/
         └── application.properties
```

---

## 🧑‍💻 Author

Md. Rajin Mashrur Siam

[GitHub](github.com/rajin-siam)

---
