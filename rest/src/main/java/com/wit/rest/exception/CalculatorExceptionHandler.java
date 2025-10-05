package com.wit.rest.exception;

import com.wit.common.dto.CalculatorResponse;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import org.slf4j.Logger;

@ControllerAdvice
public class CalculatorExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(CalculatorExceptionHandler.class);

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<CalculatorResponse> handleOperandTypeMismatch(MethodArgumentTypeMismatchException exception) {
        log.error("Invalid operand: {}", exception.getValue(), exception);
        return ResponseEntity.badRequest()
                .body(new CalculatorResponse(null, "Invalid number: " + exception.getValue()));
    }

    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<CalculatorResponse> handleArithmeticError(ArithmeticException exception) {
        log.warn("Arithmetic error: {}", exception.getMessage());
        return ResponseEntity.badRequest()
                .body(new CalculatorResponse(null, exception.getMessage()));
    }
}
