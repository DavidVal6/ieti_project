package edu.eci.ieti.proyecto.data.dto;

import java.util.Date;

public class PlantationDto {
    private String userId;
    private String area; // 20X30 MTS^2 o 40X60 MTS^2
    private String irrigationPercentage;
    private String irrigationFrequency;
    private String fertilizationPercentage;
    private String initDate;
    private String location;

    public PlantationDto(String userId, String area, String irrigationPercentage, String irrigationFrequency,
            String fertilizationPercentage, String initDate, String location) {
        this.userId = userId;
        this.area = area;
        this.irrigationPercentage = irrigationPercentage;
        this.irrigationFrequency = irrigationFrequency;
        this.fertilizationPercentage = fertilizationPercentage;
        this.initDate = initDate;
        this.location = location;
    }

    public String getUserId() {
        return userId;
    }

    public double getIrrigationPercentage() {
        return Double.parseDouble(irrigationPercentage);
    }

    public int getIrrigationFrequency() {
        return Integer.parseInt(irrigationFrequency);
    }

    public Date getInitDate() {
        return new Date(initDate);
    }

    public long getArea() {
        return Long.parseLong(area);
    }

    public double getFertilizationPercentage() {
        return Double.parseDouble(fertilizationPercentage);
    }

    public String getLocation() {
        return location;
    }

}
