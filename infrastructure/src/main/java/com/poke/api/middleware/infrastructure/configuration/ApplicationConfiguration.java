package com.poke.api.middleware.infrastructure.configuration;

import com.poke.api.middleware.application.PokemonService;
import com.poke.api.middleware.infrastructure.rest.PokemonGatewayImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {
    @Bean
    public PokemonService pokemonService(PokemonGatewayImpl pokemonGateway) {
        return new PokemonService(pokemonGateway);
    }
}
