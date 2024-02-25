package edu.eci.ieti.proyecto.controller;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.util.List;
import java.util.Arrays;
import java.util.Optional;
import org.springframework.http.MediaType;

import edu.eci.ieti.proyecto.data.Plantation;
import edu.eci.ieti.proyecto.service.PlantationService;

@WebMvcTest(PlantationController.class)
public class PlantationControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PlantationService plantationService;

    @Test
    void testCreatePlantation() throws Exception {
        Plantation plantation = new Plantation();
        plantation.setSize(600);

        // Modifica el método createPlantation para que devuelva el objeto creado
        when(plantationService.createPlantation(plantation)).thenAnswer(invocation -> {
            Plantation newPlantation = invocation.getArgument(0);
            newPlantation.setId(1L); // Asigna un id al objeto creado
            return newPlantation;
        });

        mockMvc.perform(MockMvcRequestBuilders.post("/api/plantations")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"size\": \"600\" }"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size").value(600))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1)); // Asegura que el id sea devuelto
    }

    @Test
    void testGetAllPlantations() throws Exception {
        Plantation plantation1 = new Plantation();
        Plantation plantation2 = new Plantation();
        List<Plantation> plantations = Arrays.asList(plantation1, plantation2);

        when(plantationService.getAllPlantation()).thenReturn(plantations);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/plantations"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2));
    }

    @Test
    void testGetPlantationById() throws Exception {
        Long id = 1L;
        Plantation plantation = new Plantation();
        plantation.setId(id);

        when(plantationService.getPlantationById(id)).thenReturn(Optional.of(plantation));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/plantations/" + id))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(id));
    }

    @Test
    void testUpdatePlantation() throws Exception {
        Long id = 1L;
        Plantation plantation = new Plantation();
        plantation.setId(id);
        plantation.setSize(600);

        when(plantationService.updatePlantation(id, plantation)).thenReturn(plantation);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/plantations/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"size\": \"600\" }"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(id))
                .andExpect(MockMvcResultMatchers.jsonPath("$.size").value(600));
    }

    @Test
    void testDeletePlantation() throws Exception {
        Long id = 1L;

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/plantations/" + id))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
