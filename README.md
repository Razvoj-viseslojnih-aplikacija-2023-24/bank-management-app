# 🏦 Bank Management System

A full-stack bank management application built as part of the *Razvoj višeslojnih aplikacija* course at the Faculty of Technical Sciences, University of Novi Sad.

## 🛠️ Tech Stack

| Layer | Technology |
|-------|-----------|
| Frontend | Angular |
| Backend | Java · Spring Boot · Spring Data JPA |
| Database | PostgreSQL |
| API | REST |

## ✨ Features

- Role-based access control (Employee / Administrator)
- Account creation and management
- Transaction processing with full history
- RESTful API following SOLID principles
- Multi-layer architecture: Presentation → Service → Repository → Database

## 🏗️ Architecture

The application follows a standard multi-layer architecture:
Frontend (Angular)
↓
REST API (Spring Boot Controllers)
↓
Service Layer (Business Logic)
↓
Repository Layer (Spring Data JPA)
↓
Database (PostgreSQL)

## 🚀 Getting Started

### Prerequisites
- Java 17+
- Node.js & npm
- PostgreSQL

### Backend
```bash
cd backend
./mvnw spring-boot:run
```

### Frontend
```bash
cd frontend
npm install
ng serve
```

## 👤 Author

**Jovan Ćurčin Miletaški**  
Information Systems Engineering · FTN Novi Sad  
[LinkedIn](https://www.linkedin.com/in/jovan-ćurčin-miletaški-35b7a1295/)
