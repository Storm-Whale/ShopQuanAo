package com.whale.shopquanao.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetailRequest {

    @NotNull(message = "Id Product is not required")
    private Integer idProduct;

    @NotNull(message = "Id Size is not required")
    private Integer idSize;

    @NotNull(message = "Price is not required")
    @Min(value = 0, message = "Price must be greater than zero")
    private Double price;

    @Min(value = 0, message = "Stock Quantity must be greater than zero")
    private Integer stockQuantity;
}
