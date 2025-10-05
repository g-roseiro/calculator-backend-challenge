# Calculator Backend Challenge

## How to Run the Project

1. **Build all modules and generate the JAR files**

   ```bash
   mvn clean package
   ```

2. **Start all services using Docker Compose**

   ```bash
   docker compose up --build
   ```

   Make sure Docker Desktop is running before executing this command.

3. **Access the REST API documentation**

    - Open [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
    - Or send HTTP requests manually using Postman or cURL:
        - `GET /sum?a=2&b=3`
        - `GET /subtraction?a=5&b=2`
        - `GET /multiplication?a=4&b=6`
        - `GET /division?a=10&b=2`

---

# Architecture Overview

The system is divided into three independent modules, connected through Apache Kafka and orchestrated via Docker Compose:

| Module | Description |
|--------|--------------|
| **common** | Shared DTOs, enums and Kafka topic names used by both services. |
| **calculator** | Performs the mathematical operations. Listens for Kafka requests and returns results. |
| **rest** | Exposes the REST API, sends calculation requests to Kafka, and waits for responses. |

### Technologies Used

- Java 21 + Spring Boot 3.3
- Apache Kafka (Confluent 7.5)
- JUnit 5 + Mockito for testing
- SLF4J + Logback for logging
- Springdoc OpenAPI 2 for Swagger UI

---

## Request Flow

1. The REST module receives a request such as:
   ```
   GET /sum?a=2&b=3
   ```
2. It builds a `CalculatorRequest` and sends it to the Kafka topic `calculator-requests`.
3. The Calculator module consumes the request, creates the correct operation (SUM, SUBTRACTION, etc.) using the `OperationFactory`, and computes the result.
4. The result is sent back through the `calculator-responses` Kafka topic.
5. The REST module listens to this topic and returns the response as JSON to the HTTP client.

---

## Notes

**Stop all containers**
```bash
docker compose down
```

**Clean logs**
```bash
rm -rf logs/
```