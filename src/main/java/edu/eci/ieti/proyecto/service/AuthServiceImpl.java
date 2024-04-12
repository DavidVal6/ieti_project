package edu.eci.ieti.proyecto.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import edu.eci.ieti.proyecto.config.JwtService;
import edu.eci.ieti.proyecto.controller.models.AuthResponse;
import edu.eci.ieti.proyecto.controller.models.AuthenticationRequest;
import edu.eci.ieti.proyecto.controller.models.RegisterRequest;
import edu.eci.ieti.proyecto.data.Role;
import edu.eci.ieti.proyecto.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthResponse register(RegisterRequest request) {
        var user = User.builder()
                .username(request.getName())
                .password(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .role(Role.USER)
                .build();

        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthResponse.builder()
                .token(jwtToken).build();
    }

    @Override
    public AuthResponse authenticate(AuthenticationRequest request) {
         authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
         );
         var user = userRepository.findByEmail(request.getEmail()).orElseThrow();
         var jwToken = jwtService.generateToken(user);
         return AuthResponse.builder().token(jwToken).build();
    }

}
