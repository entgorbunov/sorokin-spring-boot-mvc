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
import sorokin.java.course.exceptions.ControllerException;
import sorokin.java.course.mapper.Mapper;
import sorokin.java.course.service.PetService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
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
    private Mapper mapper;

    @Test
    void testCreatePet() throws Exception {
        PetDto petDto = new PetDto("Fluffy", 1L);
        Pet pet = new Pet(1L, "Fluffy", 1L);

        when(mapper.toEntity(any(PetDto.class))).thenReturn(pet);
        when(petService.create(any(Pet.class))).thenReturn(pet);

        mockMvc.perform(post("/pets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(petDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Fluffy"))
                .andExpect(jsonPath("$.userId").value(1));

        verify(petService).create(any(Pet.class));
    }

    @Test
    void testCreatePetWithInvalidData() throws Exception {
        PetDto petDto = new PetDto("", null);

        mockMvc.perform(post("/pets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(petDto)))
                .andExpect(status().isBadRequest());

        verify(petService, never()).create(any(Pet.class));
    }

    @Test
    void testDeletePet() throws Exception {
        when(petService.findById(1L)).thenReturn(Optional.of(new Pet()));

        mockMvc.perform(delete("/pets/1"))
                .andExpect(status().isOk());

        verify(petService).findById(1L);
        verify(petService).delete(1L);
    }

    @Test
    void testDeleteNonExistentPet() throws Exception {
        when(petService.findById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(delete("/pets/1"))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertInstanceOf(ControllerException.class, result.getResolvedException()));

        verify(petService).findById(1L);
        verify(petService, never()).delete(anyLong());
    }
}

