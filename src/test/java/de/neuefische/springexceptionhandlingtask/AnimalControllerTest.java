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


@SpringBootTest
@AutoConfigureMockMvc
public class AnimalControllerTest
{
    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetAnimalSpecies_withInvalidSpecies_shouldReturnBadRequest() throws Exception {
        mockMvc.perform(get("/api/animals/cat")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Only 'dog' is allowed"))
                .andExpect(jsonPath("$.status").value(HttpStatus.BAD_REQUEST.value()));
    }

    @Test
    void testGetAnimalSpecies_withValidSpecies_shouldReturnOk() throws Exception
    {
        mockMvc.perform(get("/api/animals/dog")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("dog"));
    }

    @Test
    void testGetAllAnimals_shouldReturnNotFound() throws Exception
    {
        mockMvc.perform(get("/api/animals")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("No Animals found"))
                .andExpect(jsonPath("$.status").value(HttpStatus.NOT_FOUND.value()));
    }


}
