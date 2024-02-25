package edu.eci.ieti.proyecto.exceptions;

public class PlantsException extends Exception {
    
    public static String PLANT_NOT_FOUND="the plant you are looking for has not been found";
    public static String PLANTATION_NOT_FOUND = "The plantation you are searching for has not been found";

    public PlantsException(String  message) {
        super(message);
    }
}

