package com.whale.shopquanao.exception;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

    private HttpStatus httpStatus;

    private String message;

    private Date timestamp;

    private String detail;
}
