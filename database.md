🔐 AUTH / USER DOMAIN (PostgreSQL)
**users** table
users
-----
id (UUID) PK
email (unique)
phone (unique)
password_hash
role (ADMIN, USER)
status (ACTIVE, BLOCKED)
created_at
updated_at

**user_profiles**
user_profiles
-------------
id (UUID) PK
user_id FK → users.id
first_name
last_name
dob
gender

**addresses**
addresses
---------
id (UUID) PK
user_id FK
address_line1
city
state
pincode
country
is_default

🔗 Relations
User 1 ──── * Address
User 1 ──── 1 Profile

📦 PRODUCT DOMAIN (MongoDB)
products collection
{
"_id": "ObjectId",
"name": "iPhone 15",
"description": "...",
"price": 79999,
"currency": "INR",
"categoryId": "electronics",
"stock": 120,
"images": ["url1", "url2"],
"attributes": {
"color": "black",
"storage": "128GB"
},
"createdAt": "ISODate"
}

categories
{
"_id": "ObjectId",
"name": "Electronics",
"parentCategory": null
}
🛒 CART DOMAIN (PostgreSQL / Redis later)
**cart**
cart
----
id UUID PK
user_id FK

**cart_items**
cart_items
----------
id UUID PK
cart_id FK
product_id
quantity
price_at_time

📑 ORDER DOMAIN (PostgreSQL)
**orders**
orders
------
id UUID PK
user_id
total_amount
status (CREATED, PAID, SHIPPED)
payment_id
created_at
order_items
order_items
-----------
id UUID PK
order_id FK
product_id
quantity
price

💳 PAYMENT DOMAIN (PostgreSQL)
**payments**
payments
--------
id UUID PK
order_id
payment_method (UPI, CARD)
payment_status
transaction_ref
created_at
🔔 NOTIFICATION / AUDIT (MongoDB)
notifications
{
"userId": "UUID",
"type": "PRODUCT_ADDED",
"message": "New product added",
"read": false,
"createdAt": "ISODate"
}
audit_logs
{
"service": "auth-service",
"action": "LOGIN_SUCCESS",
"userId": "UUID",
"timestamp": "ISODate"
}

4️⃣ DATABASE FLOWS (VERY IMPORTANT)
🔐 Login Flow
UI → auth-service
→ users table
→ password_hash check
→ JWT token
→ UI
🛍 Add to Cart Flow
UI → cart-service
→ cart_items
→ product-service (price)
📦 Order Placement Flow
UI
→ order-service
→ orders
→ order_items
→ payment-service
→ payments
🔔 New Product Notification Flow
Admin adds product
→ product-service
→ MongoDB
→ Event (Kafka later)
→ notification-service
→ notifications collection
5️⃣ ENTITY RELATION DIAGRAM (TEXTUAL)
USER ────< ADDRESS
USER ────< ORDER ────< ORDER_ITEM
USER ────< CART ────< CART_ITEM
ORDER ────1 PAYMENT
PRODUCT (MongoDB)
6️⃣ JPA vs Mongo Mapping (Backend View)
JPA (Postgres)
@Entity
@OneToMany
@ManyToOne
Mongo
@Document
Embedded objects
No joins
✅ What is DONE now
✔ Database schema
✔ Microservice ownership
✔ Data flows
✔ Mapping clarity
