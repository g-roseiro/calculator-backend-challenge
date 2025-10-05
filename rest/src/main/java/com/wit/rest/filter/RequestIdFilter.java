package com.wit.rest.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.MDC; // Mapped Diagnostic Context
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;

// Intercepts the Http Request to give him a UUID before reaches the controller layer
@Component
public class RequestIdFilter implements Filter {

    // Name of the HTTP header that will carry the unique request identifier
    // When the filter runs: httpResponse.addHeader("X-Request-ID", requestId);
    private static final String REQUEST_ID_HEADER = "X-Request-ID";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        // Cast the generic request/response to HTTP-specific objects
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String requestId = httpRequest.getHeader(REQUEST_ID_HEADER);

        // If no request ID exists, generate a new one
        if (requestId == null || requestId.isEmpty()) {
            requestId = UUID.randomUUID().toString();
        }

        MDC.put("requestId", requestId); // // Store the requestId in Mapped Diagnostic Context (MDC)

        try {
            // normal filter chain flow (pass control to the next filter or controller)
            chain.doFilter(request, response);
        } finally {
            httpResponse.addHeader(REQUEST_ID_HEADER, requestId);
            MDC.clear();
        }
    }
}