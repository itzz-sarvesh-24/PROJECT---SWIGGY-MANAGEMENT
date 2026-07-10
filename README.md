🍽️ Swiggy Order Management System

A RESTful backend application built with Spring Boot that simulates the core functionality of an online food ordering platform. The application focuses on creating and managing customer orders while automatically calculating billing information on the server, ensuring data integrity and reducing client-side responsibility.

✨ Project Highlights
🛍️ Create food orders containing multiple menu items in a single request
🧮 Automatically calculate the order's total amount using item price and quantity
🔗 Maintain a parent-child relationship between orders and their items using JPA
📋 Retrieve a specific order or view the complete order history
🗑️ Delete orders along with all associated items using cascading operations
💾 Use an H2 in-memory database for fast development without external database configuration
🏗️ Application Design

The project follows Spring Boot's layered architecture to separate responsibilities and keep the code clean and maintainable.

Client Request
       │
       ▼
 Controller
       │
       ▼
  Service Layer
(Business Logic)
       │
       ▼
 Repository
(Spring Data JPA)
       │
       ▼
    Database
      (H2)
Order Entity

Represents a customer's order and stores:

Customer name
Restaurant name
Total order amount
Collection of ordered items
Item Entity

Represents each food item inside an order.

Each item contains:

Item name
Quantity
Price

Every order can contain multiple items through a One-to-Many relationship.

⚙️ Business Logic

Instead of trusting the client to send the total bill, the backend calculates it internally.

Total Amount = Σ (Item Price × Quantity)

For example,

Item	Price	Quantity	Total
Butter Naan	₹40	2	₹80
Paneer Tikka	₹180	1	₹180

Final Order Total = ₹260

This approach prevents users from manipulating the total amount before submitting an order.

🛠️ Technology Stack
Technology	Purpose
Java 17	Programming Language
Spring Boot 4.1.0	Backend Framework
Spring Data JPA	Database Access
Hibernate	ORM Framework
H2 Database	In-memory Database
Maven	Dependency Management
📂 Project Structure
src
└── main
    ├── java
    │   └── com.example.swiggy
    │       ├── controller
    │       ├── service
    │       ├── repository
    │       ├── entity
    │       └── SwiggyApplication.java
    │
    └── resources
        ├── application.properties
        └── data.sql (optional)
🌐 REST API

Base URL

/api/v1/orders
HTTP Method	Endpoint	Description
POST	/create	Create a new order
GET	/{id}	Retrieve an order by ID
GET	/getorders	Retrieve all orders
DELETE	/{id}	Delete an order
📨 Sample Request
POST /api/v1/orders/create
Content-Type: application/json
{
  "customerName": "Sarvesh",
  "restaurantName": "Punjabi Tadka",
  "items": [
    {
      "itemName": "Butter Naan",
      "quantity": 2,
      "price": 40
    },
    {
      "itemName": "Paneer Tikka",
      "quantity": 1,
      "price": 180
    }
  ]
}
✅ Sample Response
{
  "orderId": 1,
  "customerName": "Sarvesh",
  "restaurantName": "Punjabi Tadka",
  "totalAmount": 260.0,
  "items": [
    {
      "itemId": 1,
      "itemName": "Butter Naan",
      "quantity": 2,
      "price": 40
    },
    {
      "itemId": 2,
      "itemName": "Paneer Tikka",
      "quantity": 1,
      "price": 180
    }
  ]
}
🗄️ Database

The application uses an H2 In-Memory Database.

Open the console at:

http://localhost:8080/h2-console

Connection Details

Property	Value
JDBC URL	jdbc:h2:mem:swiggydb
Username	sa
Password	(leave empty)

Since the database exists only in memory, all records are cleared whenever the application is restarted.

▶️ Running the Project

Clone the repository

git clone <repository-url>

Move into the project

cd Swiggy

Start the application

./mvnw spring-boot:run

The server starts on

http://localhost:8080
💡 Design Decisions
Server-side total calculation improves data consistency.
Cascade persistence allows an order and its items to be saved in a single transaction.
Layered architecture keeps controllers lightweight and business logic centralized.
Auto-generated IDs simplify API usage by removing the need for clients to manage identifiers.
🚀 Planned Improvements
Payment management module
Order status workflow
Input validation using @Valid
Centralized exception handling
JWT-based authentication and authorization
PostgreSQL/MySQL integration
Customer and Restaurant entities
Swagger/OpenAPI documentation
Pagination and filtering for order history
👨‍💻 Developer

Sarvesh M

Backend Developer | Java | Spring Boot | REST API | JPA | Hibernate

📄 License

This project is intended for educational purposes, personal learning, and portfolio demonstrations.
