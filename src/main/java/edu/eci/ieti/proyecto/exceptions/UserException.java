package edu.eci.ieti.proyecto.exceptions;

public class UserException extends Exception {
    
    public static String USER_NOT_FOUND="The user you are looking for has not been found";

    public UserException(String  message) {
        super(message);
    }
}