package com.wit.common.dto;

import java.math.BigDecimal;

public class CalculatorRequest {

    private String requestId;
    private OperationType operationType;
    private BigDecimal a;
    private BigDecimal b;

    public CalculatorRequest(String requestId,OperationType operationType, BigDecimal a, BigDecimal b) {
        this.requestId = requestId;
        this.operationType = operationType;
        this.a = a;
        this.b = b;
    }

    // Beacause of Jackson Deserializationå
    public CalculatorRequest() {
    }

    // Getters
    public String getRequestId() {return this.requestId;};

    public OperationType getOperation() {
        return this.operationType;
    }

    public BigDecimal getA() {
        return this.a;
    }

    public BigDecimal getB() {
        return this.b;
    }

    // Setters
    public void setRequestId(String requestId) { this.requestId = requestId;}

    public void setOperation(OperationType operationType) {
        this.operationType = operationType;
    }

    public void setA(BigDecimal a) {
        this.a = a;
    }

    public void setB(BigDecimal b) {
        this.b = b;
    }

}
