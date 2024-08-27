package com.whale.shopquanao.service.service;

import com.whale.shopquanao.dto.mapper.ProductMapper;
import com.whale.shopquanao.dto.request.ProductImageRequest;
import com.whale.shopquanao.dto.response.ProductImageResponse;
import com.whale.shopquanao.entity.ProductImage;
import com.whale.shopquanao.exception.DataNotFoundException;
import com.whale.shopquanao.repository.ProductDetailRepository;
import com.whale.shopquanao.repository.ProductImageRepository;
import com.whale.shopquanao.service.iservice.ProductImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductImageServiceImpl implements ProductImageService {

    private final ProductDetailRepository productDetailRepository;
    private final ProductImageRepository productImageRepository;
    private final ProductMapper productMapper;

    @Override
    public ProductImageResponse storeProductImage(ProductImageRequest productImageRequest) {
        if (!productDetailRepository.existsById(productImageRequest.getIdProductDetail())) {
            throw new DataNotFoundException("Can not find product detail with id " + productImageRequest.getIdProductDetail());
        }
        ProductImage productImage = productMapper.toProductImage(productImageRequest);
        productImage = productImageRepository.save(productImage);
        return productMapper.toProductImageResponse(productImage);
    }
}
