package com.poke.api.middleware.infrastructure.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Record representing the response for a Pokémon's ability from an external API.
 * This record is used to deserialize JSON data into a Java object.
 * The {@link JsonIgnoreProperties} annotation ensures that any unknown properties
 * in the JSON response are ignored during the deserialization process.
 *
 * @param ability the detailed value response of the Pokémon's ability
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record PokemonAbilityResponse(
        PokemonAbilityValueResponse ability
) {
}
