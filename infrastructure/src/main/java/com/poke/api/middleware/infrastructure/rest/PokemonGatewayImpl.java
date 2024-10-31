package com.poke.api.middleware.infrastructure.rest;

import com.poke.api.middleware.application.PokemonGateway;
import com.poke.api.middleware.domain.Pokemon;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of the {@link PokemonGateway} interface for interacting with an external Pokémon API.
 * This service uses a {@link RestTemplate} to perform HTTP requests to the API and map the responses
 * into domain objects.
 */
@Service
public class PokemonGatewayImpl implements PokemonGateway {
    private final RestTemplate restTemplate;

    /**
     * Constructs a new {@link PokemonGatewayImpl} with the specified {@link RestTemplate}.
     *
     * @param restTemplate the {@link RestTemplate} used for HTTP requests to the Pokémon API
     */
    public PokemonGatewayImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Finds a {@link Pokemon} by its name by querying an external Pokémon API.
     * This method retrieves the basic Pokémon data and maps the associated abilities by making
     * additional API calls.
     *
     * @param name the name of the Pokémon to find
     * @return an {@link Optional} containing the {@link Pokemon} object if found, or empty if not
     * @throws RuntimeException if any data is missing or cannot be retrieved
     */
    @Override
    public Optional<Pokemon> findByName(String name) {
        Optional<PokemonResponse> response = Optional.ofNullable(restTemplate.getForObject(
                "https://pokeapi.co/api/v2/pokemon/" + name,
                PokemonResponse.class
        ));

        List<AbilityResponse> abilityResponseList = response.orElseThrow()
                                                            .abilities()
                                                            .stream()
                                                            .map(a -> {
                                                                Optional<AbilityResponse> abilityResponse = Optional.ofNullable(restTemplate.getForObject(
                                                                        a.ability().url(),
                                                                        AbilityResponse.class
                                                                ));

                                                                return abilityResponse.orElseThrow();
                                                            })
                                                            .toList();

        return response.map(pokemonResponse -> PokemonMapper.map(
                pokemonResponse,
                abilityResponseList
        ));
    }
}
