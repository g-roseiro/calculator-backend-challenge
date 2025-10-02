package com.wit.common.dto;

public class CalculatorResponse {
    private String result;
    private String error;

    public CalculatorResponse(String result, String error) {
        this.result = result;
        this.error = error;
    }

    // Getters
    public String getResult() {
        return this.result;
    }

    public String getError() {
        return this.error;
    }

    // Setters
    public void setResult(String result) {
        this.result = result;
    }

    public void setError(String error) {
        this.error = error;
    }
}
