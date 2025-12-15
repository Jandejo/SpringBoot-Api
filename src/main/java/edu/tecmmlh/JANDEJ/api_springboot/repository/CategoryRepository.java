package edu.tecmmlh.JANDEJ.api_springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.tecmmlh.JANDEJ.api_springboot.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    boolean existsByName(String name);
    List<Category> findByNameContainingIgnoreCase(String name);
    List<Category> findByPriceBetween(Double minPrice, Double maxPrice);
}
