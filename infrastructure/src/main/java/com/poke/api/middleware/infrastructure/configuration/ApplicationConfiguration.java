package com.poke.api.middleware.infrastructure.configuration;

import com.poke.api.middleware.application.PokemonService;
import com.poke.api.middleware.infrastructure.rest.PokemonGatewayImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for setting up the application's beans.
 * This class defines and manages the creation of beans required for
 * the application's core services.
 */
@Configuration
public class ApplicationConfiguration {

    /**
     * Creates a {@link PokemonService} bean with the provided {@link PokemonGatewayImpl} dependency.
     *
     * @param pokemonGateway the implementation of {@link PokemonGatewayImpl} used for service operations
     * @return a new instance of {@link PokemonService}
     */
    @Bean
    public PokemonService pokemonService(PokemonGatewayImpl pokemonGateway) {
        return new PokemonService(pokemonGateway);
    }
}
