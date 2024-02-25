package edu.eci.ieti.proyecto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.eci.ieti.proyecto.data.Plant;
import edu.eci.ieti.proyecto.data.Plantation;
import edu.eci.ieti.proyecto.exceptions.PlantsException;
import edu.eci.ieti.proyecto.service.PlantService;
import edu.eci.ieti.proyecto.service.PlantationService;

@RestController
@RequestMapping("/api/plant")
public class PlantController {

    private PlantService plantService;

    public PlantController(@Autowired PlantService plantService) {
        this.plantService = plantService;
    }

    @GetMapping
    public List<Plant> getAllPlants() {
        return plantService.getAllPlants();
    }

    @PostMapping
    public Plant createPlant(@RequestBody Plant plant) {
        return plantService.createPlant(plant);
    }

    @GetMapping("/{id}")
    public Plant getPlantById(@PathVariable Long id) throws PlantsException {
        return plantService.findPlantById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Plant updatePlant(@PathVariable Long id, @RequestBody Plant plant) throws PlantsException {
        return plantService.updatePlant(plant,id);
    }

    @DeleteMapping("/{id}")
    public void deletePlant(@PathVariable Long id) throws PlantsException {
        plantService.deletePlant(id);
    }
}