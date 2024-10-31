package com.poke.api.middleware.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link Ability} class.
 */
class AbilityTest {

    /**
     * Test for successful creation of an Ability object.
     */
    @Test
    void testAbilityCreation() {
        Ability ability = Ability.from(
                1,
                "Overgrow",
                "Boosts grass-type moves"
        );
        assertNotNull(ability);
        assertEquals(
                "Overgrow",
                ability.getName()
        );
        assertEquals(
                "Boosts grass-type moves",
                ability.getEffect()
        );
    }

    /**
     * Test for validation when name is null, expecting DomainValidationException.
     */
    @Test
    void testValidateThrowsExceptionWhenNameIsNull() {
        DomainValidationException exception = assertThrows(
                DomainValidationException.class,
                () -> {
                    Ability ability = Ability.from(
                            1,
                            null,
                            "Boosts grass-type moves"
                    );
                    ability.validate();
                }
        );
        assertEquals(
                "Name is required",
                exception.getMessage()
        );
    }

    /**
     * Test for validation when effect is null, expecting DomainValidationException.
     */
    @Test
    void testValidateThrowsExceptionWhenEffectIsNull() {
        DomainValidationException exception = assertThrows(
                DomainValidationException.class,
                () -> {
                    Ability ability = Ability.from(
                            1,
                            "Overgrow",
                            null
                    );
                    ability.validate();
                }
        );
        assertEquals(
                "Effect is required",
                exception.getMessage()
        );
    }

    /**
     * Test that two Ability objects with the same name and effect are considered equal.
     */
    @Test
    void testAbilitiesAreEqual() {
        Ability ability1 = Ability.from(
                1,
                "Overgrow",
                "Boosts grass-type moves"
        );
        Ability ability2 = Ability.from(
                2,
                "Overgrow",
                "Boosts grass-type moves"
        );
        assertEquals(
                ability1,
                ability2
        );
    }

    /**
     * Test that two Ability objects with different names or effects are not considered equal.
     */
    @Test
    void testAbilitiesAreNotEqual() {
        Ability ability1 = Ability.from(
                1,
                "Overgrow",
                "Boosts grass-type moves"
        );
        Ability ability2 = Ability.from(
                2,
                "Blaze",
                "Boosts fire-type moves"
        );
        assertNotEquals(
                ability1,
                ability2
        );
    }

    /**
     * Test the compareTo method to ensure abilities are compared based on name.
     */
    @Test
    void testCompareTo() {
        Ability ability1 = Ability.from(
                1,
                "Blaze",
                "Boosts fire-type moves"
        );
        Ability ability2 = Ability.from(
                2,
                "Overgrow",
                "Boosts grass-type moves"
        );

        assertTrue(ability1.compareTo(ability2) < 0);  // "Blaze" < "Overgrow"
        assertTrue(ability2.compareTo(ability1) > 0);  // "Overgrow" > "Blaze"
        assertEquals(
                0,
                ability1.compareTo(Ability.from(3,
                        "Blaze",
                        "Another effect"
                ))
        );  // "Blaze" == "Blaze"
    }

    /**
     * Test the toString method to ensure the string representation is as expected.
     */
    @Test
    void testToString() {
        Ability ability = Ability.from(
                1,
                "Overgrow",
                "Boosts grass-type moves"
        );
        String expected = "Ability{name='Overgrow', effect='Boosts grass-type moves'}";
        assertEquals(
                expected,
                ability.toString()
        );
    }

    /**
     * Test the hashCode method to ensure that abilities with the same properties have the same hash code.
     */
    @Test
    void testHashCode() {
        Ability ability1 = Ability.from(
                1,
                "Overgrow",
                "Boosts grass-type moves"
        );
        Ability ability2 = Ability.from(
                2,
                "Overgrow",
                "Boosts grass-type moves"
        );
        assertEquals(
                ability1.hashCode(),
                ability2.hashCode()
        );
    }
}
