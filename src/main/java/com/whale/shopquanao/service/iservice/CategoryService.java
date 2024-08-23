package com.whale.shopquanao.service.iservice;

import com.whale.shopquanao.dto.request.CategoryRequest;
import com.whale.shopquanao.dto.response.CategoryResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {

    List<CategoryResponse> getAllCategory();

    CategoryResponse getCategoryById(Integer id);

    CategoryResponse storeCategory(CategoryRequest categoryRequest);

    CategoryResponse updateCategory(int id, CategoryRequest categoryRequest);

    void deleteCategory(int id);
}
