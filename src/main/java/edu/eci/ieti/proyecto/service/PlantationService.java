package edu.eci.ieti.proyecto.service;

import java.util.Optional;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.eci.ieti.proyecto.data.Plantation;
import edu.eci.ieti.proyecto.exceptions.PlantException;
import edu.eci.ieti.proyecto.repositories.PlantationRepository;

@Service
public class PlantationService {

    private final PlantationRepository plantationRepository;

    public PlantationService(@Autowired PlantationRepository plantationRepository){
        this.plantationRepository=plantationRepository;
    }

    public Plantation createPlantation(Plantation plantation){
        return  plantationRepository.save(plantation);
    }

    public Optional<Plantation> getPlantationById(Long Id) throws PlantException{
        if(plantationRepository.findById(Id).isPresent()){
            return plantationRepository.findById(Id);
        }else{
            throw new PlantException(PlantException.PLANTATION_NOT_FOUND);
        }
    }
    public List<Plantation> getAllPlantation(){
        return plantationRepository.findAll();
    }

    public  void deletePlantation(Long id)throws PlantException {
        try{
            plantationRepository.deleteById(id);
        }catch (Exception e){
            throw new PlantException("Error al eliminar la plantacion");
        }
    }

    public Plantation updatePlantation(Long id, Plantation newPlantation) throws PlantException {
        Optional<Plantation> optionalPlantation = plantationRepository.findById(id);
        if (!optionalPlantation.isPresent()) {
            throw new PlantException(PlantException.PLANTATION_NOT_FOUND);
        }
    
        Plantation existingPlantation = optionalPlantation.get();
        existingPlantation.setSize(newPlantation.getSize());
        existingPlantation.setHydratationPercentage(newPlantation.getHydratationPercentage());
        existingPlantation.setFertilizationPercentage(newPlantation.getFertilizationPercentage());
    
        return plantationRepository.save(existingPlantation);
    }
}
