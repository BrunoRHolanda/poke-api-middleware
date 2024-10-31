package com.poke.api.middleware.domain;

import java.util.Objects;

/**
 * Represents an ability with a name and effect.
 * Each ability is uniquely identified by an id and contains a name and an effect description.
 */
public class Ability extends Entity implements Comparable<Ability> {

    /**
     * The name of the ability.
     */
    private final String name;

    /**
     * The effect of the ability.
     */
    private final String effect;

    /**
     * Constructs an Ability with the specified id, name, and effect.
     *
     * @param id     the unique identifier of the ability
     * @param name   the name of the ability
     * @param effect the effect description of the ability
     */
    protected Ability(
            final int id,
            final String name,
            final String effect
    ) {
        super(id);
        this.name = name;
        this.effect = effect;
    }

    /**
     * Factory method to create a new Ability instance.
     *
     * @param id     the unique identifier of the ability
     * @param name   the name of the ability
     * @param effect the effect description of the ability
     * @return a new {@code Ability} object
     */
    public static Ability from(
            final int id,
            final String name,
            final String effect
    ) {
        return new Ability(
                id,
                name,
                effect
        );
    }

    /**
     * Gets the name of the ability.
     *
     * @return the name of the ability
     */
    public final String getName() {
        return name;
    }

    /**
     * Gets the effect description of the ability.
     *
     * @return the effect description of the ability
     */
    public final String getEffect() {
        return effect;
    }

    /**
     * Validates the state of the ability object.
     * Ensures that the name and effect are not null.
     *
     * @throws DomainValidationException if any of the required fields are null
     */
    @Override
    protected void validate() throws
            DomainValidationException {
        try {
            Objects.requireNonNull(
                    name,
                    "Name is required"
            );
            Objects.requireNonNull(
                    effect,
                    "Effect is required"
            );
        } catch (NullPointerException e) {
            throw new DomainValidationException(e.getMessage());
        }
    }

    /**
     * Compares this ability to another ability based on their names.
     *
     * @param o the other ability to be compared with
     * @return a negative integer, zero, or a positive integer as this ability's name
     * is less than, equal to, or greater than the specified ability's name
     */
    @Override
    public int compareTo(Ability o) {
        return this.getName()
                   .compareTo(o.getName());
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     * Two abilities are considered equal if they have the same name and effect.
     *
     * @param obj the reference object with which to compare
     * @return true if this object is the same as the obj argument; false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Ability ability = (Ability) obj;
        return Objects.equals(
                name,
                ability.name
        ) &&
                Objects.equals(
                        effect,
                        ability.effect
                );
    }

    /**
     * Returns a hash code value for the ability.
     *
     * @return a hash code value for this ability
     */
    @Override
    public int hashCode() {
        return Objects.hash(
                name,
                effect
        );
    }

    /**
     * Returns a string representation of the ability.
     * The string representation includes the name and effect of the ability.
     *
     * @return a string representation of the object
     */
    @Override
    public String toString() {
        return "Ability{" +
                "name='" + name + '\'' +
                ", effect='" + effect + '\'' +
                '}';
    }
}
