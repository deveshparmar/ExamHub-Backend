package com.examhub.backend.service.impl;

import com.examhub.backend.model.Category;
import com.examhub.backend.repository.CategoryRepository;
import com.examhub.backend.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Set;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category addCategory(Category category) {
        if(categoryRepository.existsByTitle(category.getTitle())){
            return null;
        }
        return this.categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Category category) {
        return this.categoryRepository.save(category);
    }

    @Override
    public Set<Category> getCategories() {
        return new LinkedHashSet<>(this.categoryRepository.findAll());
    }

    @Override
    public Category getCategory(Long id) {
        if(categoryRepository.findById(id).isPresent()) {
            return categoryRepository.findById(id).get();
        }
        return null;
    }

    @Override
    public void deleteCategory(Long id) {
        Category category = new Category();
        category.setCId(id);
        this.categoryRepository.delete(category);
    }
}
