package com.whale.shopquanao.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleResponse {

    int roleId;
    String roleName;
    String description;
    Set<UserSummary> userSummaries;
}

