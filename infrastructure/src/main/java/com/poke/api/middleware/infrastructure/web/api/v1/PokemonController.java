package com.poke.api.middleware.infrastructure.web.api.v1;

import com.poke.api.middleware.application.PokemonInput;
import com.poke.api.middleware.application.PokemonOutput;
import com.poke.api.middleware.application.PokemonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for handling requests related to Pokémon searches.
 * This controller provides an endpoint for searching detailed information about a Pokémon by name.
 */
@RestController
@RequestMapping("/v1/pokemon")
@Tag(name = "Pokemon API", description = "API for searching Pokémon details")
public class PokemonController {
    private final PokemonService pokemonService;

    /**
     * Constructs a new {@link PokemonController} with the specified {@link PokemonService}.
     *
     * @param pokemonService the service used to handle Pokémon search operations
     */
    public PokemonController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    /**
     * Endpoint to search for a Pokémon by its name.
     * This method returns detailed information including the name, sprite, and abilities of the Pokémon.
     *
     * @param name the name of the Pokémon to search for
     * @return a {@link PokemonOutput} object containing the Pokémon's details
     */
    @GetMapping
    @Operation(
            summary = "Search for a Pokémon by name",
            description = "Returns detailed information about a Pokémon based on its name"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully found Pokémon",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PokemonOutput.class),
                            examples = @ExampleObject(
                                    value = """
                    {
                        "name": "bulbasaur",
                        "sprite": "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png",
                        "abilities": [
                            {
                                "id": 65,
                                "name": "overgrow",
                                "effect": "Powers up Grass-type moves when the Pokémon's HP is low."
                            },
                            {
                                "id": 34,
                                "name": "chlorophyll",
                                "effect": "Boosts the Pokémon's Speed stat in harsh sunlight."
                            }
                        ]
                    }
                    """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Pokémon not found",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                    {
                        "timestamp": "2024-10-31T12:00:00Z",
                        "status": 404,
                        "error": "Not Found",
                        "message": "Pokémon with name 'unknown' not found",
                        "path": "/api/v1/pokemon"
                    }
                    """
                            )
                    )
            )
    })
    @Cacheable(value = "pokemonCache", key = "#name")
    public PokemonOutput search(
            @RequestParam
            @Parameter(description = "Name of the Pokémon to search for", required = true, example = "bulbasaur")
            String name) {
        return pokemonService.search(PokemonInput.with(name));
    }
}
