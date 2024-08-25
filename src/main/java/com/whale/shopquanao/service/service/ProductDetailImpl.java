package com.whale.shopquanao.service.service;

import com.whale.shopquanao.dto.mapper.ProductMapper;
import com.whale.shopquanao.dto.request.ProductDetailRequest;
import com.whale.shopquanao.dto.response.ProductDetailResponse;
import com.whale.shopquanao.entity.ProductDetail;
import com.whale.shopquanao.exception.DataNotFoundException;
import com.whale.shopquanao.repository.ProductDetailRepository;
import com.whale.shopquanao.repository.SizeRepository;
import com.whale.shopquanao.service.iservice.ProductDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductDetailImpl implements ProductDetailService {

    private final ProductDetailRepository productDetailRepository;
    private final SizeRepository sizeRepository;
    private final ProductMapper productMapper;

    @Override
    public List<ProductDetailResponse> getAllProductDetail() {
        try {
            List<ProductDetailResponse> listProductDetailResponse = new ArrayList<>();
            productDetailRepository.findAll().forEach(
                    productDetail -> listProductDetailResponse.add(productMapper.toProductDetailResponse(productDetail))
            );
            return listProductDetailResponse;
        } catch (DataAccessException e) {
            throw new RuntimeException("Error retrieving products from the database", e);
        }
    }

    @Override
    public ProductDetailResponse getProductDetailById(Integer id) {
        try {
            return productDetailRepository.findById(id)
                    .map(productMapper::toProductDetailResponse)
                    .orElseThrow(() -> new DataNotFoundException("Product detail not found with id: " + id));
        } catch (DataAccessException e) {
            throw new RuntimeException("Error retrieving product detail from the database", e);
        }
    }

    @Override
    public ProductDetailResponse storeProduct(ProductDetailRequest productDetailRequest) {
        try {
            if (!sizeRepository.existsById(productDetailRequest.getIdSize())) {
                throw new DataNotFoundException("Size not found with id: " + productDetailRequest.getIdSize());
            }

            ProductDetail productDetail = productMapper.toProductDetail(productDetailRequest);
            productDetail = productDetailRepository.save(productDetail);

            return productMapper.toProductDetailResponse(productDetail);
        } catch (DataAccessException e) {
            throw new RuntimeException("Error retrieving product detail from the database", e);
        }
    }

    @Override
    public ProductDetailResponse updateProduct(Integer id, ProductDetailRequest productDetailRequest) {
        try {
            if (!sizeRepository.existsById(productDetailRequest.getIdSize())) {
                throw new DataNotFoundException("Size not found with id: " + productDetailRequest.getIdSize());
            }

            ProductDetail productDetail = productMapper.toProductDetail(id, productDetailRequest);
            productDetail = productDetailRepository.save(productDetail);

            return productMapper.toProductDetailResponse(productDetail);
        } catch (DataAccessException e) {
            throw new RuntimeException("Error retrieving product detail from the database", e);
        }
    }

    @Override
    public void deleteProduct(Integer id) {
        try {
            if (productDetailRepository.existsById(id)) {
                throw new DataNotFoundException("Product Detail not found with id " + id);
            }
            productDetailRepository.deleteById(id);
        } catch (DataAccessException e) {
            throw new RuntimeException("Error retrieving product detail from the database", e);
        }
    }
}
