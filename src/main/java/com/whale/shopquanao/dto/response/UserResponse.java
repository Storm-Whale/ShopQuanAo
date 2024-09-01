package com.whale.shopquanao.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {

    private int id;
    private String username;
    private String email;
    private String fullName;
    private String address;
    private String phoneNumber;
//    private List<RoleResponse> roles;
}
