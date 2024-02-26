package edu.eci.ieti.proyecto.data.dto;

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

    public String getName() {
        return name;
    }

    public String getPlantType() {
        return plantType;
    }

    public long getAltitude() {
        return Long.parseLong(altitude);
    }

    public long getTemperature() {
        return Long.parseLong(temperature);
    }

    public long getHumidity() {
        return Long.parseLong(humidity);
    }
}
