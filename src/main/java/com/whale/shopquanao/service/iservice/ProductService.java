package com.whale.shopquanao.service.iservice;

import com.whale.shopquanao.dto.request.ProductRequest;
import com.whale.shopquanao.dto.response.ProductResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {

    List<ProductResponse> getAllProduct();

    ProductResponse getProductById(Integer id);

    ProductResponse storeProduct(ProductRequest productRequest);

    ProductResponse updateProduct(Integer id, ProductRequest productRequest);

    void deleteProduct(Integer id);
}
