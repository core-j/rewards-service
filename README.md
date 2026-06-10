# Rewards Service Application

## Overview
The Rewards Service Application is a Spring Boot REST API that calculates reward points for customers based on their purchase transactions over a rolling three-month period.

The application demonstrates:
* Layered architecture
* RESTful API design
* Reward calculation business logic
* Exception handling
* Unit and integration testing
* Swagger/OpenAPI documentation
* SQL-based test data loading

---

# Business Requirement
A retailer offers reward points based on the following rules:

* 2 points for every dollar spent above $100
* 1 point for every dollar spent between $50 and $100

### Example
Purchase Amount: $120

Reward Calculation:
* $50 between $50-$100 → 50 points
* $20 above $100 → 40 points

Total Reward Points:
90 points

---

# Features
* Reward point calculation
* Monthly reward aggregation
* Total reward calculation
* Dynamic month handling (no hardcoded months)
* RESTful API implementation
* Global exception handling
* Swagger API documentation
* H2 in-memory database
* SQL script-based data initialization
* Unit testing with Mockito
* Integration testing with MockMvc

---

# Technology Stack
* Java 17
* Spring Boot 3
* Spring Data JPA
* H2 Database
* Maven
* Lombok
* Swagger / OpenAPI
* JUnit 5
* Mockito

---

# Project Structure

src
 ├── main
 │    ├── java
 │    │     └── com.epurchase.rewards
 │    │            ├── controller
 │    │            ├── dto
 │    │            ├── entity
 │    │            ├── exception
 │    │            ├── repository
 │    │            ├── service
 │    │            │      └── serviceImpl
 │    │            └── RewardsServiceApplication
 │    └── resources
 │           ├── application.properties
 │           └── data.sql
 │
 └── test
       └── java
              └── com.epurchase.rewards
                      ├── controller
                      |        └── RewardControllerIntegrationTest
                      ├── service
                               └── RewardServiceImplTest

---

# Setup Instructions

## Prerequisites

Make sure the following software is installed:

* Java 17+
* Maven
* IntelliJ IDEA
* Git

---

# Clone Repository
git clone <>

---

# Run Application
Open the project in IntelliJ IDEA.

Run:
RewardsServiceApplication.java

Application runs on: http://localhost:8080

---

# API Endpoints

## Get Reward Details

### Request
http GET /api/rewards/{customerId}

### Example
http GET /api/rewards/1

### Sample Response
{
  "customerId": 1,
  "customerName": "Chaithanya",
  "monthlyRewards": {
    "APRIL": 90,
    "MAY": 25,
    "JUNE": 250
  },
  "totalRewards": 365
}

---

# Swagger Documentation

Swagger UI:
http://localhost:8080/swagger-ui.html

---

# H2 Database Console

H2 Console:
http://localhost:8080/h2-console

JDBC URL:
jdbc:h2:mem:rewardsdb

---

# Testing

## Unit Testing
* JUnit 5
* Mockito

## Integration Testing
* SpringBootTest
* MockMvc

## Negative Test Scenarios
* Invalid customer ID
* Negative transaction amount

---

# Design Decisions

* DTOs are used to separate API contracts from database entities.
* Layered architecture improves maintainability and scalability.
* Map-based month aggregation avoids hardcoding month names.
* SQL scripts are used for test data loading instead of hardcoded Java objects.

---

# Assumptions

* Reward points are calculated per transaction.
* Transactions belong to a single customer.
* Negative amounts generate zero reward points.
* Only the last three months of transactions are considered.

---

# Future Enhancements

* PostgreSQL integration
* Docker containerization
* Authentication & Authorization
* CI/CD pipeline integration
* Caching using Redis
* Cloud deployment

---

# Contributors
* Chaithanya Vulasa
