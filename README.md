Spring Boot CRUD API with JWT Authentication
 Project Overview

This is a Spring Boot REST API that supports:

✔ User Registration
✔ User Login (JWT Authentication)
✔ Create / Read / Update / Delete Notes
✔ Authentication using JWT Token
✔ Password hashing using BCrypt
✔ Swagger UI for API testing
✔ MySQL database
✔ ModelMapper + DTOs

How to Run the Project
1. Clone the repository
   git clone https://github.com/aabidahmad/crudapi1.git
   cd crudapi

Open the project in IntelliJ IDEA / Eclipse / VS Code

Wait for Maven to download dependencies

Continue from Step 2 below

2️⃣ Configure MySQL Database

Create the database:

CREATE DATABASE notes_api;

3️⃣ Update application.properties

Open:

src/main/resources/application.properties

Add/update:

spring.datasource.url=jdbc:mysql://localhost:3306/crudapi
spring.datasource.username=root
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

jwt.secret=your_secret_key
jwt.expiration=86400000

4️⃣ Run the Project
Option A — From IntelliJ IDEA

✔ Right-click → CrudApiApplication.java → Run

Option B — Using Maven

Open terminal inside project folder:

mvn spring-boot:run


Sample API Requests
🧑‍💻 Auth APIs
1️⃣ Register

POST /auth/register
Request Body

{
"name":"adil",
"email":"ad@gmail.com",
"password":"1234"
}

2️⃣ Login

POST /auth/login
Request Body

{
"email":"ad@gmail.com",
"password":"1234"
}


Response Example

{
"token": "eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJhZEBnbWFpbC5jb20iLCJpYXQiOjE3NjQ4MjI0ODIsImV4cCI6MTc2NDgyNjA4Mn0.PtoM_zlvR4r8vcRDcnH-R1BeDnKOhhoE0sU06VQ7nEZSVqaJ2jTwCJjZgz4XC19w"
}

Copy the JWT token → use it in Authorization Header

Authorization: Bearer <token>

Notes APIs (Require JWT)
3️⃣ Create Note

POST /notes

{"title": "Study Topics", "content": "Review Java Streams, Spring Security, and JWT."}

4️⃣ Get My Notes

GET /notes

Authorization: Bearer <token>

Response

[
{
"id": 12,
"title": "Study Topics",
"content": "Review Java Streams, Spring Security, and JWT.",
"username": "adil"
}
]

🛠 Tech Stack
Spring Boot 3
Spring Security + JWT
MySQL
JPA / Hibernate
ModelMapper
BCryptPasswordEncoder

📌 Assumptions

Each note belongs to one user.

A user can access only their own notes.

JWT is passed in the Authorization: Bearer token header.

Passwords are stored hashed (BCrypt).

Email must be unique for registration.

Project Structure

src/main/java/com/crudapi
│
├── controller
│     └── AuthController.java
│     └── NoteController.java
│
├── dto
│     └── LoginDTO.java
│     └──NoteRequest.java
│     └── NoteResponseDTO.java
│     └── RegisterRequest.java
│
├── entity
│     └── User.java
│     └── Note.java
│
├── exception
│     └── GlobalExceptionHandler.java
│    
├── repository
│     └── UserRepository.java
│     └── NoteRepository.java
│
├── security
│     └── JwtFilter.java
│     └── JwtUtil.java
│     └── SecurityConfig.java
│     └── CustomUserDetailsService.java
│
│
├── service
│     └── AuthService.java
│     └── NoteService.java
│
├── CrudapiApplication.java





