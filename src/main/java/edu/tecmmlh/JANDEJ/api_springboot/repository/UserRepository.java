package edu.tecmmlh.JANDEJ.api_springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import edu.tecmmlh.JANDEJ.api_springboot.model.User;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}