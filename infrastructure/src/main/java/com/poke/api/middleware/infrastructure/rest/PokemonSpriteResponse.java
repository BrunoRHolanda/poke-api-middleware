package com.poke.api.middleware.infrastructure.rest;

/**
 * Record representing the response for Pokémon sprite information from an external API.
 * This record is used to deserialize JSON data into a Java object.
 *
 * @param front_default the URL of the default front-facing sprite of the Pokémon
 */
public record PokemonSpriteResponse(
        String front_default
) {
}
