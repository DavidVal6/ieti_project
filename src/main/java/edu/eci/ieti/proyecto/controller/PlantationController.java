package edu.eci.ieti.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/api/plantation")
public class PlantationController {

    private final PlantationService plantationService;

    public PlantationController(@Autowired PlantationService plantationService){
        this.plantationService = plantationService;
    }

    @GetMapping
    public List<Plantation> getAllPlantations() {
        return plantationService.getAllPlantation();
    }

    @PostMapping
    public Plantation createPlantation(@RequestBody Plantation plantation) {
        return plantationService.createPlantation(plantation);
    }

    @GetMapping("/{id}")
    public Plantation getPlantationById(@PathVariable Long id) throws PlantsException {
        return plantationService.getPlantationById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Plantation updatePlantation(@PathVariable Long id, @RequestBody Plantation plantation) throws PlantsException {
        return plantationService.updatePlantation(plantation, id);
    }

    @DeleteMapping("/{id}")
    public void deletePlantation(@PathVariable Long id) throws PlantsException {
        plantationService.deletePlantation(id);
    }
}