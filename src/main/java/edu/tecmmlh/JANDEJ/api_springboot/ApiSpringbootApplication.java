package edu.tecmmlh.JANDEJ.api_springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import edu.tecmmlh.JANDEJ.api_springboot.model.Role;
import edu.tecmmlh.JANDEJ.api_springboot.model.User;
import edu.tecmmlh.JANDEJ.api_springboot.repository.RoleRepository;
import edu.tecmmlh.JANDEJ.api_springboot.repository.UserRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@SpringBootApplication
public class ApiSpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiSpringbootApplication.class, args);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
	CommandLineRunner init(RoleRepository roleRepo, UserRepository userRepo, PasswordEncoder encoder) {
		return args -> {
			Role adminRole = roleRepo.findByName("ROLE_ADMIN");
			if (adminRole == null) {
				adminRole = new Role();
				adminRole.setName("ROLE_ADMIN");
				adminRole = roleRepo.save(adminRole);
				System.out.println("Rol ROLE_ADMIN creado");
			}

			Role userRole = roleRepo.findByName("ROLE_USER");
			if (userRole == null) {
				userRole = new Role();
				userRole.setName("ROLE_USER");
				userRole = roleRepo.save(userRole);
				System.out.println("Rol ROLE_USER creado");
			}

			if (userRepo.findByUsername("admin").isEmpty()) {
				User admin = new User();
				admin.setUsername("admin");
				admin.setPassword(encoder.encode("admin123"));
				admin.setRoles(Set.of(adminRole));
				userRepo.save(admin);
				System.out.println("Usuario 'admin' creado con contraseña 'admin123'");
			} else {
				System.out.println("El usuario 'admin' ya existe.");
			}
		};
	}
}