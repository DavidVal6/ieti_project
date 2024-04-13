package edu.eci.ieti.proyecto.service;

import edu.eci.ieti.proyecto.controller.models.AuthResponse;
import edu.eci.ieti.proyecto.controller.models.AuthenticationRequest;
import edu.eci.ieti.proyecto.controller.models.RegisterRequest;

public interface AuthService {

    AuthResponse register (RegisterRequest request);

    AuthResponse authenticate(AuthenticationRequest request);

    
}
