package edu.eci.ieti.proyecto.data;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Plant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String name;
    private String plantType;
    private long altitude;
    private long temperature;
    private long humidity;

    public Plant(String name, String plantType, long altitude, long temperature, long humidity) {
        this.name = name;
        this.plantType = plantType;
        this.altitude = altitude;
        this.temperature = temperature;
        this.humidity = humidity;
    }

    public String getName() {
        return name;
    }

    public String getPlantType() {
        return plantType;
    }

    public long getAltitude() {
        return altitude;
    }

    public long getTemperature() {
        return temperature;
    }

    public long getHumidity() {
        return humidity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPlantType(String plantType) {
        this.plantType = plantType;
    }

    public void setAltitude(long altitude) {
        this.altitude = altitude;
    }

    public void setTemperature(long temperature) {
        this.temperature = temperature;
    }

    public void setHumidity(long humidity) {
        this.humidity = humidity;
    }

    // @ManyToOne(cascade = CascadeType.ALL)
    // @JoinColumn(name = "plantation_id", nullable = false)
    // private Plantation plantation;

}
