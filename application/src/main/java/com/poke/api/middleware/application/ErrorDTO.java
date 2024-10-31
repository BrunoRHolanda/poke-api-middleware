package com.poke.api.middleware.application;

import java.time.Instant;

/**
 * Data Transfer Object (DTO) representing error details for API responses.
 * This class encapsulates information about an error, including a timestamp,
 * HTTP status, error message, detailed message, and the request path where the error occurred.
 */
public class ErrorDTO {
    private final Instant timestamp;
    private final Integer status;
    private final String error;
    private final String message;
    private final String path;

    /**
     * Constructs a new {@link ErrorDTO} with the specified details.
     *
     * @param timestamp the time at which the error occurred
     * @param status the HTTP status code associated with the error
     * @param error a short description of the error
     * @param message a detailed message explaining the error
     * @param path the request path where the error occurred
     */
    public ErrorDTO(Instant timestamp, Integer status, String error, String message, String path) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }

    /**
     * Gets the timestamp of when the error occurred.
     *
     * @return the timestamp as an {@link Instant}
     */
    public Instant getTimestamp() {
        return timestamp;
    }

    /**
     * Gets the HTTP status code of the error.
     *
     * @return the status code as an {@link Integer}
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * Gets a short description of the error.
     *
     * @return the error description as a {@link String}
     */
    public String getError() {
        return error;
    }

    /**
     * Gets the detailed error message.
     *
     * @return the message as a {@link String}
     */
    public String getMessage() {
        return message;
    }

    /**
     * Gets the request path where the error occurred.
     *
     * @return the path as a {@link String}
     */
    public String getPath() {
        return path;
    }

    /**
     * Factory method for creating an {@link ErrorDTO} instance with the current timestamp.
     *
     * @param error a short description of the error
     * @param status the HTTP status code associated with the error
     * @param message a detailed message explaining the error
     * @param path the request path where the error occurred
     * @return a new {@link ErrorDTO} instance
     */
    public static ErrorDTO with(String error, int status, String message, String path) {
        return new ErrorDTO(
                Instant.now(),
                status,
                error,
                message,
                path
        );
    }
}
