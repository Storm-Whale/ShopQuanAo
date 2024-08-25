package com.whale.shopquanao.dto.mapper;

import com.whale.shopquanao.dto.request.ProductDetailRequest;
import com.whale.shopquanao.dto.request.ProductRequest;
import com.whale.shopquanao.dto.response.ProductDetailResponse;
import com.whale.shopquanao.dto.response.ProductImageResponse;
import com.whale.shopquanao.dto.response.ProductResponse;
import com.whale.shopquanao.entity.Product;
import com.whale.shopquanao.entity.ProductDetail;
import com.whale.shopquanao.entity.ProductImage;
import com.whale.shopquanao.entity.Size;
import com.whale.shopquanao.repository.CategoryRepository;
import com.whale.shopquanao.repository.ProductDetailRepository;
import com.whale.shopquanao.repository.ProductRepository;
import com.whale.shopquanao.repository.SizeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductMapper {

    private final CategoryRepository categoryRepository;
    private final SizeRepository sizeRepository;
    private final ProductRepository productRepository;
    private final ProductDetailRepository productDetailRepository;

    public ProductImageResponse toProductImageResponse(ProductImage productImage) {
        return ProductImageResponse.builder()
                .id(productImage.getId())
                .idProductDetail(productImage.getProductDetail().getId())
                .imageUrl(productImage.getImageUrl())
                .build();
    }

    public ProductDetailResponse toProductDetailResponse(ProductDetail productDetail) {
        List<ProductImageResponse> productImageResponse = new ArrayList<>();
        productDetail.getListProductImage().forEach(
                productImage -> productImageResponse.add(toProductImageResponse(productImage))
        );
        return ProductDetailResponse.builder()
                .id(productDetail.getId())
                .sizeName(productDetail.getSize().getSizeName())
                .price(productDetail.getPrice())
                .stockQuantity(productDetail.getStockQuantity())
                .listImage(productImageResponse)
                .build();
    }

    public ProductResponse toProductResponse(Product product) {
        List<ProductDetailResponse> productDetailResponse = new ArrayList<>();
        product.getListProductDetail().forEach(
                productDetail -> productDetailResponse.add(toProductDetailResponse(productDetail))
        );
        return ProductResponse.builder()
                .id(product.getId())
                .productName(product.getProductName())
                .description(product.getDescription())
                .categoryName(product.getCategory().getCategoryName())
                .listProductDetailResponse(productDetailResponse)
                .build();
    }

    public Product toProduct(ProductRequest productRequest) {
        return Product.builder()
                .productName(productRequest.getProductName())
                .description(productRequest.getDescription())
                .category(categoryRepository.findById(productRequest.getIdCategory())
                        .orElse(null))
                .build();
    }

    public Product toProduct(Integer id, ProductRequest productRequest) {
        Product existingProduct = productRepository.findById(id).orElse(null);
        return Product.builder()
                .productName(productRequest.getProductName())
                .description(productRequest.getDescription())
                .category(categoryRepository.findById(productRequest.getIdCategory())
                        .orElse(null))
                .listProductDetail(existingProduct != null ? existingProduct.getListProductDetail() : null)
                .build();
    }

    public ProductDetail toProductDetail(ProductDetailRequest productDetailRequest) {
        Product product = productRepository.findById(productDetailRequest.getIdProduct()).orElse(null);
        Size size = sizeRepository.findById(productDetailRequest.getIdSize()).orElse(null);
        return ProductDetail.builder()
                .product(product)
                .size(size)
                .price(productDetailRequest.getPrice())
                .stockQuantity(productDetailRequest.getStockQuantity())
                .build();
    }

    public ProductDetail toProductDetail(Integer id, ProductDetailRequest productDetailRequest) {
        ProductDetail existingProductDetail = productDetailRepository.findById(id).orElse(null);
        Product product = productRepository.findById(productDetailRequest.getIdProduct()).orElse(null);
        Size size = sizeRepository.findById(productDetailRequest.getIdSize()).orElse(null);
        return ProductDetail.builder()
                .id(id)
                .product(product)
                .size(size)
                .price(productDetailRequest.getPrice())
                .stockQuantity(productDetailRequest.getStockQuantity())
                .listProductImage(existingProductDetail != null ? existingProductDetail.getListProductImage() : null)
                .build();
    }
}
