package edu.eci.ieti.proyecto.service;

import java.util.Optional;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.eci.ieti.proyecto.data.Plantation;
import edu.eci.ieti.proyecto.exceptions.PlantsException;
import edu.eci.ieti.proyecto.repository.PlantationRepository;

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

    public  Plantation updatePlantation(Plantation plantation, Long id) throws PlantsException{
        Optional<Plantation> optionalPlantation = plantationRepository.findById(id);
        if(!optionalPlantation.isPresent()){
            throw new PlantsException(PlantsException.PLANTATION_NOT_FOUND);
        }else{
            return plantationRepository.save(plantation);
        }
    }

    
}
