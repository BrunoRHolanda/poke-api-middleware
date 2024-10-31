package com.poke.api.middleware.infrastructure.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Record representing the response for basic Pokémon data from an external API.
 * This record is used to deserialize JSON data into a Java object.
 * The {@link JsonIgnoreProperties} annotation ensures that any unknown properties
 * in the JSON response are ignored during the deserialization process.
 *
 * @param id the unique identifier of the Pokémon
 * @param name the name of the Pokémon
 * @param abilities the list of abilities associated with the Pokémon, represented by {@link PokemonAbilityResponse}
 * @param sprites the sprite information of the Pokémon, represented by {@link PokemonSpriteResponse}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record PokemonResponse(
        int id,
        String name,
        List<PokemonAbilityResponse> abilities,
        PokemonSpriteResponse sprites
) {
}
