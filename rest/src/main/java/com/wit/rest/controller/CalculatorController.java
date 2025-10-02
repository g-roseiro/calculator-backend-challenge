package com.wit.rest.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wit.common.dto.CalculatorRequest;
import com.wit.common.dto.CalculatorResponse;
import com.wit.common.dto.Operation;

import java.math.BigDecimal;
import java.math.RoundingMode;

@RestController
public class CalculatorController {

    @GetMapping("/sum")
    public ResponseEntity<CalculatorResponse> sum(@RequestParam("a") BigDecimal a, @RequestParam("b") BigDecimal b) {
        CalculatorRequest request = new CalculatorRequest(Operation.SUM, a, b);
        BigDecimal result = request.getA().add(request.getB());
        return ResponseEntity
                .ok(new CalculatorResponse(result.toPlainString(), null));
    }

    @GetMapping("/subtraction")
    public ResponseEntity<CalculatorResponse> subtraction(@RequestParam("a") BigDecimal a, @RequestParam("b") BigDecimal b) {
        CalculatorRequest request = new CalculatorRequest(Operation.SUBTRACTION, a, b);
        BigDecimal result = request.getA().subtract(request.getB());
        return ResponseEntity
                .ok(new CalculatorResponse(result.toPlainString(), null));
    }

    @GetMapping("/multiplication")
    public ResponseEntity<CalculatorResponse> multiplication(@RequestParam("a") BigDecimal a, @RequestParam("b") BigDecimal b) {
        CalculatorRequest request = new CalculatorRequest(Operation.MULTIPLICATION, a, b);
        BigDecimal result = request.getA().multiply(request.getB());
        return ResponseEntity
                .ok(new CalculatorResponse(result.toPlainString(), null));
    }

    @GetMapping("/division")
    public ResponseEntity<CalculatorResponse> division(@RequestParam("a") BigDecimal a, @RequestParam("b") BigDecimal b) {
        CalculatorRequest request = new CalculatorRequest(Operation.DIVISION, a, b);

        if (request.getB().compareTo(BigDecimal.ZERO) == 0) {
            return ResponseEntity.badRequest()
                    .body(new CalculatorResponse(null, "Division by Zero"));
        }

        BigDecimal result = request.getA().divide(request.getB(), 50, RoundingMode.HALF_UP);
        return ResponseEntity
                .ok(new CalculatorResponse(result.toPlainString(), null));
    }
}
