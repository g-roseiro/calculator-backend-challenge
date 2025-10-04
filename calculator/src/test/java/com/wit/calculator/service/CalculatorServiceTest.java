package com.wit.calculator.service;

import com.wit.calculator.operation.IOperation;
import com.wit.calculator.operation.OperationFactory;
import com.wit.common.dto.CalculatorRequest;
import com.wit.common.dto.CalculatorResponse;
import com.wit.common.dto.OperationType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CalculatorServiceTest {
    // Dependencies to mock
    private OperationFactory operationFactoryMock;
    private IOperation operationMock;
    private CalculatorService calculatorService;

    @BeforeEach
    void setUp() {
        // create mocks
        operationFactoryMock = Mockito.mock(OperationFactory.class);
        operationMock = Mockito.mock(IOperation.class);
        // inject mocked factory into real service
        calculatorService = new CalculatorService(operationFactoryMock);
    }

    @Test
    void calculatorExpectedResponse() {
        CalculatorRequest request = new CalculatorRequest(
                OperationType.SUM,
                new BigDecimal("2"),
                new BigDecimal("3")
        );
        // Test IOperation returned from mocked factoryu
        when(operationFactoryMock.createOperation(request)).thenReturn(operationMock);

        // Teste solve()
        when(operationMock.solve()).thenReturn(new BigDecimal("5"));

        CalculatorResponse response = calculatorService.calculate(request);

        // Assert both fields (result & error)
        assertEquals("5", response.getResult());
        assertNull(response.getError());

        // Verify number of method's calls
        verify(operationFactoryMock, times(1)).createOperation(request);
        verify(operationMock, times(1)).solve();
    }

    @Test
    void calculatorExceptionDivisionByZeroResponse() {
        CalculatorRequest request = new CalculatorRequest(
                OperationType.DIVISION,
                new BigDecimal("2"),
                BigDecimal.ZERO
        );

        when(operationFactoryMock.createOperation(request)).thenReturn(operationMock);
        when(operationMock.solve()).thenThrow(new ArithmeticException("Division by Zero not allowed."));

        assertThrows(ArithmeticException.class, () -> calculatorService.calculate(request));

        verify(operationFactoryMock, times(1)).createOperation(request);
        verify(operationMock, times(1)).solve();
    }

}
