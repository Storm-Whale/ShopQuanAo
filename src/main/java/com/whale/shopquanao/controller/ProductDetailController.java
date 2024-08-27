package com.whale.shopquanao.controller;

import com.whale.shopquanao.dto.request.ProductDetailRequest;
import com.whale.shopquanao.dto.response.ProductDetailResponse;
import com.whale.shopquanao.service.iservice.ProductDetailService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/product-detail")
public class ProductDetailController {

    private final ProductDetailService productDetailService;

    @GetMapping(value = "/index")
    public ResponseEntity<List<ProductDetailResponse>> index() {
        List<ProductDetailResponse> product_detail = new ArrayList<>();
        return new ResponseEntity<>(product_detail, HttpStatus.OK);
    }

    @GetMapping(value = "/find_by_id/{id}")
    public ResponseEntity<ProductDetailResponse> findById(@PathVariable(name = "id") Integer id) {
        ProductDetailResponse product_detail = productDetailService.getProductDetailById(id);
        return new ResponseEntity<>(product_detail, HttpStatus.OK);
    }

    @PostMapping(value = "/store")
    public ResponseEntity<?> store(
            @Valid @RequestBody ProductDetailRequest productDetailRequest,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getFieldErrors().stream()
                    .map(FieldError::getDefaultMessage)
                    .toList();
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
        ProductDetailResponse productDetailResponse = productDetailService.storeProduct(productDetailRequest);
        return new ResponseEntity<>(productDetailResponse, HttpStatus.CREATED);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<?> update(
            @PathVariable(name = "id") Integer id,
            @Valid @RequestBody ProductDetailRequest productDetailRequest,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getFieldErrors().stream()
                    .map(FieldError::getDefaultMessage)
                    .toList();
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
        ProductDetailResponse productDetailResponse = productDetailService.updateProduct(id, productDetailRequest);
        return new ResponseEntity<>(productDetailResponse, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable(name = "id") Integer id) {
        productDetailService.deleteProduct(id);
        return ResponseEntity.ok("Delete product detail successfully with id: " + id);
    }
}
