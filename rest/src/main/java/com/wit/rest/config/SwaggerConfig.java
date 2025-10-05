package com.wit.rest.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                    .title("WIT - Calculator REST API challenge")
                    .version("1.0.0")
                    .description("REST API for sum, subtraction, multiplication and division of two operands.")
                );
    }
}
