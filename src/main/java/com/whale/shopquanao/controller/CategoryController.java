package com.whale.shopquanao.controller;

import com.whale.shopquanao.dto.request.CategoryRequest;
import com.whale.shopquanao.dto.response.CategoryResponse;
import com.whale.shopquanao.service.iservice.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/index")
    public ResponseEntity<List<CategoryResponse>> index() {
        List<CategoryResponse> categories = categoryService.getAllCategory();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/find_by_id/{id}")
    public ResponseEntity<CategoryResponse> findById(@PathVariable("id") Integer id) {
        CategoryResponse category = categoryService.getCategoryById(id);
        return ResponseEntity.ok(category);
    }

    @PostMapping("/store")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> store(@Valid @RequestBody CategoryRequest categoryRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return handleValidationErrors(bindingResult);
        }
        CategoryResponse categoryResponse = categoryService.storeCategory(categoryRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryResponse);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(
            @PathVariable("id") Integer id,
            @Valid @RequestBody CategoryRequest categoryRequest,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return handleValidationErrors(bindingResult);
        }
        CategoryResponse categoryResponse = categoryService.updateCategory(id, categoryRequest);
        return ResponseEntity.ok(categoryResponse);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Integer id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.ok("Delete category successfully with id: " + id);
    }

    // Phương thức riêng để xử lý lỗi ràng buộc
    private ResponseEntity<List<String>> handleValidationErrors(BindingResult bindingResult) {
        List<String> errors = bindingResult.getFieldErrors().stream()
                .map(FieldError::getDefaultMessage)
                .toList();
        return ResponseEntity.badRequest().body(errors);
    }
}
