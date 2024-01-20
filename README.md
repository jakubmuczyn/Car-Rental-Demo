# Car Rental System Demo

## Short Description
This project, developed as a final project of the Java Developer SDA course, aims to create a car rental management system that facilitates operations across multiple cities. The system will support car reservations, rentals, and returns, while also providing features to calculate rental revenues and present essential statistics.

## Main System Features
1. **Car Reservation**
   - Users can reserve a car for a specified date and time.

2. **Car Rental**
   - Facilitates the process of renting a car after a successful reservation.

3. **Car Return**
   - Allows users to return rented cars, updating their availability for future rentals.

4. **Management of Branch Locations**
   - Admins can manage and add new branches in different cities.

5. **Fleet Management**
   - Admins can oversee the entire fleet of cars, including their current locations.

6. **Calculation of Revenues**
   - The system will automatically calculate rental revenues based on reservations and returns.

7. **Statistics**
   - Provides basic statistics such as the number of reservations, rentals, returns, and overall revenue.

## Technologies
- **Backend:**
  - **Spring Framework:** A comprehensive framework for building enterprise Java applications.
  - **Spring Web:** A module for building web applications.
  - **Spring Security:** A powerful and customizable authentication and access control framework.
  - **Spring JPA (Java Persistence API):** A specification for accessing, persisting, and managing data between Java objects and relational databases.
  - **H2 Database Integration:** Leverage the power of Spring Data JPA and H2 in-memory database for lightweight, embedded storage during development.
  - **Hibernate:** An object-relational mapping framework for Java that provides a way to map Java objects to relational database tables.
  - **Lombok:** A library that reduces boilerplate code in Java by generating common methods like getters, setters, and constructors.


- **Frontend:**
  - **Thymeleaf:** A modern server-side Java template engine for web and standalone environments. It enables natural templates with minimal code and can be integrated with Spring applications.

## Getting Started:

1. **Clone the Repository:**
   ```bash
   git clone https://github.com/jakubmuczyn/Car-Rental-Demo.git
   ```

2. **Navigate to the Project Directory:**
   ```bash
   cd Car-Rental-Demo
   ```

3. **Build and Run the Application:**

   Unix:
   ```bash
   ./mvnw spring-boot:run
   ```
   or Windows:
   ```bash
   mvnw.cmd spring-boot:run
   ```

4. **Access the Application:**

   Open your web browser and visit http://localhost:8080 to use the Todo App.

## Prerequisites:
- Java Development Kit (JDK)
- Maven
- [Your favorite IDE with Spring Boot support (e.g., IntelliJ, Eclipse)]

## Database Configuration:
- The application is configured to use the H2 in-memory database by default.
- Access the H2 Console at http://localhost:8080/h2-console for database management.

## License:
This Todo App is open-source and available under the MIT License.