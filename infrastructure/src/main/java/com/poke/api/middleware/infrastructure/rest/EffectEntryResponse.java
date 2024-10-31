package com.poke.api.middleware.infrastructure.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Record representing the response for an effect entry from an external API.
 * This record is used to deserialize JSON data into a Java object.
 * The {@link JsonIgnoreProperties} annotation is used to ignore any unknown properties
 * during the deserialization process.
 *
 * @param effect the description of the effect
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record EffectEntryResponse(
        String effect
) {
}
