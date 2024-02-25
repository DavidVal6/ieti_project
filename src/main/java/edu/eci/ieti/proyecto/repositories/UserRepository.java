package edu.eci.ieti.proyecto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.eci.ieti.proyecto.data.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    
}

