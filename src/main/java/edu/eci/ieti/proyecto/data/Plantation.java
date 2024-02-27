package edu.eci.ieti.proyecto.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Plantation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    @OneToMany(mappedBy = "plants", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Plant> plants;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    private Long area; // 20X30 MTS^2 o 40X60 MTS^2
    private double irrigationPercentage;
    private int irrigationFrequency;
    private double fertilizationPercentage;
    private Date initDate;
    private String location;

    public Plantation(User user, Long area, double irrigationPercentage, int irrigationFrequency,
            double fertilizationPercentage, Date initDate, String location) {
        this.user = user;
        this.area = area;
        this.irrigationPercentage = irrigationPercentage;
        this.irrigationFrequency = irrigationFrequency;
        this.fertilizationPercentage = fertilizationPercentage;
        this.initDate = initDate;
        this.location = location;
        this.plants = new ArrayList<Plant>();
    }

    public Plantation() {
    }
}
