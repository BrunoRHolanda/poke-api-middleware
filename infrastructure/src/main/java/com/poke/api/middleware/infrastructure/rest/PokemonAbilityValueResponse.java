package com.poke.api.middleware.infrastructure.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record PokemonAbilityValueResponse(
        String name,
        String url
) {
}
