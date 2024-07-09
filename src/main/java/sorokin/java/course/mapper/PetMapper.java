package sorokin.java.course.mapper;

import org.springframework.stereotype.Service;
import sorokin.java.course.dto.PetDto;
import sorokin.java.course.entity.Pet;
@Service
public class PetMapper {
    public Pet toEntity(PetDto petDto) {
        return Pet.builder()
                .name(petDto.getName())
                .userId(petDto.getUserId())
                .build();
    }

    public PetDto toDto(Pet pet) {
        return PetDto.builder()
                .name(pet.getName())
                .userId(pet.getUserId())
                .build();
    }
}
