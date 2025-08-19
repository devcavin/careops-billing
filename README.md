# sbur-rest-demo

A simple RESTful API for managing coffee records, built with **Kotlin** and **Spring Boot**.  
This project is inspired by **Chapter 3** of the book _`Spring Boot: Up & Running – Building Cloud-Native Java and Kotlin Applications`_ by **Mark Heckler**.

---

## Purpose

This repository is part of a hands-on learning journey with Spring Boot and Kotlin.  
It demonstrates how to build a **CRUD API** using Spring Boot’s core web features, without a database (in-memory 
list only). I used _H2_ for this project.

---

## Getting Started

### Prerequisites

- Java 17 or later
- Kotlin (via IntelliJ IDEA or Gradle)
- Gradle or Maven (choose based on your project setup)

### Running the App

**With Gradle**:

```bash
./gradlew bootRun

```

### API Endpoints
| Method | Endpoint        | Description             |
|--------|-----------------|-------------------------|
| GET    | `/coffees`      | Retrieve all coffees    |
| GET    | `/coffees/{id}` | Retrieve a coffee by ID |
| POST   | `/coffees`      | Create a new coffee     |
| PUT    | `/coffees/{id}` | Update or create coffee |
| DELETE | `/coffees/{id}` | Delete a coffee by ID   |


### Example Request/Response

#### Create a New Coffee

**POST /coffees**

```json
{
  "name": "Espresso"
}

```
**Sample response**
```json
{
  "id": "123",
  "name": "Espresso"
}

```

## Reference
This code is based on:
`Spring Boot: Up & Running
Building Cloud-Native Java and Kotlin Applications
Author: Mark Heckler`
[O’Reilly Media](https://www.oreilly.com/library/view/spring-boot-up/9781492076971/)

