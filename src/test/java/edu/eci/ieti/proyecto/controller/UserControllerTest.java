package edu.eci.ieti.proyecto.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import edu.eci.ieti.proyecto.data.User;
import edu.eci.ieti.proyecto.service.UserService;

import java.util.List;
import java.util.Arrays;
import java.util.Optional;
import org.springframework.http.MediaType;

import static org.mockito.Mockito.when;

@WebMvcTest(UserController.class)
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    void testCreateUser() throws Exception {
        User user = new User();
        user.setName("Alejandro");

        when(userService.createUser(user)).thenAnswer(invocation -> {
            User newUser = invocation.getArgument(0);
            newUser.setId("2L");
            return newUser;
        });

        mockMvc.perform(MockMvcRequestBuilders.post("/api/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"name\": \"Alejandro\" }"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Alejandro"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(2)); // Asegura que el id sea devuelto
    }

    @Test
    void testGetAllUsers() throws Exception {
        User user1 = new User();
        User user2 = new User();
        List<User> users = Arrays.asList(user1, user2);

        when(userService.getAllUsers()).thenReturn(users);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/user"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2));
    }

    @Test
    void testGetUserByID() throws Exception {
        User user = new User();
        user.setId("1L");

        when(userService.findUserById("1L")).thenReturn(Optional.of(user));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/user/" + 1))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
    }

    @Test
    void testUpdateUser() throws Exception {
        User user = new User();
        user.setName("Carlos");

        when(userService.updateUser(user, "1L")).thenAnswer(invocation -> {
            User newUser = invocation.getArgument(0);
            newUser.setId("1L");
            newUser.setName("Juan");
            return newUser;
        });

        mockMvc.perform(MockMvcRequestBuilders.put("/api/user/" + 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Juan\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Juan"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
    }

    @Test
    void testDeleteUser() throws Exception {
        String id = "1L";

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/user/" + id))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
