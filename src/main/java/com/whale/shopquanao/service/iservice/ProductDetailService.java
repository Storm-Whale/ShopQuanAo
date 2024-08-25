package com.whale.shopquanao.service.iservice;

import com.whale.shopquanao.dto.request.ProductDetailRequest;
import com.whale.shopquanao.dto.response.ProductDetailResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductDetailService {

    List<ProductDetailResponse> getAllProductDetail();

    ProductDetailResponse getProductDetailById(Integer id);

    ProductDetailResponse storeProduct(ProductDetailRequest productDetailRequest);

    ProductDetailResponse updateProduct(Integer id, ProductDetailRequest productDetailRequest);

    void deleteProduct(Integer id);
}
