package sorokin.java.course.mapper;

import org.springframework.stereotype.Service;
import sorokin.java.course.dto.PetDto;
import sorokin.java.course.dto.UserDto;
import sorokin.java.course.entity.Pet;
import sorokin.java.course.entity.User;
@Service
public class Mapper {

    public User toEntity(UserDto userDto) {
        return User.builder()
                .name(userDto.getName())
                .email(userDto.getEmail())
                .age(userDto.getAge())
                .pets(userDto.getPets())
                .build();
    }

    public UserDto toDto(User user) {
        return UserDto.builder()
                .name(user.getName())
                .email(user.getEmail())
                .age(user.getAge())
                .pets(user.getPets())
                .build();
    }

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
