package com.wit.rest.controller;

import com.wit.common.dto.CalculatorResponse;
import com.wit.rest.service.CalculatorRestService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CalculatorRestController.class)
public class CalculatorRestControllerTest {

    @Autowired
    private MockMvc mockMvc; // Simulate HTTP

    @Autowired
    private CalculatorRestService calculatorRestService; // mock the real service

    @TestConfiguration
    static class TestConfig {
        @Bean
        public CalculatorRestService calculatorRestService() {
            return Mockito.mock(CalculatorRestService.class);
        }
    }

    @Test
    void testSumEndpoint() throws Exception {
        when(calculatorRestService.calculate(any()))
                .thenReturn(new CalculatorResponse("5", null));

        // simulate http request and validates respinse
        mockMvc.perform(get("/sum")
                        .param("a", "2")
                        .param("b", "3"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.result").value("5"))
                .andExpect(jsonPath("$.error").doesNotExist());
    }

    @Test
    void testSubtractionEndpoint() throws Exception {
        when(calculatorRestService.calculate(any()))
                .thenReturn(new CalculatorResponse("3", null));

        mockMvc.perform(get("/subtraction")
                        .param("a", "5")
                        .param("b", "2"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.result").value("3"))
                .andExpect(jsonPath("$.error").doesNotExist());
    }

    @Test
    void testMultiplicationEndpoint() throws Exception {
        when(calculatorRestService.calculate(any()))
                .thenReturn(new CalculatorResponse("10", null));

        mockMvc.perform(get("/multiplication")
                        .param("a", "2")
                        .param("b", "5"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.result").value("10"))
                .andExpect(jsonPath("$.error").doesNotExist());
    }

    @Test
    void testDivisionEndpoint() throws Exception {
        when(calculatorRestService.calculate(any()))
                .thenReturn(new CalculatorResponse("2", null));

        mockMvc.perform(get("/division")
                        .param("a", "10")
                        .param("b", "5"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.result").value("2"))
                .andExpect(jsonPath("$.error").doesNotExist());
    }

    @Test
    void testInvalidOperandType() throws Exception {
        // this should trigger MethodArgumentTypeMismatchException
        mockMvc.perform(get("/sum")
                        .param("a", "x")
                        .param("b", "2"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.result").doesNotExist())
                .andExpect(jsonPath("$.error").value("Invalid number: x"));
    }
}
