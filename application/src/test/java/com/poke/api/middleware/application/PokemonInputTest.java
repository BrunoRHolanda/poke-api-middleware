package com.poke.api.middleware.application;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link PokemonInput} class.
 */
class PokemonInputTest {

    /**
     * Test successful creation of a PokemonInput with a valid name.
     */
    @Test
    void testPokemonInputCreation() {
        PokemonInput input = new PokemonInput("Pikachu");
        assertNotNull(input);
        assertEquals("Pikachu", input.name());
    }

    /**
     * Test that an ApplicationException is thrown when name is null.
     */
    @Test
    void testPokemonInputThrowsExceptionWhenNameIsNull() {
        Exception exception = assertThrows(ApplicationException.class, () -> {
            new PokemonInput(null);
        });
        assertEquals("Name cannot be null", exception.getMessage());
    }

    /**
     * Test that the factory method `with` correctly creates a new PokemonInput instance.
     */
    @Test
    void testWithFactoryMethod() {
        PokemonInput input = PokemonInput.with("Charmander");
        assertNotNull(input);
        assertEquals("Charmander", input.name());
    }
}
