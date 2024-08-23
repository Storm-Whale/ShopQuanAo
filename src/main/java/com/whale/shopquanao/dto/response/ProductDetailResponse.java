package com.whale.shopquanao.dto.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetailResponse {

    private Integer id;

    private String sizeName;

    private Double price;

    private Integer stockQuantity;

    private List<ProductImageResponse> listImage;
}
