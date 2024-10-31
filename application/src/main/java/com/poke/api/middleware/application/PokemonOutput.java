package com.poke.api.middleware.application;

import com.poke.api.middleware.domain.Pokemon;

import java.util.List;
import java.util.Objects;

/**
 * Represents the output data structure for a Pokémon, containing its name, sprite, and abilities.
 * This class is a read-only record used to transfer data from the domain layer to other layers.
 */
public record PokemonOutput(
        String name,
        String sprite,
        List<AbilityOutput> abilities
) {

    /**
     * Constructs a {@code PokemonOutput} record with the specified name, sprite, and abilities.
     * Ensures that {@code name}, {@code sprite}, and {@code abilities} are not null.
     *
     * @param name      the name of the Pokémon
     * @param sprite    the sprite image URL or path representing the Pokémon
     * @param abilities the list of {@link AbilityOutput} representing the Pokémon's abilities
     * @throws ApplicationException if {@code name}, {@code sprite}, or {@code abilities} is null
     */
    public PokemonOutput {
        try {
            Objects.requireNonNull(
                    name,
                    "name must not be null"
            );
            Objects.requireNonNull(
                    sprite,
                    "sprite must not be null"
            );
            Objects.requireNonNull(
                    abilities,
                    "abilities must not be null"
            );
        } catch (NullPointerException e) {
            throw new ApplicationException(e.getMessage());
        }
    }

    /**
     * Converts a {@link Pokemon} domain object to a {@code PokemonOutput}.
     *
     * @param pokemon the {@link Pokemon} object to be converted
     * @return a {@code PokemonOutput} instance containing data from the given {@link Pokemon}
     */
    public static PokemonOutput from(Pokemon pokemon) {
        return new PokemonOutput(
                pokemon.getName(),
                pokemon.getSprite(),
                AbilityOutput.from(pokemon.getAbilities())
        );
    }
}
