package com.whale.shopquanao.service.service;

import com.whale.shopquanao.dto.mapper.ProductMapper;
import com.whale.shopquanao.dto.request.ProductRequest;
import com.whale.shopquanao.dto.response.ProductResponse;
import com.whale.shopquanao.entity.Product;
import com.whale.shopquanao.exception.DataNotFoundException;
import com.whale.shopquanao.repository.CategoryRepository;
import com.whale.shopquanao.repository.ProductRepository;
import com.whale.shopquanao.service.iservice.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;

    @Override
    public List<ProductResponse> getAllProduct() {
        try {
            List<ProductResponse> listProductResponse = new ArrayList<>();
            productRepository.findAll().forEach(
                    product -> listProductResponse.add(productMapper.toProductResponse(product))
            );
            return listProductResponse;
        } catch (DataAccessException e) {
            throw new RuntimeException("Error retrieving products from the database", e);
        }
    }

    @Override
    public ProductResponse getProductById(Integer id) {
        try {
            return productRepository.findById(id)
                    .map(productMapper::toProductResponse)
                    .orElseThrow(() -> new DataNotFoundException("Product not found with id: " + id));
        } catch (DataAccessException e) {
            throw new RuntimeException("Error retrieving products from the database", e);
        }
    }

    @Override
    public ProductResponse storeProduct(ProductRequest productRequest) {
        try {
            if (!categoryRepository.existsById(productRequest.getIdCategory())) {
                throw new DataNotFoundException("Category not found with id: " + productRequest.getIdCategory());
            }

            Product product = productMapper.toProduct(productRequest);
            product = productRepository.save(product);

            return productMapper.toProductResponse(product);
        } catch (DataAccessException e) {
            throw new RuntimeException("Error retrieving products from the database", e);
        }
    }

    @Override
    public ProductResponse updateProduct(Integer id, ProductRequest productRequest) {
        try {
            if (!categoryRepository.existsById(productRequest.getIdCategory())) {
                throw new DataNotFoundException("Category not found with id: " + productRequest.getIdCategory());
            }

            Product product = productMapper.toProduct(id, productRequest);
            product = productRepository.save(product);

            return productMapper.toProductResponse(product);
        } catch (DataAccessException e) {
            throw new RuntimeException("Error retrieving products from the database", e);
        }
    }

    @Override
    public void deleteProduct(Integer id) {
        try {
            if (!productRepository.existsById(id)) {
                throw new DataNotFoundException("Product not found with id: " + id);
            }
            productRepository.deleteById(id);
        } catch (DataAccessException e) {
            throw new RuntimeException("Error deleting product from the database", e);
        }
    }
}
