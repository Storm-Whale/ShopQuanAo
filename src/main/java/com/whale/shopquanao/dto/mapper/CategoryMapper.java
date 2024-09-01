package com.whale.shopquanao.dto.mapper;

import com.whale.shopquanao.dto.request.CategoryRequest;
import com.whale.shopquanao.dto.response.CategoryResponse;
import com.whale.shopquanao.dto.response.ProductResponse;
import com.whale.shopquanao.entity.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CategoryMapper {

    private final ProductMapper productMapper;

    public CategoryResponse toCategoryResponse(Category category) {
        List<ProductResponse> listProductResponse = new ArrayList<>();
        if (category.getListProduct() != null && !category.getListProduct().isEmpty()) {
            listProductResponse = category.getListProduct().stream()
                    .map(productMapper::toProductResponse)
                    .toList();
        }
        return CategoryResponse.builder()
                .id(category.getId())
                .categoryName(category.getCategoryName())
                .listProductResponse(listProductResponse)
                .build();
    }

    public Category toCategory(CategoryRequest categoryRequest) {
        return Category.builder()
                .categoryName(categoryRequest.getCategoryName())
                .build();
    }

    public Category toCategory(Integer id, CategoryRequest categoryRequest) {
        return Category.builder()
                .id(id)
                .categoryName(categoryRequest.getCategoryName())
                .build();
    }
}
