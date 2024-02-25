package edu.eci.ieti.proyecto.controller;

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
import java.util.List;

import edu.eci.ieti.proyecto.data.Plantation;
import edu.eci.ieti.proyecto.exceptions.PlantsException;
import edu.eci.ieti.proyecto.service.PlantationService;

@RestController
@RequestMapping("/api/plantations")
public class PlantationController {

    private final PlantationService plantationService;

    public PlantationController(@Autowired PlantationService plantationService){
        this.plantationService = plantationService;
    }

    @GetMapping
    public ResponseEntity<List<Plantation>> getAllPlantations() {
        return ResponseEntity.ok(plantationService.getAllPlantation());
    }

    @PostMapping
    public ResponseEntity<Plantation> createPlantation(@RequestBody Plantation plantation) {
        return ResponseEntity.ok(plantationService.createPlantation(plantation));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Plantation> getPlantationById(@PathVariable Long id) throws PlantsException {
        return ResponseEntity.ok(plantationService.getPlantationById(id).orElse(null));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Plantation> updatePlantation(@PathVariable Long id, @RequestBody Plantation plantation) throws PlantsException {
        return ResponseEntity.ok(plantationService.updatePlantation(id, plantation));
    }

    @DeleteMapping("/{id}")
    public void deletePlantation(@PathVariable Long id) throws PlantsException {
        plantationService.deletePlantation(id);
    }
}