package com.whale.shopquanao.dto.mapper;

import com.whale.shopquanao.dto.request.UserRequest;
import com.whale.shopquanao.dto.response.UserResponse;
import com.whale.shopquanao.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        uses = {RoleMapper.class}
)
public interface UserMapper {

    // Ánh xạ từ User entity sang UserResponse
    @Mapping(source = "user.roles", target = "roleSummaries")
    UserResponse toUserResponse(User user);

    // Ánh xạ từ UserRequest sang User entity, bao gồm ánh xạ idRole thành Role trong Set<Role>
    @Mapping(target = "roles", ignore = true)
    User toUser(Integer id, UserRequest userRequest);

    default User toUser(UserRequest userRequest) {
        return toUser(null, userRequest);
    }
}
