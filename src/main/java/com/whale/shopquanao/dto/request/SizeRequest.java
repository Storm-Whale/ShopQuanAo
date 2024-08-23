package com.whale.shopquanao.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SizeRequest {

    @NotBlank(message = "Size name is not required")
    private String sizeName;
}
