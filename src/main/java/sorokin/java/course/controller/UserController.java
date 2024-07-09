package sorokin.java.course.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sorokin.java.course.dto.UserDto;
import sorokin.java.course.entity.User;
import sorokin.java.course.exceptions.ControllerException;
import sorokin.java.course.mapper.Mapper;
import sorokin.java.course.service.UserService;

import java.util.List;
import java.util.Optional;
@Validated
@Slf4j
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final Mapper mapper;

    @GetMapping("/{id}")
    public User getById(@PathVariable Long id) {
        log.info("Received request to get user by id: {}", id);
        return userService.findById(id).orElseThrow(
                () -> {
                    log.error("User not found with id: {}", id);
                    return new ControllerException("User not found with id: " + id);
                });


    }

    @GetMapping
    public List<User> getAllUsers() {
        log.info("Received request to get all users");
        return userService.findAll();
    }

    @PostMapping
    public User createUser(@Valid @RequestBody UserDto userDto) {
        log.info("Received request to create a new user");
        User user = mapper.toEntity(userDto);
        return userService.create(user);
    }

    @PutMapping("/{id}")
    public Optional<User> updateUser(@PathVariable Long id, @Valid @RequestBody UserDto userDto) {
        log.info("Received request to update user with id: {}", id);
        return userService.findById(id)
                .map(existingUser -> {
                    User updatedUser = mapper.toEntity(userDto);
                    updatedUser.setId(id);
                    return userService.update(updatedUser);
                })
                .orElseThrow(() -> {
                            log.error("User not found for update: {}", id);
                            return new ControllerException("User not found with id: " + id);
                        }
                );

    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        log.info("Received request to delete user with id: {}", id);
        userService.findById(id)
                .ifPresentOrElse(
                        user -> userService.delete(id),

                        () -> {
                            log.error("User not found for deletion, id: {}", id);
                            new ControllerException("User not found with id: " + id);
                        }
                );
    }


}
