package edu.eci.ieti.proyecto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.List;
import edu.eci.ieti.proyecto.data.Plant;
import edu.eci.ieti.proyecto.exceptions.PlantException;
import edu.eci.ieti.proyecto.repositories.PlantRepository;

@Service
public class PlantService {

    private final PlantRepository plantRepository;

    public PlantService(@Autowired PlantRepository plantRepository) {
        this.plantRepository = plantRepository;
    }

    public Plant createPlant(Plant plant) {
        return plantRepository.save(plant);
    }

    public Optional<Plant> findPlantById(String id) throws PlantException {
        if (plantRepository.findById(id).isPresent()) {
            return plantRepository.findById(id);
        } else {
            throw new PlantException(PlantException.PLANT_NOT_FOUND);
        }
    }

    public List<Plant> getAllPlants() {
        return plantRepository.findAll();
    }

    public Plant updatePlant(Plant plant, String id) throws PlantException {
        Optional<Plant> optionalPlant = findPlantById(id);
        if (!optionalPlant.isPresent()) {
            throw new PlantException(PlantException.PLANT_NOT_FOUND);
        } else {
            return plantRepository.save(plant);
        }
    }

    public void deletePlant(String id) throws PlantException {
        Optional<Plant> plant = plantRepository.findById(id);
        if (!plant.isPresent()) {
            throw new PlantException(PlantException.PLANT_NOT_FOUND);
        } else {
            plantRepository.deleteById(id);
        }
    }

}
