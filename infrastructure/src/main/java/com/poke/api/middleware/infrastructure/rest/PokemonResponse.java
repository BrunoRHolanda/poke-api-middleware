package com.poke.api.middleware.infrastructure.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record PokemonResponse(
        int id,
        String name,
        List<PokemonAbilityResponse> abilities,
        PokemonSpriteResponse sprites
) {
}
