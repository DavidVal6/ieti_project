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
    private Long phoneNumber;
    private Long numberOfHarverst;
    private Double harvestPercentage;

    public User(String name, 
            String email, 
            Long phoneNumber, 
            Long numberOfHarverst,
            Double harvestPercentage) {
        this.plantations = new ArrayList<Plantation>();
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.numberOfHarverst = numberOfHarverst;
        this.harvestPercentage = harvestPercentage;
    }

    public User() {
    }

    // public User(UserDto userDto) {
    //     this.userDto = userDto;
    //     this.name = userDto.getName();
    //     this.email = userDto.getEmail();
    //     this.phoneNumber = userDto.getPhoneNumber();
    //     this.numberOfHarverst = userDto.getNumberOfHarvests();
    //     this.harvestPercentage = userDto.getHarvestPercentage();
    //     this.plantations = new ArrayList<Plantation>();
    // }
}
