package com.whale.shopquanao.dto.mapper;

import com.whale.shopquanao.dto.request.UserRequest;
import com.whale.shopquanao.dto.response.UserResponse;
import com.whale.shopquanao.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_DEFAULT
)
public interface UserMapper {

    UserResponse toUserResponse(User user);

    User toUser(UserRequest userRequest);

    User toUser(Integer id, UserRequest userRequest);
}

