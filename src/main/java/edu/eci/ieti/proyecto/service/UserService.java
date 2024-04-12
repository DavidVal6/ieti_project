package edu.eci.ieti.proyecto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.List;

import edu.eci.ieti.proyecto.data.User;
import edu.eci.ieti.proyecto.exceptions.UserException;
import edu.eci.ieti.proyecto.repositories.UserRepository;

@Service
public class UserService{

    private final UserRepository userRepository;

    public UserService(@Autowired UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }


    public Optional<User> findUserById(String id) throws UserException {
        if (userRepository.findById(id).isPresent()) {
            return userRepository.findById(id);
        } else {
            throw new UserException(UserException.USER_NOT_FOUND);
        }
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User updateUser(User user, String id) throws UserException {
        Optional<User> optionalUser = findUserById(id);
        if (!optionalUser.isPresent()) {
            throw new UserException(UserException.USER_NOT_FOUND);
        } else {
            return userRepository.save(user);
        }
    }

    public void deleteUser(String id) throws UserException {
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()) {
            throw new UserException(UserException.USER_NOT_FOUND);
        } else {
            userRepository.deleteById(id);
        }
    }

}
