package com.wit.rest.controller;

import com.wit.common.dto.OperationType;
import com.wit.rest.service.CalculatorRestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wit.common.dto.CalculatorRequest;
import com.wit.common.dto.CalculatorResponse;

import java.math.BigDecimal;
import java.math.RoundingMode;

// /swagger-ui.html documentation included
@RestController
public class CalculatorRestController {

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

        CalculatorRequest request = new CalculatorRequest(OperationType.SUM, a, b);
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

        CalculatorRequest request = new CalculatorRequest(OperationType.SUBTRACTION, a, b);
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

        CalculatorRequest request = new CalculatorRequest(OperationType.MULTIPLICATION, a, b);
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

        CalculatorRequest request = new CalculatorRequest(OperationType.DIVISION, a, b);
        return handleOperation(request);
    }

    private ResponseEntity<CalculatorResponse> handleOperation(CalculatorRequest request) throws InterruptedException {
        CalculatorResponse response = calculatorRestService.calculate(request);
        if (response == null) {
            return ResponseEntity
                    .status(HttpStatus.GATEWAY_TIMEOUT)
                    .body(new CalculatorResponse(null, "No response received from calculator service."));
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }
}
