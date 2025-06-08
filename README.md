# Spring Boot with PostgreSQL Example

This project demonstrates a Spring Boot application integrated with PostgreSQL database, featuring employee management
functionality. It includes Docker setup for both PostgreSQL and pgAdmin for easy development and deployment.

## Technologies Used

- Spring Boot
- PostgreSQL 17.5
- Flyway for database migrations
- Docker & Docker Compose
- pgAdmin 4
- Gradle

## Features

- RESTful API for Employee management
- Database schema migration using Flyway
- Containerized PostgreSQL and pgAdmin setup
- CRUD operations for Employee entity

## Prerequisites

- Docker and Docker Compose
- JDK 17 or later
- Gradle 8.x or later

## Getting Started

1. Clone the repository:
   ```bash
   git clone https://github.com/hendisantika/spring-boot-with-postgres-example.git
   cd spring-boot-with-postgres-example
   ```

2. Start the PostgreSQL database and pgAdmin using Docker Compose:
   ```bash
   docker compose up -d
   ```

3. Build and run the application:
   ```bash
   ./gradlew bootRun
   ```

## Database Configuration

The application uses the following database configuration:

- Database Name: employees
- Schema: employees
- Port: 5433 (mapped from container's 5432)

## pgAdmin Access

- URL: http://localhost:5050
- Default Email: admin@pgadmin.org
- Default Password: admin

## API Endpoints

The following REST endpoints are available:

- **GET** `/api/employees` - List all employees
- **GET** `/api/employees/{id}` - Get an employee by ID
- **POST** `/api/employees` - Create a new employee
- **PUT** `/api/employees/{id}` - Update an employee
- **DELETE** `/api/employees/{id}` - Delete an employee

### CURL Examples

#### List all employees

```bash
curl -X GET http://localhost:8080/api/employees
```

#### Get employee by ID

```bash
curl -X GET http://localhost:8080/api/employees/1
```

#### Create new employee

```bash
curl -X POST http://localhost:8080/api/employees \
  -H "Content-Type: application/json" \
  -d '{
    "id": 1,
    "first_name": "John",
    "last_name": "Doe",
    "email": "john.doe@example.com",
    "phone_number": "+1234567890",
    "hire_date": "2024-01-01",
    "salary": 75000,
    "department": "Engineering"
  }'
```

#### Update employee

```bash
curl -X PUT http://localhost:8080/api/employees/1 \
  -H "Content-Type: application/json" \
  -d '{
    "first_name": "John",
    "last_name": "Doe",
    "email": "john.doe@example.com",
    "phone_number": "+1234567890",
    "hire_date": "2024-01-01",
    "salary": 80000,
    "department": "Engineering"
  }'
```

#### Delete employee

```bash
curl -X DELETE http://localhost:8080/api/employees/1
```

#### Response Examples

Successful response when getting an employee:

```json
{
  "id": 1,
   "first_name": "John",
   "last_name": "Doe",
  "email": "john.doe@example.com",
   "phone_number": "+1234567890",
   "hire_date": "2024-01-01",
  "salary": 75000,
  "department": "Engineering"
}
```

## Docker Services

The project includes two Docker services:

1. **PostgreSQL**
   - Container name: postgresDB
   - Version: 17.5-alpine
   - Port: 5433:5432
   - Network: Custom bridge network (172.28.1.2)

2. **pgAdmin**
   - Container name: pgadmin_container
   - Version: 9.4.0
   - Port: 5050:80
   - Network: Custom bridge network (172.28.1.3)

## Project Structure

- `src/main/java/.../controller` - REST controllers
- `src/main/java/.../entity` - Domain entities
- `src/main/java/.../repository` - Data access layer
- `src/main/java/.../service` - Business logic layer
- `src/main/resources/db/migration` - Flyway migration scripts
- `src/test/java/.../controller` - Integration tests for controllers
- `src/test/java/.../config` - Test configuration

## Testing

The project includes integration tests for the REST API endpoints. These tests use TestContainers to spin up a
PostgreSQL container for testing, ensuring that the tests run against a real database environment.

To run the tests:

```bash
./gradlew test
```

The tests verify the CRUD operations for the Employee entity, ensuring that:

- Employees can be created
- Employees can be retrieved by ID
- All employees can be listed
- Employees can be updated
- Employees can be deleted

## Contributing

Feel free to submit issues and enhancement requests.

## License

[MIT License](LICENSE)
