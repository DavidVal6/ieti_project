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
import java.util.Optional;

import edu.eci.ieti.proyecto.data.Plantation;
import edu.eci.ieti.proyecto.data.User;
import edu.eci.ieti.proyecto.data.dto.PlantationDto;
import edu.eci.ieti.proyecto.exceptions.PlantException;
import edu.eci.ieti.proyecto.exceptions.UserException;
import edu.eci.ieti.proyecto.service.PlantationService;
import edu.eci.ieti.proyecto.service.UserService;

@RestController
@RequestMapping("/api/plantations")
public class PlantationController {

    private final UserService userService;

    private final PlantationService plantationService;

    public PlantationController(@Autowired PlantationService plantationService, @Autowired UserService userService) {
        this.plantationService = plantationService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<Plantation>> getAllPlantations() {
        return ResponseEntity.ok(plantationService.getAllPlantation());
    }

    @PostMapping
    public ResponseEntity<Plantation> createPlantation(@RequestBody PlantationDto plantationDto) throws UserException {
        User user = userService.findUserById(plantationDto.getUserId()).orElse(null);
        Plantation plantation = new Plantation(user, plantationDto.getArea(), plantationDto.getIrrigationPercentage(),
                plantationDto.getIrrigationFrequency(), plantationDto.getFertilizationPercentage(),
                plantationDto.getInitDate(), plantationDto.getLocation());
        return ResponseEntity.ok(plantationService.createPlantation(plantation));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Plantation> getPlantationById(@PathVariable Long id) throws PlantException {
        return ResponseEntity.ok(plantationService.getPlantationById(id).orElse(null));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Plantation> updatePlantation(@PathVariable Long id, @RequestBody Plantation plantation)
            throws PlantException {
        Plantation updatedPlantation = plantationService.updatePlantation(id, plantation);
        return ResponseEntity.ok(updatedPlantation);
    }

    @DeleteMapping("/{id}")
    public void deletePlantation(@PathVariable Long id) throws PlantException {
        plantationService.deletePlantation(id);
    }
}