package edu.eci.ieti.proyecto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import edu.eci.ieti.proyecto.data.Plant;
import edu.eci.ieti.proyecto.exceptions.PlantsException;
import edu.eci.ieti.proyecto.repository.PlantRepository;

@Service
public class PlantService {

    private final PlantRepository plantRepository;

    public PlantService(@Autowired PlantRepository plantRepository){
        this.plantRepository = plantRepository;
    }

    public Plant createPlant(Plant plant){
        return plantRepository.save(plant);
    }
    public Optional<Plant> findPlantById(Long Id) throws PlantsException{
        if(plantRepository.findById(Id) != null){
            return plantRepository.findById(Id);
        }else{
            throw new PlantsException(PlantsException.PLANT_NOT_FOUND);
        }
    }
    public Plant updatePlant(Plant plant, Long id) throws PlantsException{
        Optional<Plant> optionalPlant= findPlantById(id);
        if(!optionalPlant.isPresent()){
            throw new PlantsException(PlantsException.PLANT_NOT_FOUND) ;
        }else{
            return plantRepository.save(plant);
        }
    }
    public void deletePlant(Long id) throws PlantsException{
        Optional<Plant> plant = plantRepository.findById(id);
        if (!plant.isPresent()){
            throw new PlantsException(PlantsException.PLANT_NOT_FOUND);
        }else{
            plantRepository.deleteById(id);
        }
    }
    
}
