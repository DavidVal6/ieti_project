package edu.eci.ieti.proyecto.data.dto;

import lombok.Data;

@Data

public class PlantDto {
    private String name;
    private String plantType;
    private String altitude;
    private String temperature;
    private String humidity;

    public PlantDto(String name, String plantType, String altitude, String temperature, String humidity) {
        this.name = name;
        this.plantType = plantType;
        this.altitude = altitude;
        this.temperature = temperature;
        this.humidity = humidity;
    }
    public PlantDto(){

    }

    public String getName() {
        return name;
    }

    public String getPlantType() {
        return plantType;
    }

    public long getAltitude() {
        return altitude != null ? Long.parseLong(altitude) : 0;
    }
    
    public long getTemperature() {
        return temperature != null ? Long.parseLong(temperature) : 0;
    }
    
    public long getHumidity() {
        return humidity != null ? Long.parseLong(humidity) : 0;
    }
}
