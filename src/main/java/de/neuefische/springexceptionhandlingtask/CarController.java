package de.neuefische.springexceptionhandlingtask;

import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/cars")
public class CarController
{

    @GetMapping("{brand}")
    String getCarBrand(@PathVariable String brand)
    {
        if (!brand.equals("porsche"))
            throw new IllegalArgumentException("Only 'porsche' allowed");

        return brand;
    }

    @GetMapping
    String getAllCars()
    {
        throw new NoSuchElementException("No Cars found");
    }

  //  @ExceptionHandler
  //  @ResponseStatus(HttpStatus.BAD_REQUEST)
  //  public ErrorMessage handleIllegalArgumentException(IllegalArgumentException ex)
  //  {
  //      return new ErrorMessage(ex.getMessage(), HttpStatus.BAD_REQUEST.value());
  //  }
}

