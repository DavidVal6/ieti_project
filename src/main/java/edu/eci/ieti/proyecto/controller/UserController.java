package edu.eci.ieti.proyecto.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import edu.eci.ieti.proyecto.data.Role;
import org.apache.catalina.connector.Response;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.eci.ieti.proyecto.data.User;
import edu.eci.ieti.proyecto.data.dto.UserDto;
import edu.eci.ieti.proyecto.exceptions.UserException;
import edu.eci.ieti.proyecto.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private UserService userService;

    public UserController(@Autowired UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserDto userDto) {
        User user = new User(userDto.getName(),
                userDto.getEmail(),
                userDto.getPhoneNumber(),
                userDto.getNumberOfHarvests(),
                userDto.getHarvestPercentage());
        return ResponseEntity.ok(userService.createUser(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable String id) throws UserException {
        return ResponseEntity.ok(userService.findUserById(id).orElse(null));
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable String id, @RequestBody UserDto userDto) throws UserException {
        User user = userService.findUserById(id).orElseThrow(() -> new UserException(UserException.USER_NOT_FOUND));
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setHarvestPercentage(userDto.getHarvestPercentage());
        return ResponseEntity.ok(userService.updateUser(user, id));
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable String id) throws UserException {
        userService.deleteUser(id);
    }

    @GetMapping("/mensaje")
    public ResponseEntity<?> getMensaje(){
        Map<String,String> mensaje = new HashMap<>();
        return ResponseEntity.ok(mensaje);         
    }
}