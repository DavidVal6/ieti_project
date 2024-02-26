package edu.eci.ieti.proyecto.controller;

import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.http.MediaType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import edu.eci.ieti.proyecto.data.Plant;
import edu.eci.ieti.proyecto.service.PlantService;
import edu.eci.ieti.proyecto.service.PlantationService;

@WebMvcTest(PlantController.class)
public class PlantControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PlantService plantService;

    @Test
    void testGetAllPlants() throws Exception {
        Plant plant1 = new Plant();
        Plant plant2 = new Plant();
        List<Plant> plants = Arrays.asList(plant1, plant2);

        when(plantService.getAllPlants()).thenReturn(plants);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/plant"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2));
    }

    @Test
    void testCreatePlant() throws Exception {
        Plant plant = new Plant();
        plant.setId(1L);

        when(plantService.createPlant(plant)).thenReturn(plant);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/plant")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"id\": 1 }"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
    }

    @Test
    void testGetPlantById() throws Exception {
        Long id = 1L;
        Plant plant = new Plant();
        plant.setId(id);

        when(plantService.findPlantById(id)).thenReturn(Optional.of(plant));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/plant/" + id))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(id));
    }

    @Test
    void testUpdatePlant() throws Exception {
        Long id = 1L;
        Plant plant = new Plant();
        plant.setId(id);

        when(plantService.updatePlant(plant, id)).thenReturn(plant);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/plant/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"id\": 1 }"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(id));
    }

    @Test
    void testDeletePlant() throws Exception {
        Long id = 1L;

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/plant/" + id))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
