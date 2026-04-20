# BIMASAKTI_TEST

A simple Spring Boot REST API for user registration and retrieval.

## Tech Stack

- Java 11 / Spring Boot 2.4.5
- PostgreSQL
- Spring Data JPA / Hibernate
- Maven

## Endpoints

| Method | Path | Description |
|--------|------|-------------|
| POST | `/register/create` | Register a new user |
| GET | `/api/user/{name}` | Get user by name |

## Running Locally

1. Start PostgreSQL and create database `db_users`
2. Update credentials in `src/main/resources/application.properties`
3. Run: `mvn spring-boot:run`
4. Server starts on port `3333`
