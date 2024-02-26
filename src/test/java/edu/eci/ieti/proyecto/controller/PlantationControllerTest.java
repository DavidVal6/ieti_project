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
        plantation.setArea(600L);

        when(plantationService.createPlantation(plantation)).thenAnswer(invocation -> {
            Plantation newPlantation = invocation.getArgument(0);
            newPlantation.setId(1L);
            return newPlantation;
        });

        mockMvc.perform(MockMvcRequestBuilders.post("/api/plantations")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"area\": \"600\" }"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.area").value(600));
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
        plantation.setArea(600L);

        when(plantationService.updatePlantation(id, plantation)).thenReturn(plantation);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/plantations/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"area\": \"600\" }"))
                .andDo(result -> {
                    String content = result.getResponse().getContentAsString();
                    System.out.println("Response JSON: " + content);
                })
                .andExpect(MockMvcResultMatchers.status().isOk());                
    }

    @Test
    void testDeletePlantation() throws Exception {
        Long id = 1L;

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/plantations/" + id))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
