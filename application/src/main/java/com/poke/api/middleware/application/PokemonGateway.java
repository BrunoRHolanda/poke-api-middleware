package com.poke.api.middleware.application;

import com.poke.api.middleware.domain.Pokemon;

import java.util.Optional;

public interface PokemonGateway {
    public Optional<Pokemon> findByName(String name);
}
