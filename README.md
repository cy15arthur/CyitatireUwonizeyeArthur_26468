# Web Technology And Internet: RESTful API Development Assignment

**Student Full Name:** Cyitatire Uwonizeye Arthur  
**Student ID:** 26468  
**Date:** February 11, 2026  

---

## 📋 Project Description

This repository contains five standalone Spring Boot REST API projects developed for the Web Technology course. Each module demonstrates proficiency in the **MVC (Model-View-Controller)** design pattern, handling HTTP methods, and implementing clean, readable Java logic using enhanced for-loops.

The projects focus on building RESTful services using in-memory data storage and proper API design practices.

---

## 📁 Project Modules

1. **Question 1: Library Book Management**  
   - Basic CRUD operations for a digital catalog.

2. **Question 2: Student Registration System**  
   - Managing student profiles and enrollment records.

3. **Question 3: Restaurant Menu Management**  
   - Categorizing food items, pricing, and availability.

4. **Question 4: E-commerce Product Catalog**  
   - Advanced API featuring:
     - Pagination  
     - Keyword Search  
     - Price Filtering  

5. **Question 5: Task Management API**  
   - To-do list system with:
     - Status Tracking  
     - Priority Management  
     - Partial Updates  

---

## 🧠 Core Concepts Explained

To build these APIs, the following architectural layers and concepts were implemented.

---

### 1️⃣ The Model Class

The **Model** represents the data structure of the application.

Example: `Product` class

It defines attributes such as:
- `productId`  
- `name`  
- `price`  
- `stockQuantity`  

The model acts as a **blueprint** for objects managed by the API.

---

### 2️⃣ The REST Controller

The **Controller** handles incoming HTTP requests and returns appropriate responses.

Key annotations and concepts:

- **@RestController**  
  Indicates that the class handles REST API requests.

- **@RequestMapping**  
  Defines the base URL path (e.g., `/api/products`).

- **Dependency Injection**  
  Allows Spring to manage class dependencies automatically.

The controller acts as the “brain” of each API module.

---

### 3️⃣ HTTP Methods & Business Logic

Each project implements the main RESTful HTTP methods.

#### ✅ GET
- Used to retrieve data.
- Enhanced for-loops are used to filter lists by:
  - Category  
  - Brand  
  - Price Range  
  - Status  

#### ✅ POST
- Used to create new resources.
- Receives data in JSON format from the request body.

#### ✅ PUT vs PATCH

| Method | Purpose | Example |
|--------|----------|----------|
| PUT | Full Update | Replace entire product object |
| PATCH | Partial Update | Update stock or mark task as completed |

#### ✅ DELETE
- Removes a resource using its unique ID.
- Uses enhanced for-loops to locate and delete items.

---

## 🚀 Getting Started & Installation

Follow these steps to run the projects locally.

---

### 1️⃣ Prerequisites

Make sure you have:

- **Java:** JDK 17 or later  
- **Build Tool:** Apache Maven  
- **Testing Tool:** Postman  

---

### 2️⃣ Clone the Repository

Run the following commands in your terminal:

```bash
git clone https://github.com/cy15arthur/CyitatireUwonizeyeArthur_26468.git
cd CyitatireUwonizeyeArthur_26468
