package com.whale.shopquanao.dto.response;

import lombok.*;

import java.util.Set;

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
    private Set<RoleSummary> roleSummaries;
}