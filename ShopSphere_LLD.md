# ShopSphere -- Low Level Design (LLD)

## 1. Services Overview

Total backend microservices:

1.  API Gateway\
2.  Auth Service\
3.  User Service\
4.  Product Service\
5.  Cart Service\
6.  Order Service\
7.  Payment Service\
8.  Notification Service

Each service is an independent Spring Boot application.

------------------------------------------------------------------------

## 2. Standard Internal Structure (All Services)

    controller  → REST endpoints
    service     → business logic
    repository  → database access
    entity      → database mapping
    dto         → request / response objects
    config      → security, filters, cache
    exception   → error handling

------------------------------------------------------------------------

## 3. Auth Service -- LLD

### Package Structure

    auth-service
     ├── controller/AuthController
     ├── service/AuthService
     ├── service/JwtService
     ├── repository/UserRepository
     ├── entity/User
     ├── dto/LoginRequest
     ├── dto/SignupRequest
     ├── dto/AuthResponse
     └── config/SecurityConfig

### Class Design

    AuthController → AuthService → UserRepository → PostgreSQL

------------------------------------------------------------------------

## 4. User Service -- LLD

    user-service
     ├── controller/UserController
     ├── service/UserService
     ├── repository/UserRepository
     ├── repository/AddressRepository
     ├── entity/User
     ├── entity/Address
     └── dto/UserProfileResponse

------------------------------------------------------------------------

## 5. Product Service -- LLD

    product-service
     ├── controller/ProductController
     ├── service/ProductService
     ├── repository/ProductRepository
     ├── document/Product
     └── dto/ProductResponse

Databases: - MongoDB (products) - Elasticsearch (search index)

------------------------------------------------------------------------

## 6. Cart Service -- LLD

    cart-service
     ├── controller/CartController
     ├── service/CartService
     ├── cache/RedisCartRepository
     └── dto/CartItemRequest

Redis Key Pattern:

    cart:{userId}

------------------------------------------------------------------------

## 7. Order Service -- LLD

    order-service
     ├── controller/OrderController
     ├── service/OrderService
     ├── repository/OrderRepository
     ├── entity/Order
     └── dto/OrderRequest

------------------------------------------------------------------------

## 8. Payment Service -- LLD

    payment-service
     ├── controller/PaymentController
     ├── service/PaymentService
     ├── repository/PaymentRepository
     └── entity/Payment

------------------------------------------------------------------------

## 9. Notification Service -- LLD

    notification-service
     ├── consumer/NotificationConsumer
     └── service/NotificationService

Messaging: - Kafka topics: order-created, product-added

------------------------------------------------------------------------

## 10. API Gateway -- LLD

    api-gateway
     ├── filter/JwtAuthFilter
     └── config/RouteConfig

------------------------------------------------------------------------

## 11. Class-Level Design Example (Auth Service)

    +------------------+
    | AuthController   |
    +------------------+
    | login()          |
    | signup()         |
    +------------------+
              |
              v
    +------------------+
    | AuthService      |
    +------------------+
    | authenticate()   |
    | register()       |
    +------------------+
              |
              v
    +------------------+
    | UserRepository   |
    +------------------+
    | findByEmail()    |
    | save()           |
    +------------------+

------------------------------------------------------------------------

## 12. Database Flows

### Login Flow

    UI → API Gateway → AuthController → AuthService → PostgreSQL

### Product Search Flow

    UI → ProductController → Redis → Elasticsearch → MongoDB

### Add to Cart Flow

    UI → CartController → Redis

### Checkout Flow

    Cart (Redis)
       ↓
    Order Service → PostgreSQL (orders)
       ↓
    Payment Service → PostgreSQL (payments)
       ↓
    Kafka → Notification Service

------------------------------------------------------------------------

## 13. Interview Summary Line

> Each microservice follows controller--service--repository design, owns
> its database, uses Redis for caching, Kafka for async communication,
> and communicates through an API Gateway.

------------------------------------------------------------------------

## 14. Notes

This Low-Level Design: - Is production aligned - Matches enterprise
