package com.poke.api.middleware.application;

import com.github.javafaker.Faker;
import com.poke.api.middleware.domain.Ability;
import com.poke.api.middleware.domain.Pokemon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the {@link PokemonService} class.
 */
class PokemonServiceTest {

    private PokemonGateway gateway;
    private PokemonService service;
    private Faker faker;

    @BeforeEach
    void setUp() {
        gateway = mock(PokemonGateway.class);
        service = new PokemonService(gateway);
        faker = new Faker();
    }

    /**
     * Test scenario where the Pokémon is not found in the local data source
     * but is found in the external gateway.
     */
    @Test
    void testSearchPokemonFoundInGateway() {
        String pokemonName = faker.pokemon().name();
        Ability ability = Ability.from(1, "Blaze", "Boosts fire-type moves");
        Pokemon pokemon = Pokemon.from(1, pokemonName, faker.internet().avatar(), List.of(ability));

        when(gateway.findByName(pokemonName)).thenReturn(Optional.of(pokemon));

        PokemonInput input = new PokemonInput(pokemonName);
        PokemonOutput output = service.search(input);

        verify(gateway, times(1)).findByName(pokemonName);
        assertEquals(pokemonName, output.name());
        assertEquals(pokemon.getSprite(), output.sprite());
        assertEquals(1, output.abilities().size());
    }

    /**
     * Test scenario where the Pokémon is not found in either the local data source
     * or the external gateway, resulting in an ApplicationException.
     */
    @Test
    void testSearchPokemonNotFoundAnywhere() {
        String pokemonName = faker.pokemon().name();

        when(gateway.findByName(pokemonName)).thenReturn(Optional.empty());

        PokemonInput input = new PokemonInput(pokemonName);
        Exception exception = assertThrows(ApplicationException.class, () -> service.search(input));

        assertEquals("Pokemon not found", exception.getMessage());
        verify(gateway, times(1)).findByName(pokemonName);
    }

    /**
     * Test scenario where the Pokémon is found in the gateway with unsorted abilities,
     * ensuring the abilities are sorted before saving to the local data source.
     */
    @Test
    void testSearchPokemonFoundInGatewayWithUnsortedAbilities() {
        String pokemonName = faker.pokemon().name();
        Ability ability1 = Ability.from(1, "Blaze", "Boosts fire-type moves");
        Ability ability2 = Ability.from(2, "Solar Power", "Increases Sp. Atk");
        Pokemon pokemon = Pokemon.from(1, pokemonName, faker.internet().avatar(), List.of(ability2, ability1));

        when(gateway.findByName(pokemonName)).thenReturn(Optional.of(pokemon));

        PokemonInput input = new PokemonInput(pokemonName);
        PokemonOutput output = service.search(input);

        verify(gateway, times(1)).findByName(pokemonName);

        // Verify that abilities are sorted in alphabetical order
        assertEquals("Blaze", output.abilities().get(0).name());
        assertEquals("Solar Power", output.abilities().get(1).name());
    }
}
