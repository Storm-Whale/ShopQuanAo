package com.whale.shopquanao.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductImageResponse {

    private Integer id;

    private Integer idProductDetail;

    private String imageUrl;
}
