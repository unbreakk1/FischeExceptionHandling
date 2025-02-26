package de.neuefische.springexceptionhandlingtask;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/animals")
public class AnimalController
{
    @GetMapping("{species}")
    String getAnimalSpecies(@PathVariable String species)
    {
        if (!species.equals("dog"))
            throw new IllegalArgumentException("Only 'dog' is allowed");

        return species;
    }

    @GetMapping
    String getAllAnimals()
    {
        throw new NoSuchElementException("No Animals found");
    }

   // @ExceptionHandler(IllegalArgumentException.class)
   // @ResponseStatus(HttpStatus.BAD_REQUEST) // Returns 400 Bad Request
   // public ErrorMessage handleIllegalArgumentException(IllegalArgumentException ex)
   // {
   //     return new ErrorMessage(ex.getMessage(), HttpStatus.BAD_REQUEST.value());
   // }

}
