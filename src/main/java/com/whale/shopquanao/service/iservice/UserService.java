package com.whale.shopquanao.service.iservice;

import com.whale.shopquanao.dto.request.UserRequest;
import com.whale.shopquanao.dto.response.UserResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    List<UserResponse> getAllUsers();

    UserResponse getUserById(Integer id);

    UserResponse storeUser(UserRequest userRequest);

    UserResponse updateUser(Integer id, UserRequest userRequest);

    void deleteUser(Integer id);
}
