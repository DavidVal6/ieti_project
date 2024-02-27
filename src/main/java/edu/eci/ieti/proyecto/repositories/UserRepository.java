package edu.eci.ieti.proyecto.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import edu.eci.ieti.proyecto.data.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

}
