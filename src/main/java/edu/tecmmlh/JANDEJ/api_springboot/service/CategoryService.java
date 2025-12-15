package edu.tecmmlh.JANDEJ.api_springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import edu.tecmmlh.JANDEJ.api_springboot.model.Category;
import edu.tecmmlh.JANDEJ.api_springboot.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Optional<Category> getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }

    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    public Category updateCategory(Long id, Category categoryDetails) {
        categoryDetails.setId(id);
        return categoryRepository.save(categoryDetails);
    }

    public boolean deleteCategory(Long id) {
        if (categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean categoryExistByName(String name) {
        return categoryRepository.existsByName(name);
    }

    public List<Category> findByNameContainingIgnoreCase(String name) {
        return categoryRepository.findByNameContainingIgnoreCase(name);
    }

    public List<Category> findByPriceBetween(Double minPrice, Double maxPrice) {
        return categoryRepository.findByPriceBetween(minPrice, maxPrice);
    }
}
