package com.poke.api.middleware.infrastructure.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AbilityResponse(
    int id,
    String name,
    EffectEntryResponse effect_entries
) {
}
