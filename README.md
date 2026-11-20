# Countries and Cities API

A Spring Boot (Java 21) REST service exposing endpoints to list countries, retrieve paginated cities for a country, and fetch detailed information for a city.

## Prerequisites
- Java 21
- Maven 3.9+
- Git

## Run the Application
```bash
mvn spring-boot:run
```

## OpenAPI / Swagger UI
Once running on `http://localhost:8080`:
- **Swagger UI:** `http://localhost:8080/swagger-ui.html`
- **OpenAPI JSON:** `http://localhost:8080/v3/api-docs`
- **Contract-First Spec:** `src/main/resources/openapi/countries-cities-api.yaml`

## Sample Requests
- `GET http://localhost:8080/countries`
- `GET http://localhost:8080/countries/1/cities?page=0&size=10`
- `GET http://localhost:8080/cities/101`

## Design Considerations & Assumptions
- **Contract-First Approach:** The OpenAPI 3.0.3 specification is defined in `src/main/resources/openapi/countries-cities-api.yaml` and mirrored with `springdoc-openapi` annotations.
- **In-Memory Storage:** Seeded in-memory collections (`InMemoryGeoRepository`) are used for fast, self-contained local execution without requiring external database setup.
- **Pagination Metadata:** `PagedResponse<T>` returns `totalRecords`, `currentPage`, `pageSize`, and `totalPages` alongside `items` (zero-based `page` default `0`, `size` default `10`).
- **Error Handling:** `GlobalExceptionHandler` returns consistent `ErrorResponse` payloads (`404 Not Found` and `400 Bad Request`).
