package edu.eci.ieti.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.eci.ieti.proyecto.controller.models.AuthResponse;
import edu.eci.ieti.proyecto.controller.models.AuthenticationRequest;
import edu.eci.ieti.proyecto.controller.models.RegisterRequest;
import edu.eci.ieti.proyecto.service.AuthService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request){
        System.out.println("Esto es en el controlador " + request.getPassword());
        return ResponseEntity.ok(authService.register(request));
    } 
    @PostMapping("/Authenticate")
    public ResponseEntity<AuthResponse> authenticate(@RequestBody AuthenticationRequest request){
        return ResponseEntity.ok(authService.authenticate(request));
    }

    @PostMapping("/test")
    public ResponseEntity<String> test(@RequestBody RegisterRequest request){
        if (request != null) {
            System.out.println(request + "");
            return ResponseEntity.ok(request.getName() + " " + request.getEmail() + " " + request.getPassword());
        } else {
            return ResponseEntity.ok("Request es nulo");
        }
    }
}
