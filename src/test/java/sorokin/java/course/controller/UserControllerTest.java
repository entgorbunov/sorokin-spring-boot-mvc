package sorokin.java.course.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import sorokin.java.course.dto.UserDto;
import sorokin.java.course.entity.User;
import sorokin.java.course.exceptions.EntityNotFoundException;
import sorokin.java.course.mapper.UserMapper;
import sorokin.java.course.service.UserService;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private UserMapper userMapper;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCreateUser() throws Exception {
        UserDto userDto = new UserDto("John Doe", "john@example.com", 30, null);
        User user = new User(1L, "John Doe", "john@example.com", 30, null);

        when(userMapper.toEntity(any(UserDto.class))).thenReturn(user);
        when(userService.create(any(User.class))).thenReturn(user);

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.email").value("john@example.com"))
                .andExpect(jsonPath("$.age").value(30));

        verify(userService).create(any(User.class));
    }

    @Test
    void testGetUserById() throws Exception {
        User user = new User(1L, "John Doe", "john@example.com", 30, null);

        when(userService.findById(1L)).thenReturn(user);

        mockMvc.perform(get("/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.email").value("john@example.com"))
                .andExpect(jsonPath("$.age").value(30));
    }

    @Test
    void testGetUserByIdNotFound() throws Exception {
        when(userService.findById(1L)).thenThrow(new EntityNotFoundException("User not found with id: 1"));

        mockMvc.perform(get("/users/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGetAllUsers() throws Exception {
        User user1 = new User(1L, "John Doe", "john@example.com", 30, null);
        User user2 = new User(2L, "Jane Doe", "jane@example.com", 28, null);

        when(userService.findAll()).thenReturn(Arrays.asList(user1, user2));

        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[1].id").value(2));
    }

    @Test
    void testUpdateUser() throws Exception {
        UserDto userDto = new UserDto("Updated John", "john@example.com", 31, null);
        User updatedUser = new User(1L, "Updated John", "john@example.com", 31, null);

        when(userMapper.toEntity(any(UserDto.class))).thenReturn(updatedUser);
        when(userService.update(any(User.class))).thenReturn(updatedUser);

        mockMvc.perform(put("/users/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Updated John"));
    }

    @Test
    void testDeleteUser() throws Exception {
        doNothing().when(userService).delete(1L);

        mockMvc.perform(delete("/users/1"))
                .andExpect(status().isOk());

        verify(userService).delete(1L);
    }
}
