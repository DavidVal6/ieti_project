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
    private Long id;

    private String name;
    private String plantType;
    private String altitude;
    private String temperature;
    private String  humidity;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "plantation_id", nullable = false)
    private Plantation plantation;
}