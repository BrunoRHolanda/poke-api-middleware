package com.poke.api.middleware.application;

/**
 * A custom exception class used to indicate application-level errors.
 * This exception is thrown when an error occurs within the application layer,
 * such as validation failures or other unexpected situations that should be handled specifically by the application.
 */
public class ApplicationException extends RuntimeException {

    /**
     * Constructs a new {@code ApplicationException} with the specified detail message.
     *
     * @param message the detail message explaining the reason for the exception
     */
    public ApplicationException(String message) {
        super(message);
    }
}
