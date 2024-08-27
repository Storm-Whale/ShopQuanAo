package com.whale.shopquanao.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ProductImageRequest {

    @NotNull(message = "Id product detail is not required")
    private Integer idProductDetail;

    @NotNull(message = "Id product detail is not required")
    private String imageUrl;
}
