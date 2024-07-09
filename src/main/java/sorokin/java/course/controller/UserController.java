package sorokin.java.course.controller;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sorokin.java.course.dto.UserDto;
import sorokin.java.course.entity.User;
import sorokin.java.course.mapper.UserMapper;
import sorokin.java.course.service.UserService;

import java.util.List;

@Validated
@RestController
@RequestMapping("/users")
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping("/{id}")
    public User getById(@PathVariable Long id) {
        LOGGER.info("Received request to get user by id: {}", id);
        return userService.findById(id);
    }

    @GetMapping
    public List<User> getAllUsers() {
        LOGGER.info("Received request to get all users");
        return userService.findAll();
    }

    @PostMapping
    public User createUser(@Valid @RequestBody UserDto userDto) {
        LOGGER.info("Received request to create a new user");
        User user = userMapper.toEntity(userDto);
        return userService.create(user);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @Valid @RequestBody UserDto userDto) {
        LOGGER.info("Received request to update user with id: {}", id);
        User user = userMapper.toEntity(userDto);
        return userService.update(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        LOGGER.info("Received request to delete user with id: {}", id);
        userService.delete(id);
    }


}
