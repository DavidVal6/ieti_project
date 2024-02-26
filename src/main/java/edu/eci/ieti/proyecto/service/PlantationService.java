package edu.eci.ieti.proyecto.service;

import java.util.Optional;
import java.lang.reflect.Field;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.eci.ieti.proyecto.data.Plantation;
import edu.eci.ieti.proyecto.exceptions.PlantsException;
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

    public Optional<Plantation> getPlantationById(Long Id) throws PlantsException{
        if(plantationRepository.findById(Id).isPresent()){
            return plantationRepository.findById(Id);
        }else{
            throw new PlantsException(PlantsException.PLANTATION_NOT_FOUND);
        }
    }
    public List<Plantation> getAllPlantation(){
        return plantationRepository.findAll();
    }

    public  void deletePlantation(Long id)throws PlantsException {
        try{
            plantationRepository.deleteById(id);
        }catch (Exception e){
            throw new PlantsException("Error al eliminar la plantacion");
        }
    }

    public Plantation updatePlantation(Long id, Plantation newPlantation) throws PlantsException {
        Optional<Plantation> optionalPlantation = plantationRepository.findById(id);
        if (!optionalPlantation.isPresent()) {
            throw new PlantsException("The plantation you are searching for has not been found");
        }
        Plantation existingPlantation = optionalPlantation.get();
    
        for (Field field : Plantation.class.getDeclaredFields()) {
            if (field.getName().equals("id")) {
                continue;
            }

            field.setAccessible(true);

            Object newValue;
            try {
                newValue = field.get(newPlantation);
                field.set(existingPlantation, newValue);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

            // Update the value of the attribute in the existingPlantation object
            
        }
        return plantationRepository.save(existingPlantation);
    }
}
