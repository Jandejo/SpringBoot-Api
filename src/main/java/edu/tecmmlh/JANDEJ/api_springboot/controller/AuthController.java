package edu.tecmmlh.JANDEJ.api_springboot.controller;

import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import edu.tecmmlh.JANDEJ.api_springboot.model.User;
import edu.tecmmlh.JANDEJ.api_springboot.model.Role;
import edu.tecmmlh.JANDEJ.api_springboot.repository.UserRepository;
import edu.tecmmlh.JANDEJ.api_springboot.repository.RoleRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepo;
    private final RoleRepository roleRepo;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        if(userRepo.findByUsername(user.getUsername()).isPresent()) {
            return "Usuario ya existe";
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role userRole = roleRepo.findByName("ROLE_USER");
        user.setRoles(Set.of(userRole));
        userRepo.save(user);
        return "Usuario registrado";
    }
}
