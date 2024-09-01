package com.whale.shopquanao.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleResponse {

    int roleId;
    String roleName;
    String description;
//    List<UserResponse> users;
}
