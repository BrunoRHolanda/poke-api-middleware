package com.poke.api.middleware.infrastructure.rest;

import com.poke.api.middleware.domain.Ability;

import java.util.List;

public class AbilityMapper {
    public static Ability map(AbilityResponse abilityResponse) {
        return Ability.from(
                abilityResponse.id(),
                abilityResponse.name(),
                abilityResponse.effect_entries()
                               .effect()
        );
    }

    public static List<Ability> map(List<AbilityResponse> abilityResponseList) {
        return abilityResponseList.stream()
                                  .map(AbilityMapper::map)
                                  .toList();
    }
}
