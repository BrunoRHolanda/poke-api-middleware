package com.poke.api.middleware.infrastructure.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Record representing the detailed response for a Pokémon's ability value from an external API.
 * This record is used to deserialize JSON data into a Java object.
 * The {@link JsonIgnoreProperties} annotation ensures that any unknown properties
 * in the JSON response are ignored during the deserialization process.
 *
 * @param name the name of the Pokémon's ability
 * @param url the URL providing more details about the ability
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record PokemonAbilityValueResponse(
        String name,
        String url
) {
}
