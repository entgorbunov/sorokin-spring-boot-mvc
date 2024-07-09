package sorokin.java.course.controller;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sorokin.java.course.dto.PetDto;
import sorokin.java.course.entity.Pet;
import sorokin.java.course.mapper.PetMapper;
import sorokin.java.course.service.PetService;

import java.util.List;

@Validated
@RestController
@RequestMapping("/pets")
public class PetController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PetController.class);
    private final PetService petService;
    private final PetMapper petMapper;

    public PetController(PetService petService, PetMapper petMapper) {
        this.petService = petService;
        this.petMapper = petMapper;
    }

    @GetMapping("/{id}")
    public Pet getById(@PathVariable Long id) {
        LOGGER.info("Received request to get pet by id: {}", id);
        return petService.findById(id);
    }

    @GetMapping
    public List<Pet> getAllPets() {
        LOGGER.info("Received request to get all pets");
        return petService.findAll();
    }

    @PostMapping
    public Pet createPet(@Valid @RequestBody PetDto petDto) {
        LOGGER.info("Received request to create a new pet");
        Pet pet = petMapper.toEntity(petDto);
        return petService.create(pet);
    }

    @PutMapping("/{id}")
    public Pet updatePet(@PathVariable Long id, @Valid @RequestBody PetDto petDto) {
        LOGGER.info("Received request to update pet with id: {}", id);
        Pet entity = petMapper.toEntity(petDto);
        return petService.update(entity);
    }

    @DeleteMapping("/{id}")
    public void deletePet(@PathVariable Long id) {
        LOGGER.info("Received request to delete pet with id: {}", id);
        petService.delete(id);
    }
}
