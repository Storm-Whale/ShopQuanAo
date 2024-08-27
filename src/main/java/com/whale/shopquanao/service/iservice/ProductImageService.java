package com.whale.shopquanao.service.iservice;

import com.whale.shopquanao.dto.request.ProductImageRequest;
import com.whale.shopquanao.dto.response.ProductImageResponse;
import org.springframework.stereotype.Service;

@Service
public interface ProductImageService {

    ProductImageResponse storeProductImage(ProductImageRequest productImageRequest);
}
