package com.whale.shopquanao.controller;

import com.whale.shopquanao.dto.request.ProductRequest;
import com.whale.shopquanao.dto.response.ProductResponse;
import com.whale.shopquanao.service.iservice.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/product")
public class ProductController {

    private final ProductService productService;

    @GetMapping(value = "/index")
    public ResponseEntity<List<ProductResponse>> index() {
        List<ProductResponse> products = productService.getAllProduct();
        return ResponseEntity.ok(products);
    }

    @GetMapping(value = "/find_by_id/{id}")
    public ResponseEntity<ProductResponse> findById(@PathVariable(name = "id") Integer id) {
        ProductResponse product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }

    @PostMapping(value = "/store")
    public ResponseEntity<?> store(@Valid @RequestBody ProductRequest productRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getFieldErrors().stream()
                    .map(FieldError::getDefaultMessage)
                    .toList();
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
        ProductResponse productResponse = productService.storeProduct(productRequest);
        return new ResponseEntity<>(productResponse, HttpStatus.CREATED);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<?> update(
            @PathVariable(name = "id") Integer id,
            @Valid @RequestBody ProductRequest productRequest,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getFieldErrors().stream()
                   .map(FieldError::getDefaultMessage)
                   .toList();
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
        ProductResponse productResponse = productService.updateProduct(id, productRequest);
        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable(name = "id") Integer id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok("Delete product successfully with id: " + id);
    }
}
