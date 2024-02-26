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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Plant> getPlants() {
        return plants;
    }

    public void setPlants(List<Plant> plants) {
        this.plants = plants;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getArea() {
        return area;
    }

    public void setArea(Long area) {
        this.area = area;
    }

    public double getIrrigationPercentage() {
        return irrigationPercentage;
    }

    public void setIrrigationPercentage(double irrigationPercentage) {
        this.irrigationPercentage = irrigationPercentage;
    }

    public int getIrrigationFrequency() {
        return irrigationFrequency;
    }

    public void setIrrigationFrequency(int irrigationFrequency) {
        this.irrigationFrequency = irrigationFrequency;
    }

    public double getFertilizationPercentage() {
        return fertilizationPercentage;
    }

    public void setFertilizationPercentage(double fertilizationPercentage) {
        this.fertilizationPercentage = fertilizationPercentage;
    }

    public Date getInitDate() {
        return initDate;
    }

    public void setInitDate(Date initDate) {
        this.initDate = initDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
