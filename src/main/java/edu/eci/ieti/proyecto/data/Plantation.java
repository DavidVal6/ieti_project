package edu.eci.ieti.proyecto.data;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Plantation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(mappedBy = "plants", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Plant> plants;

    private String size; // 20X30 MTS^2 o 40X60 MTS^2
    private double hidratationPercentage;
    private double fertilizationPercentage;
    private int frequencyOfWateringDays;

}
