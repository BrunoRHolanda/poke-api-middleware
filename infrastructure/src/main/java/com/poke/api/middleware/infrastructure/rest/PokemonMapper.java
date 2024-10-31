package com.poke.api.middleware.infrastructure.rest;

import com.poke.api.middleware.domain.Pokemon;

import java.util.List;

public class PokemonMapper {
    public static Pokemon map(
            PokemonResponse pokemonResponse,
            List<AbilityResponse> abilityResponseList
    ) {
        return Pokemon.from(
                pokemonResponse.id(),
                pokemonResponse.name(),
                pokemonResponse.sprites()
                               .front_default(),
                AbilityMapper.map(abilityResponseList)
        );
    }
}
