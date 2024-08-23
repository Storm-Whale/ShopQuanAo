package com.whale.shopquanao.dto.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponse {

    private Integer id;

    private String categoryName;

    private List<ProductResponse> listProductResponse;
}
