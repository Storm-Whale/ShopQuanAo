package com.whale.shopquanao.dto.request;

import com.whale.shopquanao.entity.Category;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {
    @NotBlank(message = "Product name is not required")
    private String productName;

    private String description;

    @NotBlank(message = "Id Category is not required")
    private Integer idCategory;
}
