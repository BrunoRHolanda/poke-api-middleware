package com.poke.api.middleware.infrastructure.rest;

import com.poke.api.middleware.domain.Pokemon;

import java.util.List;

/**
 * Utility class for mapping data from {@link PokemonResponse} objects to {@link Pokemon} domain objects.
 * This class helps transform the response from the external API into a format suitable for use within the application.
 */
public class PokemonMapper {

    /**
     * Maps a {@link PokemonResponse} object and a list of {@link AbilityResponse} objects to a {@link Pokemon} domain object.
     *
     * @param pokemonResponse the {@link PokemonResponse} object containing basic Pokémon data
     * @param abilityResponseList the list of {@link AbilityResponse} objects representing the abilities of the Pokémon
     * @return a {@link Pokemon} object created from the provided response and ability data
     */
    public static Pokemon map(
            PokemonResponse pokemonResponse,
            List<AbilityResponse> abilityResponseList
    ) {
        return Pokemon.from(
                pokemonResponse.id(),
                pokemonResponse.name(),
                pokemonResponse.sprites().front_default(),
                AbilityMapper.map(abilityResponseList)
        );
    }
}
