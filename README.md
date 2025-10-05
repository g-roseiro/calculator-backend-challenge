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

### Request Flow

1. **REST receives the HTTP request**  
   Example: `GET /sum?a=2&b=3`  
   The filter adds or propagates the `X-Request-ID` header and stores it in the MDC for logging correlation.


2. **REST → Kafka (request)**  
   The REST module builds a `CalculatorRequest` containing:
    - `operation` (SUM, SUBTRACTION, MULTIPLICATION, DIVISION)
    - `a` and `b` (`BigDecimal` values)
    - `requestId` (same value from `X-Request-ID`)  
      It then sends the request to the Kafka topic **`calculator-requests`**.


3. **Calculator consumes and processes**  
   The Calculator module consumes the message, sets the `requestId` in the MDC, and:
    - Uses the `OperationFactory` to instantiate the correct operation class.
    - Calls `solve()` and **builds a `CalculatorResponse`**:
        - On success: includes `result` (as a string from `toPlainString()`), `error = null`, and `requestId`.
        - On arithmetic error (e.g., division by zero): does not throw the exception outward; instead returns  
          `result = null`, `error = "Division by Zero not allowed."`, and `requestId`.


4. **Calculator → Kafka (response)**  
   The `CalculatorResponse` is published to the Kafka topic **`calculator-responses`**.


5. **REST consumes the response and returns HTTP**
    - If the response arrives within the timeout, REST returns **HTTP 200** with:
        - Header: `X-Request-ID` (same `requestId`)
        - Body (JSON) = `CalculatorResponse`, for example:
          ```json
          { "result": "5", "error": null }
          ```
        - If there was a business error (e.g., division by zero):
          ```json
          { "result": null, "error": "Division by Zero not allowed." }
          ```
    - If no response arrives within the timeout, REST returns **HTTP 504 (Gateway Timeout)**:
        ```json
        { "result": null, "error": "No response received from calculator service." }
        ```
---

## End Notes

**Stop all containers**
```bash
docker compose down
```

**Clean logs**
```bash
rm -rf logs/
```