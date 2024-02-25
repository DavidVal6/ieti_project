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
        plantation.setSize("20X30 MTS^2");

        when(plantationService.createPlantation(plantation)).thenReturn(plantation);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/plantations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"size\": \"20X30 MTS^2\" }"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size").value("20X30 MTS^2"));
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
}
