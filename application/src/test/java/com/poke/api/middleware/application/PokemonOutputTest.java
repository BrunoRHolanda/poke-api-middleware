package com.poke.api.middleware.application;

import com.poke.api.middleware.domain.Ability;
import com.poke.api.middleware.domain.Pokemon;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link PokemonOutput} class.
 */
class PokemonOutputTest {

    /**
     * Test successful creation of a PokemonOutput with valid data.
     */
    @Test
    void testPokemonOutputCreation() {
        AbilityOutput abilityOutput = new AbilityOutput(
                1,
                "Overgrow",
                "Boosts grass-type moves"
        );
        PokemonOutput pokemonOutput = new PokemonOutput(
                "Bulbasaur",
                "bulbasaur_sprite.png",
                List.of(abilityOutput)
        );

        assertNotNull(pokemonOutput);
        assertEquals(
                "Bulbasaur",
                pokemonOutput.name()
        );
        assertEquals(
                "bulbasaur_sprite.png",
                pokemonOutput.sprite()
        );
        assertEquals(
                1,
                pokemonOutput.abilities()
                             .size()
        );
        assertEquals(
                "Overgrow",
                pokemonOutput.abilities()
                             .get(0)
                             .name()
        );
    }

    /**
     * Test that an ApplicationException is thrown when name is null.
     */
    @Test
    void testPokemonOutputThrowsExceptionWhenNameIsNull() {
        AbilityOutput abilityOutput = new AbilityOutput(
                1,
                "Overgrow",
                "Boosts grass-type moves"
        );

        Exception exception = assertThrows(
                ApplicationException.class,
                () -> {
                    new PokemonOutput(
                            null,
                            "bulbasaur_sprite.png",
                            List.of(abilityOutput)
                    );
                }
        );
        assertEquals(
                "name must not be null",
                exception.getMessage()
        );
    }

    /**
     * Test that an ApplicationException is thrown when sprite is null.
     */
    @Test
    void testPokemonOutputThrowsExceptionWhenSpriteIsNull() {
        AbilityOutput abilityOutput = new AbilityOutput(
                1,
                "Overgrow",
                "Boosts grass-type moves"
        );

        Exception exception = assertThrows(
                ApplicationException.class,
                () -> {
                    new PokemonOutput(
                            "Bulbasaur",
                            null,
                            List.of(abilityOutput)
                    );
                }
        );
        assertEquals(
                "sprite must not be null",
                exception.getMessage()
        );
    }

    /**
     * Test that an ApplicationException is thrown when abilities list is null.
     */
    @Test
    void testPokemonOutputThrowsExceptionWhenAbilitiesIsNull() {
        Exception exception = assertThrows(
                ApplicationException.class,
                () -> {
                    new PokemonOutput(
                            "Bulbasaur",
                            "bulbasaur_sprite.png",
                            null
                    );
                }
        );
        assertEquals(
                "abilities must not be null",
                exception.getMessage()
        );
    }

    /**
     * Test that the from(Pokemon) method correctly converts a Pokemon to a PokemonOutput.
     */
    @Test
    void testFromPokemon() {
        Ability ability = Ability.from(
                1,
                "Overgrow",
                "Boosts grass-type moves"
        );
        Pokemon pokemon = Pokemon.from(
                1,
                "Bulbasaur",
                "bulbasaur_sprite.png",
                List.of(ability)
        );

        PokemonOutput pokemonOutput = PokemonOutput.from(pokemon);

        assertEquals(
                pokemon.getName(),
                pokemonOutput.name()
        );
        assertEquals(
                pokemon.getSprite(),
                pokemonOutput.sprite()
        );
        assertEquals(
                1,
                pokemonOutput.abilities()
                             .size()
        );
        assertEquals(
                "Overgrow",
                pokemonOutput.abilities()
                             .get(0)
                             .name()
        );
    }
}
