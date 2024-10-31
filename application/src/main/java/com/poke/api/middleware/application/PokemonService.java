package com.poke.api.middleware.application;

import com.poke.api.middleware.domain.Pokemon;

/**
 * Service class responsible for handling Pokémon-related operations, such as searching for a Pokémon.
 * It interacts with both a data source and an external gateway to fetch Pokémon information.
 */
public class PokemonService {

    private final PokemonDatasource datasource;
    private final PokemonGateway gateway;

    /**
     * Constructs a {@code PokemonService} with the specified data source and gateway.
     *
     * @param datasource the {@link PokemonDatasource} for accessing local Pokémon data
     * @param gateway    the {@link PokemonGateway} for accessing external Pokémon data
     */
    public PokemonService(
            PokemonDatasource datasource,
            PokemonGateway gateway
    ) {
        this.datasource = datasource;
        this.gateway = gateway;
    }

    /**
     * Searches for a {@link Pokemon} by name. If the Pokémon is not found in the local data source,
     * it retrieves it from the external gateway, sorts its abilities, saves it to the local data source,
     * and then returns the result.
     *
     * @param input the {@link PokemonInput} containing the name of the Pokémon to search
     * @return a {@link PokemonOutput} representing the Pokémon data
     * @throws ApplicationException if the Pokémon is not found in both the data source and the gateway
     */
    public PokemonOutput search(PokemonInput input) {
        Pokemon pokemon = datasource.findByName(input.name())
                                    .orElseGet(() -> {
                                        Pokemon pokemonFromGateway = gateway.findByName(input.name())
                                                                            .orElseThrow(() -> new ApplicationException("Pokemon not found"));
                                        pokemonFromGateway.sortAbilities();

                                        datasource.save(pokemonFromGateway);

                                        return pokemonFromGateway;
                                    });

        return PokemonOutput.from(pokemon);
    }
}
