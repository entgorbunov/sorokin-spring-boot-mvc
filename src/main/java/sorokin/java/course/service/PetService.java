package sorokin.java.course.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sorokin.java.course.entity.Pet;
import sorokin.java.course.exceptions.EntityNotFoundException;
import sorokin.java.course.exceptions.InvalidEntityException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@org.springframework.stereotype.Service
public class PetService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PetService.class);
    private static final Map<Long, Pet> PET_MAP = new HashMap<>();
    private static final AtomicLong ID_COUNTER = new AtomicLong(1);

    public void delete(Long id) {
        LOGGER.info("Deleting pet with id = {}", id);
        if (!PET_MAP.containsKey(id)) {
            LOGGER.error("Pet not found for delete with id: {}", id);
            throw new EntityNotFoundException("Pet not found for delete with id: " + id);
        }
        PET_MAP.remove(id);
    }

    public Pet findById(Long id) {
        LOGGER.info("Finding pet with id = {}", id);
        Pet pet = PET_MAP.get(id);
        if (pet == null) {
            LOGGER.error("Pet not found with id: {}", id);
            throw new EntityNotFoundException("Pet not found with id: " + id);
        }
        return pet;
    }

    public List<Pet> findAll() {
        LOGGER.info("Fetching all pets");
        return PET_MAP.values().stream().toList();
    }

    public Pet update(Pet pet) {
        LOGGER.info("Updating pet with id: {}", pet.getId());
        if (PET_MAP.containsKey(pet.getId())) {
            validatePet(pet);
            PET_MAP.put(pet.getId(), pet);
            return pet;
        }
        LOGGER.error("Pet not found for update, id: {}", pet.getId());
        throw new EntityNotFoundException("Pet not found with id: " + pet.getId());
    }

    public Pet create(Pet pet) {
        if (pet.getId() == null) {
            pet.setId(generateId());
        }
        validatePet(pet);
        LOGGER.info("Creating new pet with id: {}", pet.getId());
        return PET_MAP.put(pet.getId(), pet);
    }




    private Long generateId() {
        return ID_COUNTER.getAndIncrement();
    }

    private void validatePet(Pet pet) {
        if (pet.getName() == null || pet.getName().trim().isEmpty()) {
            throw new InvalidEntityException("Pet name cannot be empty");
        }
        if (pet.getUserId() == null || pet.getUserId() <= 0) {
            throw new InvalidEntityException("Invalid user ID");
        }
    }


}
