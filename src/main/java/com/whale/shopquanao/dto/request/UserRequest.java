package com.whale.shopquanao.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRequest {

    String username;
    String email;
    String password;
    String fullName;
    String address;
    String phoneNumber;
    Integer idRole;
}
