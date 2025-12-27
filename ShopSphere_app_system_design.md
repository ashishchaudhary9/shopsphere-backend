## 1. Application Name Suggestions

You can choose a name that sounds modern, scalable, and commerce-oriented:

- **ShopSphere** (recommended – scalable ecosystem feel)
- Cartify
- BuyNest
- QuickKart
- NeoShop
- PayCart

For this document, I’ll refer to the application as **ShopSphere**.

---

## 2. Simplified & Improved Problem Statement

**ShopSphere** is a scalable, secure shopping web application designed using modern **system design and microservices architecture** principles. It allows users to browse products, manage carts, place orders, and make payments via UPI, while administrators manage products, users, and system operations.

The system is built with **Spring Boot microservices**, uses **PostgreSQL and MongoDB**, supports **fast search**, **caching**, **load balancing**, **secure authentication**, **session management**, **audit logging**, and **real-time notifications**.

The goal is to design an **industry-grade e-commerce platform** that demonstrates best practices in backend design, scalability, security, and performance.

---

## 3. Refined Feature List (Add / Remove / Improve)

### ✅ Core Features (Kept & Improved)
- User roles: **Admin**, **Normal User**
- Login / Signup via **Email or Phone Number**
- Encrypted password storage (BCrypt)
- Product listing & detail pages
- Cart management (Add / Remove items)
- Checkout flow with address selection
- Payment via **UPI (QR Code)**
- Session management
- Logging & audit logs
- Notifications on new product addition

### ➕ Recommended Additions (Industry-Standard)
- JWT-based authentication (stateless)
- API Gateway
- Rate limiting
- Centralized logging
- Search service (Elasticsearch)
- Message queue for notifications (Kafka / RabbitMQ)
- CDN for static content
- Role-based access control (RBAC)

### ➖ Not Required at Initial Phase (Can be Phase-2)
- URL Shortener (not core to shopping app, optional microservice)

---

## 4. High-Level Architecture (HLD)

```
Client (Browser / Mobile)
        |
        v
   F5 Load Balancer (Nginx /F5/ ALB) 
        |
        v
   API Gateway (Spring Cloud Gateway)
        |
-------------------------------------------------
| Auth | User | Product | Cart | Order | Payment |
-------------------------------------------------
        |
-------------------------------------------------
| PostgreSQL | MongoDB | Redis | Elasticsearch |
-------------------------------------------------
        |
-----------------------------
| Kafka / RabbitMQ | Logs |
-----------------------------
```

### Responsibilities
- **F5 Load Balancer**: Traffic distribution, SSL termination
- **API Gateway**: Routing, authentication, rate limiting
- **Microservices**: Single responsibility
- **Databases**: Polyglot persistence
- **Cache**: Performance optimization
- **Message Queue**: Async processing

---

## 5. Microservices Breakdown (LLD)

### 5.1 Auth Service
- Login / Signup
- Password encryption (BCrypt)
- JWT token generation

**Tech**: Spring Boot, PostgreSQL

---

### 5.2 User Service
- User profile
- Address management
- Settings

---

### 5.3 Product Service
- Product CRUD (Admin)
- Product listing
- Category management

**DB**: MongoDB (flexible schema)

---

### 5.4 Cart Service
- Add / Remove products
- Cart persistence

**DB**: Redis (fast access)

---

### 5.5 Order Service
- Checkout
- Order history
- Order status

---

### 5.6 Payment Service
- UPI QR generation
- Payment status callback

---

### 5.7 Notification Service
- New product notification
- Order confirmation

**Async via Kafka**

---

## 6. Database Design – Entities & Attributes

### 6.1 User Entity (PostgreSQL)
```
User
----
user_id (PK)
name
email
phone
password_hash
role (ADMIN / USER)
created_at
status
```

---

### 6.2 Address Entity
```
Address
-------
address_id (PK)
user_id (FK)
street
city
state
pincode
is_default
```

---

### 6.3 Product Entity (MongoDB)
```
Product
-------
product_id
name
description
price
category
stock
images[]
created_at
```

---

### 6.4 Cart Entity (Redis)
```
Cart
----
user_id
items { product_id : quantity }
```

---

### 6.5 Order Entity (PostgreSQL)
```
Order
-----
order_id (PK)
user_id
order_status
total_amount
payment_id
created_at
```

---

### 6.6 Payment Entity
```
Payment
-------
payment_id
order_id
payment_mode (UPI)
status
transaction_ref
```

---

## 7. Key System Flows

### 7.1 Login Flow
```
User → API Gateway → Auth Service → DB
        ← JWT Token ←
```

---

### 7.2 Product Search Flow
```
User → Gateway → Product Service → Cache → Elasticsearch → DB
```

---

### 7.3 Add to Cart Flow
```
User → Cart Service → Redis
```

---

### 7.4 Checkout Flow
```
Cart → Order Service → Payment Service → UPI QR
```

---

### 7.5 Notification Flow
```
Product Added → Kafka → Notification Service → User
```

---

## 8. Caching Strategy

- **Redis** for cart & session data
- Cache-aside pattern
- TTL-based eviction
- Manual cache reset via Admin API

---

## 9. Logging & Audit

- Application logs (ELK Stack)
- Audit logs:
  - Login attempts
  - Admin actions
  - Payment updates

---

## 10. Security Considerations

- HTTPS via F5
- JWT + Refresh Token
- BCrypt password hashing
- RBAC
- SQL injection protection
- Rate limiting

---

## 11. Summary

**ShopSphere** is a well-structured, scalable shopping application showcasing real-world system design concepts like microservices, load balancing, caching, async processing, secure authentication, and database optimization.

This design is suitable for:
- System design interviews
- Real enterprise projects
- Learning Spring Boot microservices deeply

---

➡️ Next, we can deep-dive into:
- API contracts
- Sequence diagrams
- JWT & session management
- Database indexing & sharding
- F5 load balancer configuration

