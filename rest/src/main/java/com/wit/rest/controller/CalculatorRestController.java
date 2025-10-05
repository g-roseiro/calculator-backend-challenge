package com.wit.rest.controller;

import com.wit.common.dto.OperationType;
import com.wit.rest.service.CalculatorRestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wit.common.dto.CalculatorRequest;
import com.wit.common.dto.CalculatorResponse;

import java.math.BigDecimal;

import org.slf4j.Logger;

@RestController
public class CalculatorRestController {

    private static final Logger log = LoggerFactory.getLogger(CalculatorRestController.class);
    private final CalculatorRestService calculatorRestService;

    public CalculatorRestController(CalculatorRestService calculatorRestService) {
        this.calculatorRestService = calculatorRestService;
    }

    @Operation(
            summary = "Adds two numbers.",
            description = "Receives two values (a and b) and returns thei sum.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Sum successfully calculated.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CalculatorResponse.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid input parameters.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CalculatorResponse.class)
                            )
                    )
            }
    )
    @GetMapping(value = "/sum", produces = "application/json")
    public ResponseEntity<CalculatorResponse> sum(
            @Parameter(description = "First operand") @RequestParam("a") BigDecimal a,
            @Parameter(description = "Second operand") @RequestParam("b") BigDecimal b) throws InterruptedException {

        log.info("Received request: /sum with a={}, b={}", a, b);

        String requestId = MDC.get("requestId");
        CalculatorRequest request = new CalculatorRequest(requestId, OperationType.SUM, a, b);
        return handleOperation(request);
    }

    @Operation(
            summary = "Subtracts two numbers.",
            description = "Receives two numbers (a and b) and returns the result of a - b.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Subtraction successfully calculated.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CalculatorResponse.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid input parameters.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CalculatorResponse.class)
                            )
                    )
            }
    )
    @GetMapping(value = "/subtraction", produces = "application/json")
    public ResponseEntity<CalculatorResponse> subtraction(
            @Parameter(description = "First operand") @RequestParam("a") BigDecimal a,
            @Parameter(description = "Second operand") @RequestParam("b") BigDecimal b) throws InterruptedException {

        log.info("Received request: /subtraction with a={}, b={}", a, b);

        String requestId = MDC.get("requestId");
        CalculatorRequest request = new CalculatorRequest(requestId, OperationType.SUBTRACTION, a, b);
        return handleOperation(request);
    }

    @Operation(
            summary = "Multiplies two numbers.",
            description = "Receives two values (a and b) and returns the result of a * b.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Multiplication successfully calculated.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CalculatorResponse.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid input parameters.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CalculatorResponse.class)
                            )
                    )
            }
    )
    @GetMapping(value = "/multiplication", produces = "application/json")
    public ResponseEntity<CalculatorResponse> multiplication(
            @Parameter(description = "First operand") @RequestParam("a") BigDecimal a,
            @Parameter(description = "Second operand") @RequestParam("b") BigDecimal b) throws InterruptedException {

        log.info("Received request: /multiplication with a={}, b={}", a, b);

        String requestId = MDC.get("requestId");
        CalculatorRequest request = new CalculatorRequest(requestId, OperationType.MULTIPLICATION, a, b);
        return handleOperation(request);
    }

    @Operation(
            summary = "Divides two numbers.",
            description = "Receives two values (a and b) and returns the result of a / b. Division by zero is not allowed.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Division successfully calculated.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CalculatorResponse.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid input parameters (e.g., division by zero).",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CalculatorResponse.class)
                            )
                    )
            }
    )
    @GetMapping(value = "/division", produces = "application/json")
    public ResponseEntity<CalculatorResponse> division(
            @Parameter(description = "First operand") @RequestParam("a") BigDecimal a,
            @Parameter(description = "Second operand") @RequestParam("b") BigDecimal b) throws InterruptedException {

        log.info("Received request: /division with a={}, b={}", a, b);

        String requestId = MDC.get("requestId");
        CalculatorRequest request = new CalculatorRequest(requestId, OperationType.DIVISION, a, b);
        return handleOperation(request);
    }

    private ResponseEntity<CalculatorResponse> handleOperation(CalculatorRequest request) throws InterruptedException {
        log.debug("Handling operation: {}", request.getOperation());

        CalculatorResponse response = calculatorRestService.calculate(request);

        if (response == null) {
            log.error("No response received from calculator service for operation {}", request.getOperation());
            return ResponseEntity
                    .status(HttpStatus.GATEWAY_TIMEOUT)
                    .body(new CalculatorResponse(null, "No response received from calculator service."));
        }

        if (response.getError() != null) {
            log.warn("Operation {} completed with error: {}", request.getOperation(), response.getError());
        } else {
            log.info("Operation {} completed successfully. Result={}", request.getOperation(), response.getResult());
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }
}
