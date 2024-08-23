package com.whale.shopquanao.dto.mapper;

import com.whale.shopquanao.dto.request.SizeRequest;
import com.whale.shopquanao.dto.response.ProductDetailResponse;
import com.whale.shopquanao.dto.response.SizeResponse;
import com.whale.shopquanao.entity.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class SizeMapper {

    private final ProductMapper productMapper;

    public SizeResponse toSizeResponse(Size size) {
        List<ProductDetailResponse> listProductDetailResponse = size.getListProductDetail().stream()
                .map(productMapper::toProductDetailResponse)
                .collect(Collectors.toList());
        return SizeResponse.builder()
                .id(size.getId())
                .sizeName(size.getSizeName())
                .listProductDetailResponse(listProductDetailResponse)
                .build();
    }

    public static Size toSize(SizeRequest sizeRequest) {
        return Size.builder()
                .sizeName(sizeRequest.getSizeName())
                .build();
    }

    public static Size toSize(Integer id, SizeRequest sizeRequest) {
        return Size.builder()
                .id(id)
                .sizeName(sizeRequest.getSizeName())
                .build();
    }
}
