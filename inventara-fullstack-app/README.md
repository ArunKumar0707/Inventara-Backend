# 📦 Inventara — Product Inventory Management System

A full-stack CRUD application built with **Spring Boot** (backend) and **Angular** (frontend), featuring a clean dashboard UI for managing products and categories.

---

## 🏗️ Architecture Overview

```
inventory-app/
├── backend/                          # Spring Boot application
│   └── src/main/java/com/inventory/app/
│       ├── InventoryApplication.java
│       ├── config/
│       │   └── DataSeeder.java        # Seeds sample data on startup
│       ├── controller/
│       │   ├── CategoryController.java
│       │   └── ProductController.java
│       ├── service/
│       │   ├── CategoryService.java
│       │   └── ProductService.java
│       ├── repository/
│       │   ├── CategoryRepository.java
│       │   └── ProductRepository.java
│       ├── entity/
│       │   ├── Category.java
│       │   └── Product.java
│       ├── dto/
│       │   ├── CategoryDTO.java
│       │   └── ProductDTO.java
│       └── exception/
│           ├── GlobalExceptionHandler.java
│           ├── ResourceNotFoundException.java
│           └── DuplicateResourceException.java
│
└── frontend/                         # Angular 17 application
    └── src/app/
        ├── app.component.*            # Root layout
        ├── components/
        │   ├── product-form/          # Add/Edit product form
        │   ├── product-list/          # Product table with pagination
        │   └── category-list/         # Category manager
        ├── services/
        │   ├── product.service.ts
        │   └── category.service.ts
        └── models/
            ├── product.model.ts
            └── category.model.ts
```

---

## 🚀 Running the Application

### Prerequisites

| Tool        | Version   |
|-------------|-----------|
| Java JDK    | 17+       |
| Maven       | 3.8+      |
| Node.js     | 18+       |
| Angular CLI | 17+       |

---

### Step 1 — Start the Backend

```bash
cd inventory-app/backend

# Build and run
./mvnw spring-boot:run

# Or with Maven installed globally
mvn spring-boot:run
```

**Backend starts at:** `http://localhost:8080`

**H2 Console:** `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:inventorydb`
- Username: `sa`
- Password: *(leave blank)*

The `DataSeeder` will automatically populate sample categories and products on startup.

---

### Step 2 — Start the Frontend

```bash
cd inventory-app/frontend

# Install dependencies
npm install

# Install Angular CLI globally (if not already)
npm install -g @angular/cli

# Start the dev server
ng serve
```

**Frontend starts at:** `http://localhost:4200`

---

## 🔌 API Endpoints

### Categories

| Method | Endpoint           | Description            |
|--------|--------------------|------------------------|
| POST   | /api/categories    | Create a new category  |
| GET    | /api/categories    | List all categories    |

### Products

| Method | Endpoint                         | Description                      |
|--------|----------------------------------|----------------------------------|
| POST   | /api/products                    | Create a new product             |
| GET    | /api/products                    | List products (paginated)        |
| GET    | /api/products/{id}               | Get a single product             |
| PUT    | /api/products/{id}               | Update a product                 |
| DELETE | /api/products/{id}               | Delete a product                 |
| GET    | /api/products/category/{catId}   | Filter products by category      |

#### Pagination Query Params (GET /api/products)
```
?page=0&size=10&sortBy=name&sortDir=asc
```

---

## 📬 Sample JSON Requests & Responses

### Create Category
**POST** `/api/categories`
```json
// Request
{
  "name": "Electronics"
}

// Response 201
{
  "id": 1,
  "name": "Electronics"
}
```

### Create Product
**POST** `/api/products`
```json
// Request
{
  "name": "Wireless Headphones",
  "price": 149.99,
  "quantity": 50,
  "categoryId": 1
}

// Response 201
{
  "id": 1,
  "name": "Wireless Headphones",
  "price": 149.99,
  "quantity": 50,
  "categoryId": 1,
  "categoryName": "Electronics"
}
```

### Get Products (Paginated)
**GET** `/api/products?page=0&size=5&sortBy=price&sortDir=desc`
```json
{
  "content": [
    {
      "id": 1,
      "name": "Laptop Pro 15",
      "price": 1299.99,
      "quantity": 25,
      "categoryId": 1,
      "categoryName": "Electronics"
    }
  ],
  "totalElements": 11,
  "totalPages": 3,
  "size": 5,
  "number": 0,
  "first": true,
  "last": false
}
```

### Update Product
**PUT** `/api/products/1`
```json
// Request
{
  "name": "Laptop Pro 15 (Updated)",
  "price": 1199.99,
  "quantity": 20,
  "categoryId": 1
}
```

### Validation Error Response
```json
{
  "status": 400,
  "message": "Validation failed",
  "errors": {
    "name": "Product name must not be blank",
    "price": "Price must be zero or greater"
  },
  "timestamp": "2024-01-15T10:30:00"
}
```

### Not Found Error Response
```json
{
  "status": 404,
  "message": "Product not found with id: 999",
  "timestamp": "2024-01-15T10:30:00"
}
```

---

## 📮 Postman Collection

Import the following JSON into Postman:

```json
{
  "info": { "name": "Inventara API", "schema": "https://schema.getpostman.com/json/collection/v2.1.0/collection.json" },
  "item": [
    {
      "name": "Categories",
      "item": [
        {
          "name": "Create Category",
          "request": {
            "method": "POST",
            "url": "http://localhost:8080/api/categories",
            "header": [{ "key": "Content-Type", "value": "application/json" }],
            "body": { "mode": "raw", "raw": "{\n  \"name\": \"Electronics\"\n}" }
          }
        },
        {
          "name": "Get All Categories",
          "request": { "method": "GET", "url": "http://localhost:8080/api/categories" }
        }
      ]
    },
    {
      "name": "Products",
      "item": [
        {
          "name": "Create Product",
          "request": {
            "method": "POST",
            "url": "http://localhost:8080/api/products",
            "header": [{ "key": "Content-Type", "value": "application/json" }],
            "body": { "mode": "raw", "raw": "{\n  \"name\": \"Wireless Headphones\",\n  \"price\": 149.99,\n  \"quantity\": 50,\n  \"categoryId\": 1\n}" }
          }
        },
        {
          "name": "Get All Products (Paginated)",
          "request": { "method": "GET", "url": { "raw": "http://localhost:8080/api/products?page=0&size=10&sortBy=id&sortDir=asc", "query": [{"key":"page","value":"0"},{"key":"size","value":"10"},{"key":"sortBy","value":"id"},{"key":"sortDir","value":"asc"}] } }
        },
        {
          "name": "Get Product by ID",
          "request": { "method": "GET", "url": "http://localhost:8080/api/products/1" }
        },
        {
          "name": "Update Product",
          "request": {
            "method": "PUT",
            "url": "http://localhost:8080/api/products/1",
            "header": [{ "key": "Content-Type", "value": "application/json" }],
            "body": { "mode": "raw", "raw": "{\n  \"name\": \"Updated Name\",\n  \"price\": 99.99,\n  \"quantity\": 30,\n  \"categoryId\": 1\n}" }
          }
        },
        {
          "name": "Delete Product",
          "request": { "method": "DELETE", "url": "http://localhost:8080/api/products/1" }
        },
        {
          "name": "Get Products by Category",
          "request": { "method": "GET", "url": "http://localhost:8080/api/products/category/1" }
        }
      ]
    }
  ]
}
```

---

## ✨ Features

**Backend**
- Full CRUD for Products
- Create & Read for Categories
- H2 in-memory database (zero setup)
- Spring Data JPA with pagination & sorting
- Bean Validation (`@NotBlank`, `@Min`, `@NotNull`)
- Global exception handler with structured error responses
- CORS configured for Angular dev server
- Sample data seeded automatically

**Frontend**
- Dashboard layout (form left, table right)
- Add and edit products via reactive form
- Delete with confirmation dialog
- Category filter dropdown
- Sortable table columns (click header)
- Pagination controls
- Stock status badges (In Stock / Low Stock / Out of Stock)
- Real-time success/error notifications
- Form validation with field-level messages
- Category manager with inline form

---

## 🎨 UI Design

Dark industrial theme with warm amber accents:
- **Font pair**: Fraunces (serif, headings) + DM Mono (monospace, data)
- **Color palette**: Dark charcoal surfaces + amber `#E8A44A` accent
- **Design tokens**: Full CSS variable system for consistent theming
