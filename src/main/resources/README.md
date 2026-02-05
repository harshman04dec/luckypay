# LuckyPay – Loan Account Service

## Overview
This is a Spring Boot REST API that:
- Integrates with an external Loan Account API
- Persists loan EMI details into a MySQL database
- Exposes a GET API to fetch loan due information

## Tech Stack
- Java: 17 (mandatory)
- Spring Boot: 3.5.x (recommended: 3.5.1 or latest 3.5.x)
- Build Tool: Gradle
- Database: MySQL 8+
- ORM: Spring Data JPA (Hibernate)
- HTTP Client: WebClient
- Logging: SLF4J + Logback (default with Spring Boot)

## Prerequisites
Ensure the following are installed:
- Java 17 (`java -version`)
- MySQL Server running locally
- Gradle (or use Gradle Wrapper)

## Database Setup

### 1. Create Database
Login to MySQL and create the database:

```sql
CREATE DATABASE luckypay;
