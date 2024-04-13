package edu.eci.ieti.proyecto.controller.models;

import lombok.*;

@Builder
@Data
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class RegisterRequest {
    private String name;
    private String email;
    private String password;
}
