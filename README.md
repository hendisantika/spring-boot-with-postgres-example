# Spring Boot with PostgreSQL Example

[![Java CI with Gradle](https://github.com/hendisantika/spring-boot-with-postgres-example/actions/workflows/gradle.yml/badge.svg)](https://github.com/hendisantika/spring-boot-with-postgres-example/actions/workflows/gradle.yml)

This project demonstrates a Spring Boot application integrated with PostgreSQL database, featuring employee management
functionality. It includes Docker setup for both PostgreSQL and pgAdmin for easy development and deployment.

## Technologies Used

- Java 25 (Gradle toolchain)
- Spring Boot 4.1.1
- Gradle 9.7.1 (wrapper included)
- PostgreSQL 17.5 (`postgres:17.5-alpine3.22`)
- Flyway for database migrations
- Jackson 3 for JSON serialization
- Lombok
- Testcontainers 1.21.4 for integration tests
- Docker & Docker Compose
- pgAdmin 4 (9.4.0)

## Features

- RESTful API for Employee management
- Database schema migration using Flyway
- Containerized PostgreSQL and pgAdmin setup
- CRUD operations for Employee entity
- Integration tests backed by a real PostgreSQL container

## Prerequisites

- JDK 25
- Docker and Docker Compose

Gradle itself is not required — use the bundled `./gradlew` wrapper.

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

The app depends on `spring-boot-docker-compose`, so running it from an IDE or via `bootRun` will also manage the
`compose.yaml` services and wire the datasource to the running container automatically.

## Database Configuration

The `compose.yaml` PostgreSQL service is configured as:

| Setting  | Value                                          |
|----------|------------------------------------------------|
| Database | `employees`                                    |
| Schema   | `employees`                                    |
| User     | `yu71`                                         |
| Password | `S3cret`                                       |
| Port     | `5433` on the host (mapped to `5432` in the container) |

Flyway creates the `employees` schema and the `employees` table from `src/main/resources/db/migration`.

## pgAdmin Access

- URL: http://localhost:5050
- Default Email: admin@pgadmin.org
- Default Password: admin

Both can be overridden with the `PGADMIN_DEFAULT_EMAIL`, `PGADMIN_DEFAULT_PASSWORD` and `PGADMIN_PORT` environment
variables.

## API Endpoints

The following REST endpoints are available:

- **GET** `/api/employees` - List all employees
- **GET** `/api/employees/{id}` - Get an employee by ID
- **POST** `/api/employees` - Create a new employee
- **PUT** `/api/employees/{id}` - Update an employee
- **DELETE** `/api/employees/{id}` - Delete an employee

### Employee Payload

JSON uses a snake_case naming strategy (`spring.jackson.property-naming-strategy: SNAKE_CASE`). An `Employee` has:

| Field           | Type             |
|-----------------|------------------|
| `id`            | integer (assigned by the client, not generated) |
| `first_name`    | string           |
| `last_name`     | string           |
| `email`         | string           |
| `age`           | integer          |
| `designation`   | string           |
| `phone_number`  | string           |
| `salary`        | number           |
| `department`    | string           |
| `hire_date`     | date (`yyyy-MM-dd`) |
| `address`       | string           |
| `date_of_birth` | date (`yyyy-MM-dd`) |
| `created_at`    | timestamp (set by the server on create) |
| `updated_at`    | timestamp (set by the server on create and update) |

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

Returns `201 Created`.

```bash
curl -X POST http://localhost:8080/api/employees \
  -H "Content-Type: application/json" \
  -d '{
    "id": 1,
    "first_name": "John",
    "last_name": "Doe",
    "email": "john.doe@example.com",
    "age": 30,
    "designation": "Software Engineer",
    "phone_number": "+1234567890",
    "salary": 75000,
    "department": "Engineering",
    "hire_date": "2024-01-01",
    "address": "123 Tech Street",
    "date_of_birth": "1994-01-01"
  }'
```

#### Update employee

The path `{id}` wins over any `id` in the body.

```bash
curl -X PUT http://localhost:8080/api/employees/1 \
  -H "Content-Type: application/json" \
  -d '{
    "first_name": "John",
    "last_name": "Doe",
    "email": "john.doe@example.com",
    "age": 31,
    "designation": "Senior Software Engineer",
    "phone_number": "+1234567890",
    "salary": 80000,
    "department": "Engineering",
    "hire_date": "2024-01-01",
    "address": "123 Tech Street",
    "date_of_birth": "1994-01-01"
  }'
```

#### Delete employee

Returns `204 No Content`.

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
  "age": 30,
  "designation": "Software Engineer",
  "phone_number": "+1234567890",
  "salary": 75000.0,
  "department": "Engineering",
  "hire_date": "2024-01-01",
  "address": "123 Tech Street",
  "date_of_birth": "1994-01-01",
  "created_at": "2024-01-01T09:00:00",
  "updated_at": "2024-01-01T09:00:00"
}
```

A request for an unknown ID returns `404 Not Found` with an empty body.

## Docker Services

The project includes two Docker services:

1. **PostgreSQL**
    - Container name: postgresDB
    - Version: 17.5-alpine3.22
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
PostgreSQL container for testing, ensuring that the tests run against a real database environment. Docker must be
running.

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

## Continuous Integration

`.github/workflows/gradle.yml` runs `./gradlew build` on every push and pull request against `main`, using Temurin
JDK 25 on `ubuntu-latest`. A second job submits the Gradle dependency graph so Dependabot can alert on vulnerable
dependencies.

## Contributing

Feel free to submit issues and enhancement requests.

## License

MIT License.
