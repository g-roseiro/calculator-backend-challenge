package com.wit.rest.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.MDC; // Mapped Diagnostic Context
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

/**
 * Intercepts every HTTP request before it reaches the Controller layer.
 * Generates or retrieves a unique Request ID, stores it in the MDC for logging,
 * and attaches it to the HTTP response header (X-Request-ID).
 */
@Component
@Order(1) // FIlter order on chain
public class RequestIdFilter extends OncePerRequestFilter {

    // Name of the HTTP header that will carry the unique request identifier
    private static final String REQUEST_ID_HEADER = "X-Request-ID";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, jakarta.servlet.ServletException {

        // Try to read an existing Request ID from incoming headers
        String requestId = request.getHeader(REQUEST_ID_HEADER);

        // If not provided by the client, generate a new one
        if (requestId == null || requestId.isEmpty()) {
            requestId = UUID.randomUUID().toString();
        }

        // Store the Request ID in the MDC (Mapped Diagnostic Context)
        MDC.put("requestId", requestId);
        // Attach the Request ID to the HTTP response header
        response.setHeader(REQUEST_ID_HEADER, requestId);

        try {
            // Continue the normal filter chain (go to next filter or controller layer)
            filterChain.doFilter(request, response);
        } finally {
            MDC.clear();
        }
    }
}