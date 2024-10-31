package com.poke.api.middleware.infrastructure.rest;

import com.poke.api.middleware.application.PokemonGateway;
import com.poke.api.middleware.domain.Pokemon;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class PokemonGatewayImpl implements PokemonGateway {
    private final RestTemplate restTemplate;

    public PokemonGatewayImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

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
                                                                        a.ability()
                                                                         .url(),
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
