package com.poke.api.middleware.application;

import java.util.Objects;

/**
 * Represents the input data structure for a Pokémon entity, containing only its name.
 * This class is a read-only record used to pass data from external sources into the application layer.
 */
public record PokemonInput(
        String name
) {

    /**
     * Constructs a {@code PokemonInput} record with the specified name.
     * Ensures that the {@code name} field is not null.
     *
     * @param name the name of the Pokémon
     * @throws ApplicationException if {@code name} is null
     */
    public PokemonInput {
        try {
            Objects.requireNonNull(
                    name,
                    "Name cannot be null"
            );
        } catch (NullPointerException e) {
            throw new ApplicationException(e.getMessage());
        }
    }

    /**
     * Factory method to create a new {@code PokemonInput} instance.
     *
     * @param name the name of the Pokémon
     * @return a new {@code PokemonInput} instance with the specified name
     */
    public static PokemonInput with(final String name) {
        return new PokemonInput(name);
    }
}
