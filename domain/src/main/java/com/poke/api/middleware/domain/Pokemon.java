package com.poke.api.middleware.domain;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Represents a Pokémon with a name, sprite image, and a list of abilities.
 * Each Pokémon is uniquely identified by an id and has multiple abilities.
 */
public class Pokemon extends Entity {

    /**
     * The name of the Pokémon.
     */
    private final String name;

    /**
     * The sprite image of the Pokémon.
     */
    private final String sprite;

    /**
     * The list of abilities that the Pokémon possesses.
     */
    private final List<Ability> abilities;

    /**
     * Constructs a Pokémon with the specified id, name, sprite, and abilities.
     *
     * @param id        the unique identifier of the Pokémon
     * @param name      the name of the Pokémon
     * @param sprite    the sprite image representing the Pokémon
     * @param abilities the list of abilities that the Pokémon possesses
     */
    protected Pokemon(
            final int id,
            final String name,
            final String sprite,
            final List<Ability> abilities
    ) {
        super(id);
        this.name = name;
        this.sprite = sprite;
        this.abilities = abilities;
    }

    /**
     * Factory method to create a new {@code Pokemon} instance.
     *
     * @param id        the unique identifier of the Pokémon
     * @param name      the name of the Pokémon
     * @param sprite    the sprite image of the Pokémon
     * @param abilities the list of abilities of the Pokémon
     * @return a new {@code Pokemon} object
     */
    public static Pokemon from(
            final int id,
            final String name,
            final String sprite,
            final List<Ability> abilities
    ) {
        return new Pokemon(
                id,
                name,
                sprite,
                abilities
        );
    }

    /**
     * Gets the name of the Pokémon.
     *
     * @return the name of the Pokémon
     */
    public final String getName() {
        return name;
    }

    /**
     * Gets the sprite image of the Pokémon.
     *
     * @return the sprite image of the Pokémon
     */
    public final String getSprite() {
        return sprite;
    }

    /**
     * Gets the list of abilities that the Pokémon possesses.
     *
     * @return an immutable list of abilities of the Pokémon
     */
    public final List<Ability> getAbilities() {
        return abilities;
    }

    /**
     * Validates the state of the Pokémon object.
     * Ensures that the name, sprite, and abilities are not null,
     * and that all abilities are valid.
     *
     * @throws DomainValidationException if any of the required fields are null
     */
    @Override
    protected void validate() throws
            DomainValidationException {
        try {
            Objects.requireNonNull(
                    name,
                    "Name cannot be null"
            );
            Objects.requireNonNull(
                    sprite,
                    "Sprite cannot be null"
            );
            Objects.requireNonNull(
                    abilities,
                    "Abilities cannot be null"
            );
        } catch (NullPointerException e) {
            throw new DomainValidationException(e.getMessage());
        }

        abilities.forEach(Ability::validate);
    }

    /**
     * Sorts the abilities of the Pokémon alphabetically by their names.
     */
    public void sortAbilities() {
        Collections.sort(abilities);
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     * Two Pokémon are considered equal if they have the same name, sprite, and abilities.
     *
     * @param o the reference object with which to compare
     * @return true if this Pokémon is the same as the obj argument; false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pokemon pokemon)) return false;
        return Objects.equals(
                name,
                pokemon.name
        ) &&
                Objects.equals(
                        sprite,
                        pokemon.sprite
                ) &&
                Objects.equals(
                        abilities,
                        pokemon.abilities
                );
    }

    /**
     * Returns a hash code value for the Pokémon.
     *
     * @return a hash code value for this Pokémon
     */
    @Override
    public int hashCode() {
        return Objects.hash(
                getId(),
                name,
                sprite,
                abilities
        );
    }

    /**
     * Returns a string representation of the Pokémon.
     * The string representation includes the id, name, sprite, and abilities of the Pokémon.
     *
     * @return a string representation of the Pokémon object
     */
    @Override
    public String toString() {
        return "Pokemon{" +
                "id=" + getId() +
                ", name='" + name + '\'' +
                ", sprite='" + sprite + '\'' +
                ", abilities=" + abilities +
                '}';
    }
}
