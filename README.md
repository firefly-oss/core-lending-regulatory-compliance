# Core Lending Regulatory Compliance

A microservice component of the Firefly platform that handles regulatory compliance and reporting for lending operations.

## Overview

The Core Lending Regulatory Compliance service is responsible for managing regulatory compliance requirements for lending operations, including:

- Anti-Money Laundering (AML) case management
- Suspicious Activity Reports (SAR) processing
- Regulatory reporting and submissions
- Compliance record management

This service provides a reactive API built on Spring WebFlux for efficient handling of compliance-related operations.

## Architecture

This microservice follows a modular architecture with clear separation of concerns:

```
core-lending-regulatory-compliance
├── core-lending-regulatory-compliance-core       # Business logic and service implementations
├── core-lending-regulatory-compliance-interfaces # DTOs and public interfaces
├── core-lending-regulatory-compliance-models     # Data models and repositories
└── core-lending-regulatory-compliance-web        # REST API controllers and application entry point
```

### Technology Stack

- **Java 21**: Utilizing the latest Java features including virtual threads
- **Spring Boot**: Application framework
- **Spring WebFlux**: Reactive web framework
- **R2DBC**: Reactive database connectivity
- **PostgreSQL**: Database
- **Flyway**: Database migration
- **OpenAPI/Swagger**: API documentation
- **Maven**: Build tool

## Module Structure

### core-lending-regulatory-compliance-interfaces

Contains Data Transfer Objects (DTOs) and public interfaces that define the contract for the service. This module has minimal dependencies and can be shared with client applications.

### core-lending-regulatory-compliance-models

Contains database entity models and repository interfaces. This module handles data persistence using R2DBC for reactive database operations.

### core-lending-regulatory-compliance-core

Contains the business logic and service implementations. This module implements the interfaces defined in the interfaces module and uses the repositories from the models module.

### core-lending-regulatory-compliance-web

Contains the REST API controllers and the application entry point. This module exposes the functionality implemented in the core module through RESTful endpoints.

## API Endpoints

The service provides the following main API groups:

### AML (Anti-Money Laundering)

- `/api/v1/aml-cases`: CRUD operations for AML cases
- `/api/v1/aml-actions`: CRUD operations for AML actions
- `/api/v1/aml-sars`: CRUD operations for Suspicious Activity Reports

### Regulatory Reporting

- `/api/v1/reporting-runs`: CRUD operations for reporting runs
- `/api/v1/reporting-records`: CRUD operations for reporting records
- `/api/v1/regulatory-submissions`: CRUD operations for regulatory submissions

## Setup and Installation

### Prerequisites

- Java 21 or higher
- Maven 3.8 or higher
- PostgreSQL 14 or higher

### Environment Variables

The following environment variables need to be set:

```
DB_HOST=localhost
DB_PORT=5432
DB_NAME=regulatory_compliance
DB_USERNAME=postgres
DB_PASSWORD=your_password
DB_SSL_MODE=disable
```

### Building the Application

```bash
mvn clean install
```

### Running the Application

```bash
mvn spring-boot:run -pl core-lending-regulatory-compliance-web
```

Or using the JAR file:

```bash
java -jar core-lending-regulatory-compliance-web/target/core-lending-regulatory-compliance-web-1.0.0-SNAPSHOT.jar
```

### Docker

A Dockerfile is provided to build and run the application in a container:

```bash
# Build the Docker image
docker build -t core-lending-regulatory-compliance .

# Run the container
docker run -p 8080:8080 \
  -e DB_HOST=host.docker.internal \
  -e DB_PORT=5432 \
  -e DB_NAME=regulatory_compliance \
  -e DB_USERNAME=postgres \
  -e DB_PASSWORD=your_password \
  -e DB_SSL_MODE=disable \
  core-lending-regulatory-compliance
```

## Configuration

The application uses Spring profiles for different environments:

- `dev`: Development environment with detailed logging
- `testing`: Testing environment
- `prod`: Production environment with minimal logging and disabled Swagger UI

Configuration is managed through `application.yaml` in the web module.

## API Documentation

API documentation is available through Swagger UI when running in development or testing mode:

```
http://localhost:8080/swagger-ui.html
```

The OpenAPI specification is available at:

```
http://localhost:8080/v3/api-docs
```

## Health Checks and Monitoring

The application exposes the following actuator endpoints:

- Health: `http://localhost:8080/actuator/health`
- Info: `http://localhost:8080/actuator/info`
- Prometheus metrics: `http://localhost:8080/actuator/prometheus`

## Development Guidelines

### Code Style

This project follows the standard Java code style with Google's Java Style Guide.

### Testing

- Unit tests should be written for all service implementations
- Integration tests should be written for repositories and controllers
- Tests can be run with: `mvn test`

### Database Migrations

Database migrations are managed with Flyway. Migration scripts are located in:

```
core-lending-regulatory-compliance-models/src/main/resources/db/migration
```

New migrations should follow the naming convention: `V{version}__{description}.sql`