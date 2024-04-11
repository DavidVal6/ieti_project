package edu.eci.ieti.proyecto.data;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @OneToMany(mappedBy = "plantations", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Plantation> plantations;

    private String name;
    private String email;
    private String password;
    private Long phoneNumber;
    private Long numberOfHarvest;
    private Double harvestPercentage;
    
    
    

    public User(String name, 
            String email, 
            Long phoneNumber, 
            Long numberOfHarvest,
            Double harvestPercentage) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.plantations = new ArrayList<Plantation>();
        this.numberOfHarvest = numberOfHarvest;
        this.harvestPercentage = harvestPercentage;
    }

    public User() {
    }
}
