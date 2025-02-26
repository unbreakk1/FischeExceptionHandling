package de.neuefische.springexceptionhandlingtask;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler
{
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) // Returns 400 Bad Request
    public ErrorMessage handleIllegalArgumentException(IllegalArgumentException e)
    {
        return new ErrorMessage(e.getMessage(), HttpStatus.BAD_REQUEST.value());
    }

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND) // Returns 404 Not Found
    public ErrorMessage handleNoSuchElementException(NoSuchElementException e)
    {
        return new ErrorMessage(e.getMessage(), HttpStatus.NOT_FOUND.value());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // Returns 500 Internal Server Error
    public ErrorMessage handleGenericException(Exception e)
    {
        return new ErrorMessage("An unexpected error occurred", HttpStatus.INTERNAL_SERVER_ERROR.value());
    }
}
