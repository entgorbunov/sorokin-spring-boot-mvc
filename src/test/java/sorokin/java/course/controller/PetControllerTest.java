package sorokin.java.course.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import sorokin.java.course.dto.PetDto;
import sorokin.java.course.entity.Pet;
import sorokin.java.course.exceptions.EntityNotFoundException;
import sorokin.java.course.mapper.PetMapper;
import sorokin.java.course.service.PetService;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PetController.class)
class PetControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PetService petService;

    @MockBean
    private PetMapper petMapper;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCreatePet() throws Exception {
        PetDto petDto = new PetDto("Fluffy", 1L);
        Pet pet = new Pet(1L, "Fluffy", 1L);

        when(petMapper.toEntity(any(PetDto.class))).thenReturn(pet);
        when(petService.create(any(Pet.class))).thenReturn(pet);

        mockMvc.perform(post("/pets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(petDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Fluffy"))
                .andExpect(jsonPath("$.userId").value(1));

        verify(petService).create(any(Pet.class));
    }

    @Test
    void testGetPetById() throws Exception {
        Pet pet = new Pet(1L, "Fluffy", 1L);

        when(petService.findById(1L)).thenReturn(pet);

        mockMvc.perform(get("/pets/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Fluffy"))
                .andExpect(jsonPath("$.userId").value(1));
    }

    @Test
    void testGetPetByIdNotFound() throws Exception {
        when(petService.findById(1L)).thenThrow(new EntityNotFoundException("Pet not found with id: 1"));

        mockMvc.perform(get("/pets/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGetAllPets() throws Exception {
        Pet pet1 = new Pet(1L, "Fluffy", 1L);
        Pet pet2 = new Pet(2L, "Buddy", 2L);

        when(petService.findAll()).thenReturn(Arrays.asList(pet1, pet2));

        mockMvc.perform(get("/pets"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[1].id").value(2));
    }

    @Test
    void testUpdatePet() throws Exception {
        PetDto petDto = new PetDto("UpdatedFluffy", 1L);
        Pet updatedPet = new Pet(1L, "UpdatedFluffy", 1L);

        when(petMapper.toEntity(any(PetDto.class))).thenReturn(updatedPet);
        when(petService.update(any(Pet.class))).thenReturn(updatedPet);

        mockMvc.perform(put("/pets/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(petDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("UpdatedFluffy"));
    }

    @Test
    void testDeletePet() throws Exception {
        doNothing().when(petService).delete(1L);

        mockMvc.perform(delete("/pets/1"))
                .andExpect(status().isOk());

        verify(petService).delete(1L);
    }
}
