package com.wit.common.dto;

import java.math.BigDecimal;

public class CalculatorResponse {
    private BigDecimal result;
    private String error;

    public CalculatorResponse(BigDecimal result, String error) {
        this.result = result;
        this.error = error;
    }

    // Getters
    public BigDecimal getResult() {
        return this.result;
    }

    public String getError() {
        return this.error;
    }

    // Setters
    public void setResult(BigDecimal result) {
        this.result = result;
    }

    public void setError(String error) {
        this.error = error;
    }
}
