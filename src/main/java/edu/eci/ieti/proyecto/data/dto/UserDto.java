package edu.eci.ieti.proyecto.data.dto;

public class UserDto {

    private String name;
    private String email;
    private String phoneNumber;
    private String numberOfHarvest;
    private String harvestPercentage;

    public UserDto(String name,
            String email,
            String phoneNumber,
            String numberOfHarvest,
            String harvestPercentage) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.numberOfHarvest = numberOfHarvest;
        this.harvestPercentage = harvestPercentage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getPhoneNumber() {
        return Long.parseLong(phoneNumber);
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Long getNumberOfHarvests() {
        return Long.parseLong(numberOfHarvest);
    }

    public void setNumberOfHarvests(String numberOfHarverst) {
        this.numberOfHarvest = numberOfHarverst;
    }

    public Double getHarvestPercentage() {
        return Double.parseDouble(harvestPercentage);
    }

    public void setHarvestPercentage(String harvestPercentage) {
        this.harvestPercentage = harvestPercentage;
    }
}
