package edu.eci.ieti.proyecto.controller.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    private String name;
    private String email;
    private String password;
    private Long phoneNumber;
    private Long numberOfHarvest;
    private Double harvestPercentage;
}
