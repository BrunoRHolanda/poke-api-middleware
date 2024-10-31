package com.poke.api.middleware.infrastructure.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(title = "Poké API Middleware", version = "1.0.0", description = "API documentation for Poké API Middleware"))
public class SwaggerConfig {
}
