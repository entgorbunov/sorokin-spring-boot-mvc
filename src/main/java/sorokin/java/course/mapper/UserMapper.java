package sorokin.java.course.mapper;

import org.springframework.stereotype.Service;
import sorokin.java.course.dto.PetDto;
import sorokin.java.course.dto.UserDto;
import sorokin.java.course.entity.Pet;
import sorokin.java.course.entity.User;
@Service
public class UserMapper {

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


}
