package edu.eci.ieti.proyecto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<Plant>> getAllPlants() {
        return ResponseEntity.ok(plantService.getAllPlants());
    }

    @PostMapping
    public ResponseEntity<Plant> createPlant(@RequestBody Plant plant) {
        return ResponseEntity.ok(plantService.createPlant(plant));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Plant> getPlantById(@PathVariable Long id) throws PlantsException {
        return ResponseEntity.ok(plantService.findPlantById(id).orElse(null));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Plant> updatePlant(@PathVariable Long id, @RequestBody Plant plant) throws PlantsException {
        return ResponseEntity.ok(plantService.updatePlant(plant,id));
    }

    @DeleteMapping("/{id}")
    public void deletePlant(@PathVariable Long id) throws PlantsException {
        plantService.deletePlant(id);
    }
}