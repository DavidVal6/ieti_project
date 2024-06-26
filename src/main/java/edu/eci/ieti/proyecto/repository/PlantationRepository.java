package edu.eci.ieti.proyecto.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import edu.eci.ieti.proyecto.data.Plantation;

@Repository
public interface PlantationRepository extends MongoRepository<Plantation, String> {

}
