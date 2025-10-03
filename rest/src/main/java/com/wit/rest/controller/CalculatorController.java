package com.wit.rest.controller;

import com.wit.common.dto.OperationType;
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
public class CalculatorController {

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
            @Parameter(description = "Second operand") @RequestParam("b") BigDecimal b) {

        CalculatorRequest request = new CalculatorRequest(OperationType.SUM, a, b);
        BigDecimal result = request.getA().add(request.getB());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new CalculatorResponse(result.toPlainString(), null));
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
            @Parameter(description = "Second operand") @RequestParam("b") BigDecimal b) {

        CalculatorRequest request = new CalculatorRequest(OperationType.SUBTRACTION, a, b);
        BigDecimal result = request.getA().subtract(request.getB());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new CalculatorResponse(result.toPlainString(), null));
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
            @Parameter(description = "Second operand") @RequestParam("b") BigDecimal b) {

        CalculatorRequest request = new CalculatorRequest(OperationType.MULTIPLICATION, a, b);
        BigDecimal result = request.getA().multiply(request.getB());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new CalculatorResponse(result.toPlainString(), null));
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
            @Parameter(description = "Second operand") @RequestParam("b") BigDecimal b) {

        CalculatorRequest request = new CalculatorRequest(OperationType.DIVISION, a, b);

        if (request.getB().compareTo(BigDecimal.ZERO) == 0) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new CalculatorResponse(null, "Division by Zero"));
        }

        BigDecimal result = request.getA().divide(request.getB(), 50, RoundingMode.HALF_UP);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new CalculatorResponse(result.toPlainString(), null));
    }
}
