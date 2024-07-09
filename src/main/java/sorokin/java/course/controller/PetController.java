package sorokin.java.course.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sorokin.java.course.dto.PetDto;
import sorokin.java.course.entity.Pet;
import sorokin.java.course.exceptions.ControllerException;
import sorokin.java.course.mapper.Mapper;
import sorokin.java.course.service.PetService;

import java.util.List;
import java.util.Optional;
@Validated
@Slf4j
@RestController
@RequestMapping("/pets")
@RequiredArgsConstructor
public class PetController {

    private final PetService petService;
    private final Mapper mapper;

    @GetMapping("/{id}")
    public Pet getById(@PathVariable Long id) {
        log.info("Received request to get pet by id: {}", id);
        return petService.findById(id).orElseThrow(() -> {
            log.error("Pet not found with id: {}", id);
            return new ControllerException("Pet not found with id: " + id);
        });
    }

    @GetMapping
    public List<Pet> getAllPets() {
        log.info("Received request to get all pets");
        return petService.findAll();
    }

    @PostMapping
    public Pet createPet(@Valid @RequestBody PetDto petDto) {
        log.info("Received request to create a new pet");
        Pet pet = mapper.toEntity(petDto);
        return petService.create(pet);
    }

    @PutMapping("/{id}")
    public Optional<Pet> updatePet(@PathVariable Long id, @Valid @RequestBody PetDto petDto) {
        log.info("Received request to update pet with id: {}", id);
        return petService.findById(id)
                .map(existingPet -> {
                    Pet updatedPet = mapper.toEntity(petDto);
                    updatedPet.setId(id);
                    return petService.update(updatedPet);
                })
                .orElseThrow(() -> {
                    log.error("Pet not found for update, id: {}", id);
                    return new ControllerException("Pet not found with id: " + id);
                });
    }

    @DeleteMapping("/{id}")
    public void deletePet(@PathVariable Long id) {
        log.info("Received request to delete pet with id: {}", id);
        petService.findById(id).ifPresentOrElse(
                pet -> petService.delete(id),
                () -> {
                    log.error("Pet not found for deletion, id: {}", id);
                    throw new ControllerException("Pet not found with id: " + id);
                }
        );
    }
}
