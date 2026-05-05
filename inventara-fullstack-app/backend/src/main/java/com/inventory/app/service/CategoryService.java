package com.inventory.app.service;

import com.inventory.app.dto.CategoryDTO;
import com.inventory.app.entity.Category;
import com.inventory.app.exception.DuplicateResourceException;
import com.inventory.app.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryDTO createCategory(CategoryDTO dto) {
        if (categoryRepository.existsByName(dto.getName())) {
            throw new DuplicateResourceException("Category with name '" + dto.getName() + "' already exists");
        }
        Category category = Category.builder()
                .name(dto.getName())
                .build();
        Category saved = categoryRepository.save(category);
        return toDTO(saved);
    }

    @Transactional(readOnly = true)
    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public CategoryDTO toDTO(Category category) {
        return CategoryDTO.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }
}
