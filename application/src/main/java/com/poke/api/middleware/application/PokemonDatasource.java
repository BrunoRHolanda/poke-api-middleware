package com.poke.api.middleware.application;

import com.poke.api.middleware.domain.Pokemon;

import java.util.Optional;

/**
 * Provides an interface for data operations related to {@link Pokemon} entities.
 * This interface defines methods for retrieving and saving Pokémon data,
 * allowing implementations to interact with various data sources such as databases or APIs.
 */
public interface PokemonDatasource {

    /**
     * Finds a {@link Pokemon} by its name.
     *
     * @param name the name of the Pokémon to search for
     * @return an {@link Optional} containing the found {@link Pokemon}, or empty if no Pokémon is found
     */
    Optional<Pokemon> findByName(String name);

    /**
     * Saves a {@link Pokemon} entity to the data source.
     *
     * @param pokemon the {@link Pokemon} entity to be saved
     */
    void save(Pokemon pokemon);
}
