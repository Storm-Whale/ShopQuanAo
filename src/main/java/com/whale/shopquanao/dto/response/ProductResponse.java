package com.whale.shopquanao.dto.response;

import lombok.*;

import java.util.HashMap;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {

    private Integer id;

    private String productName;

    private String description;

    private String categoryName;

    private List<ProductDetailResponse> listProductDetailResponse;
}
