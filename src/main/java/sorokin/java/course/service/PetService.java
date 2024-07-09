package sorokin.java.course.service;

import lombok.extern.slf4j.Slf4j;
import sorokin.java.course.entity.Pet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@org.springframework.stereotype.Service
public class PetService implements Service<Pet, Long> {
    private final Map<Long, Pet> pets = new HashMap<>();

    @Override
    public void delete(Long id) {
        log.info("Deleting pet with id = {}", id);
        pets.remove(id);
    }

    @Override
    public Optional<Pet> findById(Long aLong) {
        log.info("Finding pet with id = {}", aLong);
        return Optional.ofNullable(pets.get(aLong));
    }

    @Override
    public List<Pet> findAll() {
        log.info("Fetching all pets");
        return pets.values().stream().toList();
    }

    @Override
    public Optional<Pet> update(Pet pet) {
        log.info("Updating pet with id: {}", pet.getId());
        if (pets.containsKey(pet.getId())) {
            validatePet(pet);
            pets.put(pet.getId(), pet);
            return Optional.of(pet);
        }
        log.warn("Pet not found for update, id: {}", pet.getId());
        return Optional.empty();
    }

    @Override
    public Pet create(Pet pet) {
        if (pet.getId() == null) {
            pet.setId(generateId());
        }
        validatePet(pet);
        log.info("Creating new pet with id: {}", pet.getId());
        return pets.put(pet.getId(), pet);
    }

    private Long generateId() {
        return pets.keySet()
                       .stream()
                       .max(Long::compare)
                       .orElse(0L) + 1;
    }

    private void validatePet(Pet pet) {
        if (pet.getName() == null || pet.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Pet name cannot be empty");
        }
        if (pet.getUserId() == null || pet.getUserId() <= 0) {
            throw new IllegalArgumentException("Invalid user ID");
        }
    }
}
