package de.neuefische.springexceptionhandlingtask;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the AnimalController class.
 * These tests use Spring Boot's testing capabilities to verify the behavior of endpoints
 * defined in AnimalController. Tests focus on expected outputs based on different inputs
 * and scenarios, such as valid requests, invalid requests, and exceptions.
 */
@SpringBootTest
@AutoConfigureMockMvc
public class AnimalControllerTest
{
    @Autowired
    private MockMvc mockMvc;

    /**
     * Tests the /api/animals/{species} endpoint with an invalid species.
     * A species other than "dog" should result in a 400 Bad Request response,
     * with an appropriate error message in the response body.
     *
     * @throws Exception if the test fails to execute
     */
    @Test
    void testGetAnimalSpecies_withInvalidSpecies_shouldReturnBadRequest() throws Exception
    {
        // Perform a GET request with "cat" as the species (invalid)
        mockMvc.perform(get("/api/animals/cat")
                        .contentType(MediaType.APPLICATION_JSON))
                // Verify that the HTTP status is 400 Bad Request
                .andExpect(status().isBadRequest())
                // Verify that the error message matches the expected content
                .andExpect(jsonPath("$.message").value("Only 'dog' is allowed"))
                .andExpect(jsonPath("$.status").value(HttpStatus.BAD_REQUEST.value()));
    }

    /**
     * Tests the /api/animals/{species} endpoint with a valid species.
     * Providing "dog" as the species is valid, and the endpoint should return an OK (200)
     * response with the species name in the response body.
     * @throws Exception if the test fails to execute
     */
    @Test
    void testGetAnimalSpecies_withValidSpecies_shouldReturnOk() throws Exception
    {
        // Perform a GET request with "dog" as the species (valid)
        mockMvc.perform(get("/api/animals/dog")
                        .contentType(MediaType.APPLICATION_JSON))
                // Verify that the HTTP status is 200 OK
                .andExpect(status().isOk())
                // Verify that the response body contains the species name "dog"
                .andExpect(content().string("dog"));
    }

    /**
     * Tests the /api/animals endpoint to retrieve all animals.
     * Since the endpoint is designed to throw a NoSuchElementException, the response
     * should be a 404 Not Found with an appropriate error message in the response body.
     * @throws Exception if the test fails to execute
     */
    @Test
    void testGetAllAnimals_shouldReturnNotFound() throws Exception
    {
        // Perform a GET request to retrieve all animals
        mockMvc.perform(get("/api/animals")
                        .contentType(MediaType.APPLICATION_JSON))
                // Verify that the HTTP status is 404 Not Found
                .andExpect(status().isNotFound())
                // Verify that the error message matches "No Animals found"
                .andExpect(jsonPath("$.message").value("No Animals found"))
                .andExpect(jsonPath("$.status").value(HttpStatus.NOT_FOUND.value()));
    }
}
