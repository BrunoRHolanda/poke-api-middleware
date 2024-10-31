package com.poke.api.middleware.application;

import com.poke.api.middleware.domain.Ability;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link AbilityOutput} class.
 */
class AbilityOutputTest {

    /**
     * Test successful creation of an AbilityOutput with valid data.
     */
    @Test
    void testAbilityOutputCreation() {
        AbilityOutput abilityOutput = new AbilityOutput(1, "Overgrow", "Boosts grass-type moves");
        assertNotNull(abilityOutput);
        assertEquals(1, abilityOutput.id());
        assertEquals("Overgrow", abilityOutput.name());
        assertEquals("Boosts grass-type moves", abilityOutput.effect());
    }

    /**
     * Test that an ApplicationException is thrown when name is null.
     */
    @Test
    void testAbilityOutputThrowsExceptionWhenNameIsNull() {
        Exception exception = assertThrows(ApplicationException.class, () -> {
            new AbilityOutput(1, null, "Boosts grass-type moves");
        });
        assertEquals("Name cannot be null", exception.getMessage());
    }

    /**
     * Test that an ApplicationException is thrown when effect is null.
     */
    @Test
    void testAbilityOutputThrowsExceptionWhenEffectIsNull() {
        Exception exception = assertThrows(ApplicationException.class, () -> {
            new AbilityOutput(1, "Overgrow", null);
        });
        assertEquals("Effect cannot be null", exception.getMessage());
    }

    /**
     * Test that the from(Ability ability) method correctly converts an Ability to an AbilityOutput.
     */
    @Test
    void testFromAbility() {
        Ability ability = Ability.from(1, "Overgrow", "Boosts grass-type moves");
        AbilityOutput abilityOutput = AbilityOutput.from(ability);

        assertEquals(ability.getId(), abilityOutput.id());
        assertEquals(ability.getName(), abilityOutput.name());
        assertEquals(ability.getEffect(), abilityOutput.effect());
    }

    /**
     * Test that the from(List<Ability> abilities) method correctly converts a list of Ability to a list of AbilityOutput.
     */
    @Test
    void testFromAbilityList() {
        Ability ability1 = Ability.from(1, "Overgrow", "Boosts grass-type moves");
        Ability ability2 = Ability.from(2, "Blaze", "Boosts fire-type moves");

        List<AbilityOutput> abilityOutputs = AbilityOutput.from(List.of(ability1, ability2));

        assertEquals(2, abilityOutputs.size());
        assertEquals("Overgrow", abilityOutputs.get(0).name());
        assertEquals("Blaze", abilityOutputs.get(1).name());
    }
}
