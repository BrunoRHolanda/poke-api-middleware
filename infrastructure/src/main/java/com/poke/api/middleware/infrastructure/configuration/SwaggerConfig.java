package com.poke.api.middleware.infrastructure.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for setting up Swagger/OpenAPI documentation for the application.
 * This class is annotated with {@link OpenAPIDefinition} to provide metadata for the generated API documentation.
 */
@Configuration
@OpenAPIDefinition(info = @Info(
        title = "Poké API Middleware",
        version = "1.0.0",
        description = "API documentation for Poké API Middleware"
))
public class SwaggerConfig {
    // This class serves as a configuration entry point for Swagger/OpenAPI.
}
