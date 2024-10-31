package com.poke.api.middleware.infrastructure.configuration;

import com.poke.api.middleware.application.ApplicationException;
import com.poke.api.middleware.application.ErrorDTO;
import com.poke.api.middleware.domain.DomainValidationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Global exception handler for handling various exceptions across the application.
 * This class extends {@link ResponseEntityExceptionHandler} and provides
 * specific methods to handle different types of exceptions and return structured responses.
 */
@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Handles {@link HttpClientErrorException} and returns a structured response entity
     * with details about the client error.
     *
     * @param ex the exception that occurred during an HTTP client request
     * @param request the {@link WebRequest} object containing details of the request
     * @return a {@link ResponseEntity} containing an {@link ErrorDTO} with error details,
     *         HTTP headers, and the status code from the exception
     */
    @ExceptionHandler({HttpClientErrorException.class})
    public ResponseEntity<ErrorDTO> handleHttpClientErrorException(
            HttpClientErrorException ex,
            WebRequest request
    ) {
        return new ResponseEntity<>(
                ErrorDTO.with(
                        ex.getLocalizedMessage(),
                        ex.getStatusCode().value(),
                        ex.getMessage(),
                        request.getContextPath()
                ),
                new HttpHeaders(),
                ex.getStatusCode()
        );
    }

    /**
     * Handles {@link ApplicationException} and returns a structured response entity
     * with details about the application-level error.
     *
     * @param ex the exception specific to the application that occurred
     * @param request the {@link WebRequest} object containing details of the request
     * @return a {@link ResponseEntity} containing an {@link ErrorDTO} with error details,
     *         HTTP headers, and a status code of 422 (Unprocessable Entity)
     */
    @ExceptionHandler({ApplicationException.class})
    public ResponseEntity<ErrorDTO> handleApplicationException(
            ApplicationException ex,
            WebRequest request
    ) {
        return new ResponseEntity<>(
                ErrorDTO.with(
                        ex.getLocalizedMessage(),
                        HttpStatus.UNPROCESSABLE_ENTITY.value(),
                        ex.getMessage(),
                        request.getContextPath()
                ),
                new HttpHeaders(),
                HttpStatus.UNPROCESSABLE_ENTITY
        );
    }

    /**
     * Handles {@link DomainValidationException} and returns a structured response entity
     * with details about the domain validation error.
     *
     * @param ex the exception that occurred due to domain validation issues
     * @param request the {@link WebRequest} object containing details of the request
     * @return a {@link ResponseEntity} containing an {@link ErrorDTO} with error details,
     *         HTTP headers, and a status code of 422 (Unprocessable Entity)
     */
    @ExceptionHandler({DomainValidationException.class})
    public ResponseEntity<ErrorDTO> handleDomainValidationException(
            DomainValidationException ex,
            WebRequest request
    ) {
        return new ResponseEntity<>(
                ErrorDTO.with(
                        ex.getLocalizedMessage(),
                        HttpStatus.UNPROCESSABLE_ENTITY.value(),
                        ex.getMessage(),
                        request.getContextPath()
                ),
                new HttpHeaders(),
                HttpStatus.UNPROCESSABLE_ENTITY
        );
    }
}
