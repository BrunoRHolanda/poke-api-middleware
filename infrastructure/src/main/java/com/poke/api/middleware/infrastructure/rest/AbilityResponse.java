package com.poke.api.middleware.infrastructure.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Record representing the response for an ability from an external API.
 * This record is used to deserialize JSON data into a Java object.
 * The {@link JsonIgnoreProperties} annotation ensures that any unknown properties
 * in the JSON response are ignored during the deserialization process.
 *
 * @param id the unique identifier of the ability
 * @param name the name of the ability
 * @param effect_entries the list of {@link EffectEntryResponse} objects representing the effects of the ability
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record AbilityResponse(
        int id,
        String name,
        List<EffectEntryResponse> effect_entries
) {
}
