# ⚡ Async Audit Logging System

A scalable asynchronous backend system built with **Spring Boot**, **MongoDB**, and **PostgreSQL**, designed to demonstrate concurrent request processing, decoupled audit logging, and enterprise-grade backend architecture.

This project focuses on:
- Asynchronous execution
- Concurrent backend processing
- REST API design
- Thread pool management
- Dual-database architecture
- Observability & audit systems

---

# 🚀 Overview

The system provides REST APIs for managing user operations while asynchronously recording detailed audit logs without blocking the primary request lifecycle.

User data is stored in **MongoDB**, while audit events are persisted separately in **PostgreSQL** to simulate enterprise-scale observability and compliance systems.

The application leverages:
- `CompletableFuture`
- Spring Async Processing
- Custom Thread Pools
- Concurrent execution patterns

to improve responsiveness and system scalability.

---

# ✨ Features

## ⚡ Asynchronous Processing
- Non-blocking service execution using `@Async`
- `CompletableFuture`-based request handling
- Decoupled audit workflows

## 🧵 Concurrent Execution
- Custom `ThreadPoolTaskExecutor`
- Controlled thread pool scaling
- Parallel request & audit processing

## 🗂️ Dual Database Architecture
### MongoDB
- Stores application user data
- Flexible document-based persistence

### PostgreSQL
- Stores structured audit logs
- Optimized for querying and reporting

## 📈 Audit Logging Infrastructure
Captures:
- User actions
- API request details
- Thread execution information
- Status codes
- Timestamps
- Error traces

## 🌐 REST APIs
Supports:
- Create User
- Update User
- Delete User
- Fetch Users

---

# 🏗️ System Architecture

```text
                    Client Request
                           │
                           ▼
                   Spring REST Controller
                           │
                           ▼
                  Async Service Layer
                           │
            ┌──────────────┴──────────────┐
            ▼                             ▼
     MongoDB User Store          Async Audit Service
                                                │
                                                ▼
                                    PostgreSQL Audit Logs
```

---

# 🧠 Core Engineering Concepts

## Asynchronous Backend Design
Implemented asynchronous workflows using:
- `@EnableAsync`
- `@Async`
- `CompletableFuture`

to separate business operations from audit persistence.

---

## Thread Pool Management

Configured custom thread pools using:

```java
ThreadPoolTaskExecutor
```

with:
- core pool sizing
- max thread scaling
- queue management

for controlled concurrency handling.

---

## Observability & Audit Systems

Designed a lightweight audit infrastructure that records:
- execution metadata
- request context
- thread information
- operation outcomes

without impacting API response latency.

---

## Polyglot Persistence

Implemented a dual-database strategy:
- MongoDB for operational user data
- PostgreSQL for structured audit records

to separate transactional and analytical concerns.

---

# 🛠️ Tech Stack

## Backend
- Spring Boot
- Java

## Databases
- MongoDB
- PostgreSQL

## Concurrency
- CompletableFuture
- Spring Async
- ThreadPoolTaskExecutor

## Build Tool
- Maven

## Concepts
- REST APIs
- Concurrent Programming
- Async Execution
- Backend Architecture
- Audit Logging
- DTO Design

---

# 📂 Project Structure

```text
src/
│
├── controller/
├── service/
├── repository/
├── dto/
├── model/
├── config/
├── audit/
└── async/
```

---

# ⚙️ Installation & Setup

## 1️⃣ Clone Repository

```bash
git clone https://github.com/AKHIL159/Async-Audit-Logging-System.git
cd async-audit-logging-system
```

---

## 2️⃣ Configure Databases

### MongoDB
Start MongoDB locally.

### PostgreSQL
Create PostgreSQL database for audit logging.

Update credentials in:

```properties
application.properties
```

---

## 3️⃣ Build Project

```bash
mvn clean install
```

---

## 4️⃣ Run Application

```bash
mvn spring-boot:run
```

---

# 🌐 API Endpoints

## Create User

```http
POST /users
```

## Get User

```http
GET /users/{id}
```

## Update User

```http
PUT /users/{id}
```

## Delete User

```http
DELETE /users/{id}
```

---

# 📊 Audit Logging Workflow

```text
Incoming API Request
        │
        ▼
Business Logic Execution
        │
        ▼
Immediate API Response
        │
        ▼
Separate Async Thread
        │
        ▼
Persist Audit Metadata
```

This architecture prevents logging operations from blocking user-facing API execution.

---

# 🧵 Threading Model

The application demonstrates:
- request thread separation
- async worker thread execution
- concurrent task delegation
- scalable background processing

Audit operations run independently from primary business workflows.

---

# 🔐 Engineering Challenges Solved

- Decoupling audit logging from request lifecycle
- Managing concurrent execution safely
- Designing scalable async workflows
- Maintaining thread-aware observability
- Integrating multiple persistence technologies

---

# 📈 Scalability Considerations

The architecture is designed to support:
- higher request throughput
- independent audit scaling
- asynchronous side-effect handling
- reduced request blocking

Future enhancements may include:
- Kafka event streaming
- distributed tracing
- reactive programming
- centralized logging systems

---

# 📚 Learning Outcomes

This project strengthened understanding of:
- asynchronous backend systems
- concurrent programming
- thread pool management
- scalable REST API design
- observability architecture
- enterprise backend workflows

---

# 👨‍💻 Author

## Akhil

Focused on:
- Backend Engineering
- Concurrent Systems
- Distributed Architectures
- Performance Optimization
- Scalable API Design

---

# ⭐ If you like this project

Consider giving it a ⭐ on GitHub!
