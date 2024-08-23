package com.whale.shopquanao.dto.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SizeResponse {

    private Integer id;

    private String sizeName;

    private List<ProductDetailResponse> listProductDetailResponse;
}
