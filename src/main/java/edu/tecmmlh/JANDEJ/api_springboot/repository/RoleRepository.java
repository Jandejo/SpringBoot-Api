package edu.tecmmlh.JANDEJ.api_springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import edu.tecmmlh.JANDEJ.api_springboot.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}