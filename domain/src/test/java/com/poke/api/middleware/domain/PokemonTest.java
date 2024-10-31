package com.poke.api.middleware.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link Pokemon} class.
 */
class PokemonTest {

    private Ability ability1;
    private Ability ability2;
    private Ability ability3;

    @BeforeEach
    void setUp() {
        ability1 = Ability.from(1, "Blaze", "Boosts fire-type moves");
        ability2 = Ability.from(2, "Overgrow", "Boosts grass-type moves");
        ability3 = Ability.from(3, "Torrent", "Boosts water-type moves");
    }

    /**
     * Test successful creation of a Pokemon object.
     */
    @Test
    void testPokemonCreation() {
        List<Ability> abilities = Arrays.asList(ability1, ability2);
        Pokemon pokemon = Pokemon.from(1, "Charizard", "charizard_sprite.png", abilities);

        assertNotNull(pokemon);
        assertEquals("Charizard", pokemon.getName());
        assertEquals("charizard_sprite.png", pokemon.getSprite());
        assertEquals(abilities, pokemon.getAbilities());
    }

    /**
     * Test validation throws DomainValidationException when name is null.
     */
    @Test
    void testValidationThrowsExceptionWhenNameIsNull() {
        List<Ability> abilities = Collections.singletonList(ability1);
        DomainValidationException exception = assertThrows(DomainValidationException.class, () -> {
            Pokemon pokemon = Pokemon.from(1, null, "sprite.png", abilities);
            pokemon.validate();
        });
        assertEquals("Name cannot be null", exception.getMessage());
    }

    /**
     * Test validation throws DomainValidationException when sprite is null.
     */
    @Test
    void testValidationThrowsExceptionWhenSpriteIsNull() {
        List<Ability> abilities = Collections.singletonList(ability1);
        DomainValidationException exception = assertThrows(DomainValidationException.class, () -> {
            Pokemon pokemon = Pokemon.from(1, "Charizard", null, abilities);
            pokemon.validate();
        });
        assertEquals("Sprite cannot be null", exception.getMessage());
    }

    /**
     * Test validation throws DomainValidationException when abilities list is null.
     */
    @Test
    void testValidationThrowsExceptionWhenAbilitiesIsNull() {
        DomainValidationException exception = assertThrows(DomainValidationException.class, () -> {
            Pokemon pokemon = Pokemon.from(1, "Charizard", "sprite.png", null);
            pokemon.validate();
        });
        assertEquals("Abilities cannot be null", exception.getMessage());
    }

    /**
     * Test that the abilities list is sorted alphabetically by name.
     */
    @Test
    void testSortAbilities() {
        List<Ability> abilities = Arrays.asList(ability2, ability1, ability3);
        Pokemon pokemon = Pokemon.from(1, "Charizard", "charizard_sprite.png", abilities);

        pokemon.sortAbilities();
        assertEquals(ability1, pokemon.getAbilities().get(0));  // Blaze should be first
        assertEquals(ability2, pokemon.getAbilities().get(1));  // Overgrow should be second
        assertEquals(ability3, pokemon.getAbilities().get(2));  // Torrent should be third
    }

    /**
     * Test that two Pokemon with the same name, sprite, and abilities are considered equal.
     */
    @Test
    void testPokemonEquality() {
        List<Ability> abilities = Arrays.asList(ability1, ability2);

        Pokemon pokemon1 = Pokemon.from(1, "Charizard", "charizard_sprite.png", abilities);
        Pokemon pokemon2 = Pokemon.from(1, "Charizard", "charizard_sprite.png", abilities);

        assertTrue(pokemon1.equals(pokemon2));
    }

    /**
     * Test that two Pokemon with different names or sprites or abilities are not equal.
     */
    @Test
    void testPokemonInequality() {
        List<Ability> abilities1 = Arrays.asList(ability1, ability2);
        List<Ability> abilities2 = Arrays.asList(ability1, ability3);

        Pokemon pokemon1 = Pokemon.from(1, "Charizard", "charizard_sprite.png", abilities1);
        Pokemon pokemon2 = Pokemon.from(2, "Blastoise", "blastoise_sprite.png", abilities2);

        assertNotEquals(pokemon1, pokemon2);
    }

    /**
     * Test the toString method returns the expected string representation of the Pokemon.
     */
    @Test
    void testToString() {
        List<Ability> abilities = Arrays.asList(ability1, ability2);
        Pokemon pokemon = Pokemon.from(1, "Charizard", "charizard_sprite.png", abilities);
        String expected = "Pokemon{id=1, name='Charizard', sprite='charizard_sprite.png', abilities=[Ability{name='Blaze', effect='Boosts fire-type moves'}, Ability{name='Overgrow', effect='Boosts grass-type moves'}]}";
        assertEquals(expected, pokemon.toString());
    }

    /**
     * Test the hashCode method ensures that two Pokemon objects with the same properties have the same hash code.
     */
    @Test
    void testHashCode() {
        List<Ability> abilities = Arrays.asList(ability1, ability2);

        Pokemon pokemon1 = Pokemon.from(1, "Charizard", "charizard_sprite.png", abilities);
        Pokemon pokemon2 = Pokemon.from(1, "Charizard", "charizard_sprite.png", abilities);

        assertEquals(pokemon1.hashCode(), pokemon2.hashCode());
    }
}
