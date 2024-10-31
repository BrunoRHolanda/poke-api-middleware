package com.poke.api.middleware.infrastructure.rest;

import com.poke.api.middleware.domain.Ability;

import java.util.List;

/**
 * Utility class for mapping data from {@link AbilityResponse} objects to {@link Ability} domain objects.
 * This class provides methods to map individual responses and lists of responses.
 */
public class AbilityMapper {

    /**
     * Maps a single {@link AbilityResponse} object to an {@link Ability} domain object.
     *
     * @param abilityResponse the {@link AbilityResponse} object to be mapped
     * @return an {@link Ability} object created from the given response
     */
    public static Ability map(AbilityResponse abilityResponse) {
        return Ability.from(
                abilityResponse.id(),
                abilityResponse.name(),
                !abilityResponse.effect_entries().isEmpty() ?
                abilityResponse.effect_entries().get(0).effect() : ""
        );
    }

    /**
     * Maps a list of {@link AbilityResponse} objects to a list of {@link Ability} domain objects.
     *
     * @param abilityResponseList the list of {@link AbilityResponse} objects to be mapped
     * @return a list of {@link Ability} objects created from the given list of responses
     */
    public static List<Ability> map(List<AbilityResponse> abilityResponseList) {
        return abilityResponseList.stream()
                                  .map(AbilityMapper::map)
                                  .toList();
    }
}
