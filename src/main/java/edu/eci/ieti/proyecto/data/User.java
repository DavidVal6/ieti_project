package edu.eci.ieti.proyecto.data;

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
    private Long id;

    @OneToMany(mappedBy = "plantations", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Plantation> plantations;

    private String name;
    private String email;
    private Long phoneNumber;
    private Long numberOfHarverst;
    private Double harvestPercentage;
}
