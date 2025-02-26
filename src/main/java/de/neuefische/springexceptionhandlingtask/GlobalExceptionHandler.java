package de.neuefische.springexceptionhandlingtask;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler
{
    /**
     * Handles IllegalArgumentException.
     */
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) // Returns 400 Bad Request
    public ErrorMessage handleIllegalArgumentException(IllegalArgumentException e)
    {
        return new ErrorMessage(e.getMessage(), HttpStatus.BAD_REQUEST.value());
    }

    /**
     * Handles NoSuchElementException.
     */
    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND) // Returns 404 Not Found
    public ErrorMessage handleNoSuchElementException(NoSuchElementException e)
    {
        return new ErrorMessage(e.getMessage(), HttpStatus.NOT_FOUND.value());
    }

    /**
     * Handles NullPointerException.
     */
    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) // Returns 400 Bad Request
    public ErrorMessage handleNullPointerException(NullPointerException e)
    {
        return new ErrorMessage("Null value encountered", HttpStatus.BAD_REQUEST.value());
    }

    /**
     * Handles ArithmeticException.
     */
    @ExceptionHandler(ArithmeticException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) // Returns 400 Bad Request
    public ErrorMessage handleArithmeticException(ArithmeticException e)
    {
        return new ErrorMessage("Arithmetic error: " + e.getMessage(), HttpStatus.BAD_REQUEST.value());
    }

    /**
     * Handles all other standard Java exceptions.
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // Returns 500 Internal Server Error
    public ErrorMessage handleGenericException(Exception e)
    {
        return new ErrorMessage("An unexpected error occurred", HttpStatus.INTERNAL_SERVER_ERROR.value());
    }
}
