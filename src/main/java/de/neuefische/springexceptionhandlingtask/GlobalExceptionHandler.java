package de.neuefische.springexceptionhandlingtask;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;
import java.time.LocalDateTime;

/**
 * A global exception handler for all controllers in the application.
 * This class uses @RestControllerAdvice to provide centralized exception handling
 * for standard and custom exceptions. It returns meaningful error responses
 * that include the error message, HTTP status, and a timestamp.
 */
@RestControllerAdvice
public class GlobalExceptionHandler
{

    /**
     * Handles IllegalArgumentException thrown by methods inside any controller.
     * This exception typically represents invalid arguments provided by the client.
     *
     * @param e the exception instance raised during runtime
     * @return an ErrorMessage object containing the error details
     */
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) // Returns HTTP 400 Bad Request
    public ErrorMessage handleIllegalArgumentException(IllegalArgumentException e)
    {
        return new ErrorMessage(e.getMessage(), HttpStatus.BAD_REQUEST.value(), LocalDateTime.now());
    }

    /**
     * Handles NoSuchElementException thrown by methods inside any controller.
     * This exception typically occurs when a resource cannot be found.
     *
     * @param e the exception instance raised during runtime
     * @return an ErrorMessage object containing error details related to the resource not found
     */
    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND) // Returns HTTP 404 Not Found
    public ErrorMessage handleNoSuchElementException(NoSuchElementException e)
    {
        return new ErrorMessage(e.getMessage(), HttpStatus.NOT_FOUND.value(), LocalDateTime.now());
    }

    /**
     * Handles NullPointerException thrown by methods inside any controller.
     * This exception is raised when the application attempts to operate on a null reference.
     *
     * @param e the exception instance raised during runtime
     * @return an ErrorMessage object containing the error details
     */
    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) // Returns HTTP 400 Bad Request
    public ErrorMessage handleNullPointerException(NullPointerException e)
    {
        return new ErrorMessage("Null value encountered", HttpStatus.BAD_REQUEST.value(), LocalDateTime.now());
    }

    /**
     * Handles ArithmeticException thrown by methods inside any controller.
     * This exception is typically caused by mathematical errors such as division by zero.
     *
     * @param e the exception instance raised during runtime
     * @return an ErrorMessage object containing the error details
     */
    @ExceptionHandler(ArithmeticException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) // Returns HTTP 400 Bad Request
    public ErrorMessage handleArithmeticException(ArithmeticException e)
    {
        return new ErrorMessage("Arithmetic error: " + e.getMessage(), HttpStatus.BAD_REQUEST.value(), LocalDateTime.now());
    }

    /**
     * Handles all other uncaught exceptions in the application.
     * This acts as a fallback mechanism for unexpected exceptions.
     *
     * @param e the exception instance raised during runtime
     * @return an ErrorMessage object containing a generic error message
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // Returns HTTP 500 Internal Server Error
    public ErrorMessage handleGenericException(Exception e)
    {
        return new ErrorMessage("An unexpected error occurred", HttpStatus.INTERNAL_SERVER_ERROR.value(), LocalDateTime.now());
    }

}
