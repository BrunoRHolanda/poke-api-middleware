package com.poke.api.middleware.application;

import com.poke.api.middleware.domain.Ability;

import java.util.List;
import java.util.Objects;

/**
 * Represents the output data structure for an ability, containing its id, name, and effect.
 * This class is a read-only record used to transfer data from the domain layer to other layers.
 */
public record AbilityOutput(
        int id,
        String name,
        String effect
) {

    /**
     * Constructs an {@code AbilityOutput} record.
     * Ensures that the {@code name} and {@code effect} fields are not null.
     *
     * @param id     the unique identifier of the ability
     * @param name   the name of the ability
     * @param effect the effect description of the ability
     * @throws ApplicationException if {@code name} or {@code effect} is null
     */
    public AbilityOutput {
        try {
            Objects.requireNonNull(name, "Name cannot be null");
            Objects.requireNonNull(effect, "Effect cannot be null");
        } catch (NullPointerException e) {
            throw new ApplicationException(e.getMessage());
        }
    }

    /**
     * Converts an {@link Ability} domain object to an {@code AbilityOutput}.
     *
     * @param ability the {@code Ability} object to be converted
     * @return an {@code AbilityOutput} instance containing data from the given {@code Ability}
     */
    public static AbilityOutput from(Ability ability) {
        return new AbilityOutput(
                ability.getId(),
                ability.getName(),
                ability.getEffect()
        );
    }

    /**
     * Converts a list of {@link Ability} objects to a list of {@code AbilityOutput} objects.
     *
     * @param abilities the list of {@code Ability} objects to be converted
     * @return a list of {@code AbilityOutput} instances
     */
    public static List<AbilityOutput> from(List<Ability> abilities) {
        return List.of(
                abilities.stream()
                         .map(AbilityOutput::from)
                         .toArray(AbilityOutput[]::new)
        );
    }
}
