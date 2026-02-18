# DriveEase: E-Commerce REST API
## Assignment Three - Web Technology and Internet 2026

This project implements a robust RESTful API for managing an e-commerce product catalog using Spring Boot and PostgreSQL.

## Features
* **Product Management**: Full CRUD operations (Create, Read, Update, Delete).
* **Validation**: Checks for existing Product IDs to prevent duplicates.
* **Database Integration**: Persists data in a PostgreSQL `ecommerce_db`.

## Setup & Configuration
* **Java Version**: 21
* **Framework**: Spring Boot 4.0.2
* **Database**: PostgreSQL
* **Port**: 8080

## API Testing Screenshots (Post was done in class)

### GET All Products
![GET all products](https://github.com/user-attachments/assets/92069443-651d-4764-ba13-eb7debcda7f0)

### Get Product by ID
![Get Product by ID](https://github.com/user-attachments/assets/52acc205-b48c-4519-a79f-658df25b8dee)

### Update Product
![Update Product](https://github.com/user-attachments/assets/6bb91ac2-6f6a-4e83-abe7-06cf035fae45)

### Delete Product
![Delete Product](https://github.com/user-attachments/assets/cdf1e647-1105-426b-90d4-7af7c3302c97)

### Database State (pgAdmin)
<img width="958" height="502" alt="database" src="https://github.com/user-attachments/assets/82149d98-0105-4845-895b-19df09e833a5" />

---

## Database Configuration
The following properties are configured in `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/ecommerce_db
spring.datasource.username=postgres
spring.datasource.password=arthur
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
