package com.poke.api.middleware.application;

import com.poke.api.middleware.domain.Pokemon;

import java.util.Optional;

/**
 * Defines an interface for accessing {@link Pokemon} data from an external source or service.
 * The primary purpose of this interface is to abstract the retrieval of Pokémon data,
 * allowing different implementations to fetch data from various sources, such as external APIs.
 */
public interface PokemonGateway {

    /**
     * Finds a {@link Pokemon} by its name.
     *
     * @param name the name of the Pokémon to search for
     * @return an {@link Optional} containing the found {@link Pokemon}, or empty if no Pokémon is found
     */
    Optional<Pokemon> findByName(String name);
}
