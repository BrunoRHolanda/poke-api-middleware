package com.poke.api.middleware.domain;

/**
 * A custom exception class used to indicate domain validation errors.
 * This exception is thrown when validation within the domain layer fails,
 * typically during the validation of entities or value objects.
 */
public class DomainValidationException extends RuntimeException {

    /**
     * Constructs a new DomainValidationException with the specified detail message.
     *
     * @param message the detail message explaining the reason for the validation failure
     */
    public DomainValidationException(String message) {
        super(message);
    }
}
