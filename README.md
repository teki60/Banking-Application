# 🏦 Banking App Backend (Spring Boot)

This is a simple **banking-style backend application** built with **Java Spring Boot**, designed to support a frontend that allows users to:

* Register & Login
* View balance
* Deposit money
* Withdraw money
* View transaction history

This project will gradually evolve from **basic CRUD** into a **secure and scalable** enterprise-style backend.

---

## 🚀 Tech Stack

| Technology      | Purpose                  |
| --------------- | ------------------------ |
| Java 17+        | Backend Language         |
| Spring Boot     | Main framework           |
| Spring Web      | REST API                 |
| Spring Data JPA | Database access layer    |
| MySQL           | Database                 |
| Lombok          | Reduces boilerplate code |

---

## 📌 Features (Current Phase)

✔ User Registration
✔ Login & Validation
✔ Account for each user
✔ Deposit & Withdraw operations
✔ Track all transactions
✔ REST API endpoints for frontend integration

---

## 🗂️ Project Structure (Layered Architecture)

```
src/
 └─ main/
     ├─ java/
     │   └─ com.banking
     │        ├─ controller       # REST APIs
     │        ├─ service          # Business logic
     │        ├─ repository       # DB queries (Spring JPA)
     │        ├─ entity           # DB models
     │        └─ dto              # Request/Response models
     └─ resources/
         └─ application.properties
```

---

## 🛠️ API Endpoints (Phase 1 - Basic)

| Method | Endpoint                 | Description                 |
| ------ | ------------------------ | --------------------------- |
| POST   | `/register`              | Create a new user + account |
| POST   | `/login`                 | Validate user               |
| GET    | `/balance/{userId}`      | Fetch current balance       |
| POST   | `/deposit`               | Add deposit amount          |
| POST   | `/withdraw`              | Withdraw amount if possible |
| GET    | `/transactions/{userId}` | View full history           |

---

## 🗄 Database Design

### User Table

| Field     | Type      | Description        |
| --------- | --------- | ------------------ |
| id        | PK        | Unique user ID     |
| username  | String    | Login name         |
| password  | String    | Encrypted password |
| createdAt | Timestamp | Registration date  |

### Account Table

| Field   | Type   | Description               |
| ------- | ------ | ------------------------- |
| id      | PK     | Account ID                |
| user_id | FK     | Linked user               |
| balance | Double | Current available balance |

### Transaction Table

| Field      | Type      | Description               |
| ---------- | --------- | ------------------------- |
| id         | PK        | Transaction ID            |
| account_id | FK        | Linked account            |
| type       | Enum      | DEPOSIT / WITHDRAW        |
| amount     | Double    | Transaction amount        |
| timestamp  | Timestamp | When transaction happened |

---

## ▶️ How to Run the App

1️⃣ Configure DB connection in `application.properties`

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/banking_app
spring.datasource.username=root
spring.datasource.password=YOUR_PASSWORD
spring.jpa.hibernate.ddl-auto=update
```  

2️⃣ Run MySQL Server  
3️⃣ Start Spring Boot Application (`BankingAppBackendApplication.java`)  
4️⃣ Test APIs using Postman / Frontend  

---

## 🎯 Future Enhancements (Learning Roadmap)

| Phase   | What Gets Added                 | Skills Learned                   |
| ------- | ------------------------------- | -------------------------------- |
| Phase 2 | JWT Security, Role-based access | Spring Security + Authentication |
| Phase 3 | Microservices & Messaging       | Kafka, API Gateway, Docker       |
| Phase 4 | Cloud Deployment                | AWS / Azure / CI-CD              |

---

## 🤝 Contribution

This project is for building backend skills step-by-step.
You can extend and improve along the journey!

---
