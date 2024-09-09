package com.whale.shopquanao.dto.mapper;

import com.whale.shopquanao.dto.request.RoleRequest;
import com.whale.shopquanao.dto.response.RoleResponse;
import com.whale.shopquanao.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        uses = {UserMapper.class}
)
public interface RoleMapper {

    @Mapping(source = "role.users", target = "userSummaries")
    RoleResponse toRoleResponse(Role role);

    Role toRole(Integer id, RoleRequest roleRequest);

    default Role toRole(RoleRequest roleRequest) {
        return toRole(null, roleRequest);
    }
}

