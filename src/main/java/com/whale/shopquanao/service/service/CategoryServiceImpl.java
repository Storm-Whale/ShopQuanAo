package com.whale.shopquanao.service.service;

import com.whale.shopquanao.dto.mapper.CategoryMapper;
import com.whale.shopquanao.dto.request.CategoryRequest;
import com.whale.shopquanao.dto.response.CategoryResponse;
import com.whale.shopquanao.entity.Category;
import com.whale.shopquanao.exception.DataNotFoundException;
import com.whale.shopquanao.repository.CategoryRepository;
import com.whale.shopquanao.service.iservice.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public List<CategoryResponse> getAllCategory() {
        try {
            return categoryRepository.findAll().stream()
                    .map(categoryMapper::toCategoryResponse)
                    .collect(Collectors.toList());
        } catch (DataAccessException e) {
            throw new RuntimeException("Error retrieving categories from the database", e);
        }
    }

    @Override
    public CategoryResponse getCategoryById(Integer id) {
        try {
            return categoryRepository.findById(id)
                    .map(categoryMapper::toCategoryResponse)
                    .orElseThrow(() -> new DataNotFoundException("Category not found with id: " + id));
        } catch (DataAccessException e) {
            throw new RuntimeException("Error retrieving category from the database", e);
        }
    }

    @Override
    public CategoryResponse storeCategory(CategoryRequest categoryRequest) {
        try {
            if (categoryRepository.existsByCategoryName(categoryRequest.getCategoryName())) {
                throw new DuplicateKeyException("A category with the name '" + categoryRequest.getCategoryName() + "' already exists.");
            }

            Category category = categoryMapper.toCategory(categoryRequest);
            category = categoryRepository.save(category);

            return categoryMapper.toCategoryResponse(category);
        } catch (DataAccessException e) {
            throw new RuntimeException("Error storing category in the database", e);
        }
    }

    @Override
    public CategoryResponse updateCategory(int id, CategoryRequest categoryRequest) {
        try {
            Category category = categoryRepository.findById(id)
                    .orElseThrow(() -> new DataNotFoundException("Category not found with id: " + id));

            category.setCategoryName(categoryRequest.getCategoryName());
            category = categoryRepository.save(category);

            return categoryMapper.toCategoryResponse(category);
        } catch (DataAccessException e) {
            throw new RuntimeException("Error updating category in the database", e);
        }
    }

    @Override
    public void deleteCategory(int id) {
        try {
            if (!categoryRepository.existsById(id)) {
                throw new DataNotFoundException("Category not found with id: " + id);
            }
            categoryRepository.deleteById(id);
        } catch (DataAccessException e) {
            throw new RuntimeException("Error deleting category from the database", e);
        }
    }
}
