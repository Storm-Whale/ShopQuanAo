package com.whale.shopquanao.dto.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleRequest {

    String roleName;
    String description;
//    List<Integer> userIds;
}
