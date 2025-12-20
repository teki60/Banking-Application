# 🏦 Banking App Backend (Spring Boot + AI Integration)

A **production-style** Banking Backend built with **Java Spring Boot**, featuring:

- Secure user registration & login
- Account creation for each user
- Deposit & withdrawal operations
- Transaction history
- Clean layered architecture
- MySQL persistence
- **AI-powered Financial Insights** _(Phase 2)_
- **AI Transaction Explanation** _(Phase 2)_
- **AI Fraud Detection** _(Phase 2)_

This project is built step-by-step to learn **enterprise backend**, **microservices**, and **AI integration**.

---

# 🚀 Tech Stack

| Technology                   | Purpose                    |
| ---------------------------- | -------------------------- |
| **Java 17+**                 | Backend Language           |
| **Spring Boot**              | Main Application Framework |
| **Spring Web**               | REST API                   |
| **Spring Data JPA**          | ORM / Database Access      |
| **MySQL**                    | Database                   |
| **Lombok**                   | Reduce Boilerplate Code    |
| **Spring AI** _(Phase 2)_    | AI/LLM Integration Layer   |
| **Groq LLM API** _(Phase 2)_ | Fast AI Inference          |

---

# 📌 Features (Phase 1 – Core Banking Backend)

### 🔐 Authentication

✔ User Registration
✔ Login validation
✔ Auto-create account for new users

### 💰 Banking Operations

✔ Deposit money
✔ Withdraw money
✔ Account balance retrieval

### 📜 Transaction System

✔ Transaction logging
✔ View transaction history
✔ Enum-based transaction types

### 🧱 Architecture

✔ Controller → Service → Repository layers
✔ DTO-based input/output
✔ MySQL persistence
✔ Validation for invalid inputs

---

# 🤖 Phase 2 – AI Features (COMPLETED)

Your backend integrates **Spring AI + Groq LLM** to provide AI-driven financial guidance to users.

These features use:

- User transaction data  
- Account balance  
- Spending patterns  
- Time-based behavior  

to generate **human-like explanations and advice**.

---

### 1️⃣ AI Transaction Explanation ✅

LLM analyzes the user’s **last N transactions** and explains what happened.

**Input to AI**
- Last N transactions  
- Current balance  

**What it explains**
- How the balance changed  
- Deposits vs withdrawals  
- Recent transaction behavior  

**Example**
> “You deposited ₹5000 and withdrew ₹1000 twice. Your balance increased due to consistent deposits.”

---

### 2️⃣ AI Smart Financial Insights ✅

LLM acts like a **financial advisor** and gives suggestions on how the user can save money.

**Input to AI**
- Last N transactions  
- Current balance  
- Transaction frequency  
- Average withdrawal amount  

**What it provides**
- Spending habit analysis  
- Overspending identification  
- Suggestions to save money  
- Motivational and friendly advice  

**Example**
> “You tend to withdraw money frequently. Reducing small withdrawals could help you control spending and save more over time.”

---

### 3️⃣ AI Fraud & Risk Awareness Detection ✅

This feature **does NOT block transactions** and **does NOT label fraud**.

It only provides **risk awareness** to the user.

**Input to AI**
- Last N transactions  
- Balance  
- Transaction frequency  
- Average & maximum withdrawal  
- Late-night transaction count  

**What it checks**
- Unusually large withdrawals  
- Late-night transaction patterns  
- Sudden behavior changes  

**Behavior**
- Calm  
- Reassuring  
- Non-alarming  
- Advisory only  

**Example**
> “Two withdrawals happened late at night. If these are unfamiliar, consider reviewing them or contacting support.”

---

# 🗂️ Project Structure (Clean Layered Architecture)

```
src/
 └─ main/
     ├─ java/com/banking_app_backend
     │    ├─ controller/            # REST Controllers
     │    ├─ service/               # Interfaces
     │    ├─ service/impl/          # Business Logic
     │    ├─ repository/            # Database Access (JPA)
     │    ├─ entity/                # DB Tables
     │    ├─ dto/                   # Request/Response Models
     │    ├─ ai/                    # Spring AI Integration Layer
     │    └─ exception/             # (Future) Global Exception Handling
     └─ resources/
          └─ application.properties / application.yml

```

---

# 🗄 Database Design

### **User Table**

| Field     | Type      | Description           |
| --------- | --------- | --------------------- |
| id        | PK        | Unique User ID        |
| userName  | String    | Unique login name     |
| password  | String    | (Will encrypt later)  |
| createdAt | Timestamp | Account creation date |

---

### **Account Table**

| Field   | Type   | Description             |
| ------- | ------ | ----------------------- |
| id      | PK     | Account ID              |
| user_id | FK     | Linked User             |
| balance | Double | Current account balance |

---

### **Transaction Table**

| Field      | Type      | Description           |
| ---------- | --------- | --------------------- |
| id         | PK        | Unique transaction ID |
| account_id | FK        | Linked account        |
| type       | Enum      | DEPOSIT / WITHDRAW    |
| amount     | Double    | Transaction amount    |
| timestamp  | Timestamp | Time of transaction   |

---

# 🛠️ API Endpoints

## 🔐 Auth APIs

| Method | Endpoint         | Description             |
| ------ | ---------------- | ----------------------- |
| POST   | `/auth/register` | Register user + account |
| POST   | `/auth/login`    | Login user              |

## 💰 Account APIs

| Method | Endpoint                         | Description           |
| ------ | -------------------------------- | --------------------- |
| GET    | `/account/balance/{userId}`      | Get balance           |
| POST   | `/account/deposit`               | Deposit money         |
| POST   | `/account/withdraw`              | Withdraw money        |
| GET    | `/account/transactions/{userId}` | Full transaction list |

---

## 🤖 AI Endpoints (Phase 2 – Implemented)

| Method | Endpoint                                   | Description                           |
|------:|--------------------------------------------|---------------------------------------|
| GET   | `/ai/explain-transactions/{userId}?n=5`    | AI-generated transaction explanation  |
| GET   | `/ai/smart-insights/{userId}?n=5`           | AI-based financial advice             |
| GET   | `/ai/check-fraud/{userId}?n=5`              | AI risk awareness analysis             |


---

# ▶️ How to Run

### 1️⃣ Setup MySQL and update:

```
spring.datasource.url=jdbc:mysql://localhost:3306/banking_app
spring.datasource.username=root
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```

### 2️⃣ Run MySQL

### 3️⃣ Start Spring Boot

### 4️⃣ Test in Postman

---

# 🧭 Future Enhancements (Complete Roadmap)

### ⭐ Phase 1 – Core Banking (COMPLETED)

- Register/Login
- Deposit/Withdraw
- Balance & Transactions
- DTO + layered architecture

---

### ⭐ Phase 2 – AI Integration (COMPLETED)

- Spring AI setup
- Groq model integration
- Transaction explanation endpoint
- Smart insights endpoint
- Fraud detection endpoint

---

### ⭐ Phase 3 – Security

- JWT Authentication
- Password hashing
- Role-based access
- Secure endpoints

---

### ⭐ Phase 4 – Production Quality

- Global exception handler
- Error response standardization
- Logging (SLF4J)
- Validation handling

---

### ⭐ Phase 5 – Microservices Architecture

Split into multiple services:

- Auth Service
- Account Service
- Transaction Service
- AI Service

Then add:

- Kafka event streaming
- API Gateway (Spring Cloud)
- Service Discovery (Eureka)
- Config Server
- Distributed logs

---

### ⭐ Phase 6 – DevOps & Cloud

- Dockerize all services
- Kubernetes deployment
- CI/CD pipeline
- AWS deployment (EKS, RDS, Load Balancer)

---

# 🤝 Contribution

This project is built step-by-step as a learning journey into **professional backend development + AI engineering**.
Enhance it, extend it, and continue improving every phase.
