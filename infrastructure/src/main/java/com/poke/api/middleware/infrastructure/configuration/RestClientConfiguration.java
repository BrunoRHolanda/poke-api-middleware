package com.poke.api.middleware.infrastructure.configuration;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Configuration class for setting up RestTemplate beans in the application.
 * This class provides a centralized way to create and configure {@link RestTemplate}
 * instances used for making HTTP requests.
 */
@Configuration
public class RestClientConfiguration {

    /**
     * Creates and configures a {@link RestTemplate} bean using the provided {@link RestTemplateBuilder}.
     * The builder allows customization of the {@link RestTemplate} before building it.
     *
     * @param builder the {@link RestTemplateBuilder} used for creating the {@link RestTemplate}
     * @return a configured {@link RestTemplate} instance
     */
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }
}
