# endterm-projectOOP
Real Estate API Documentation




  Bonus task at the end:)






A. Project Overview:

This project is a professional RESTful API for a Real Estate Management System built with Spring Boot. The system manages property listings, allowing for the creation, retrieval, and deletion of different property types like Houses and Apartments. The architecture is designed to be scalable, using a layered package structure consisting of controllers, services, repositories, and models.


B. REST API Documentation:

Endpoint list

GET /api/properties: Retrieves all property records.

POST /api/properties: Adds a new House or Apartment listing.

DELETE /api/properties/{id}: Removes a listing by its unique identifier.

HTTP methods

GET: Used for reading data.

POST: Used for creating new resources.

DELETE: Used for removing resources.

Sample JSON responses

GET request: [ { "id": 1, "address": "Abay 10", "price": 50000000, "type": "house", "yardSize": 100 } ]

Error response: { "status": 404, "error": "Not Found", "message": "Property not found with id: 99" }

Postman screenshots are available in docs/screenshots/


C. Design Patterns Section:

Singleton Pattern

Purpose: Ensures a single point of access to the database connection.

Usage: Implemented in DatabaseConfig to manage the JDBC connection instance efficiently.

Factory Pattern

Purpose: Centralizes the creation logic of different property types.

Usage: PropertyFactory creates either a House or Apartment object based on the input type string.

Builder Pattern

Purpose: Simplifies the construction of complex objects with many attributes.

Usage: Implemented via Lombok @SuperBuilder in the Property, House, and Apartment classes.


D. Component Principles Section:

REP (Release-Reuse Equivalency): Classes are grouped into packages like model and repository to facilitate reuse across the system.

CCP (Common Closure Principle): Classes that change together, such as PropertyRepository and its implementation, are kept in the same repository package.

CRP (Common Reuse Principle): The package structure ensures that users of the service layer do not need to depend on internal repository implementation details.


E. SOLID and OOP Summary:

Single Responsibility: Each layer has one task (e.g., Exception handles only errors).

Open/Closed: The system is open for adding new property types without changing the base Property class.

Dependency Inversion: PropertyService depends on the PropertyRepository interface rather than a concrete class.

Inheritance and Polymorphism: House and Apartment inherit from the abstract Property class.


F. Database Schema:

Table name: properties

Columns: id (serial), address (varchar), price (double), type (varchar), yard_size (int), floor_number (int).


G. System Architecture Diagram:

Client (Postman) -> Controller -> Service -> Repository -> Database (PostgreSQL).

Note: You can find the visual diagram in the docs/uml.png file.

.


H. Instructions to Run the Spring Boot Application:

Ensure PostgreSQL is running and the database realestate_db is created.

Update application.properties with your database username and password.

Open the project in IntelliJ IDEA and wait for Maven to load dependencies.

Run the RealEstateApiApplication.java file.

Access the API at http://localhost:8080/api/properties.

.

Bonus: Caching Layer Implementationя

Singleton Cache: Created a `PropertyCache` class using the Singleton pattern to manage in-memory data.

Performance Enhancement: Implemented caching in the `findAll()` method to return data from memory after the first database query.

Auto-Invalidation: The cache is automatically cleared during `save` and `delete` operations to ensure data consistency.

SOLID Principles: The caching mechanism is decoupled and integrated into the Service layer, maintaining the layered architecture.

